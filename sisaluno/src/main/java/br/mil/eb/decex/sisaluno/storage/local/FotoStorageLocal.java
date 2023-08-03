 package br.mil.eb.decex.sisaluno.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.mil.eb.decex.sisaluno.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Profile("local")
@Component
public class FotoStorageLocal implements FotoStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	private static final String THUMBNAIL_PREFIX = "thumbnail.";
	
	private Path local;
	private Path localTemporario;
	
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".sisalunofotos"));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();		
	}
			
	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if(files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {				
				throw new RuntimeException("Erro ao tentar salvar na pasta temporária", e);
			}
		}
		return novoNome;
		
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporária", e);
		}
	}
	
	@Override
	public void salvar(String foto) {
		try {
			
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
			
		} catch (IOException e) {
			throw new RuntimeException("Erro ao tentar mover foto para o destino final! ", e);
		}	
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(50, 70).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
			System.out.println("Thumbnail definitivo = " + local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao tentar gerar thumbnail", e);
		}		
	}
	
	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto", e);
		}
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pastas criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporária: " + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
	}
	

	public String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nome original: %s, novo nome: %s", nomeOriginal, novoNome));
		}
		
		return novoNome;
		
	}	
	
	@Override
	public byte[] recuperarThumbnail(String fotoUsuario) {		
		return recuperar(THUMBNAIL_PREFIX + fotoUsuario);
	}
	
//	@Override
//	public byte[] recuperarThumbnailAluno(String fotoAluno) {		
//		return recuperar(THUMBNAIL_PREFIX + fotoAluno);
//	}
//	
//	@Override
//	public byte[] recuperarThumbnailCurso(String fotoCurso) {		
//		return recuperar(THUMBNAIL_PREFIX + fotoCurso);
//	}	
	
	@Override
	public void excluir(String foto) {			
		try {			
			Files.deleteIfExists(this.local.resolve(foto + foto));			
			Files.deleteIfExists(this.local.resolve(THUMBNAIL_PREFIX + foto));
		} catch (IOException e) {
			logger.warn(String.format("Erro ao tentar apagar a foto '%s'. Mensagem: %s", foto, e.getMessage()));
		}
		
	}
	
}
