package br.mil.eb.decex.sisaluno.storage;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public interface FotoStorage {
			
	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String nome);

	public void salvar(String foto);	

	public byte[] recuperar(String foto);
	
	public byte[] recuperarThumbnail(String fotoUsuario);	
		
//	public byte[] recuperarThumbnailAluno(String fotoAluno);
//	
//	public byte[] recuperarThumbnailCurso(String fotoCurso);	

	public void excluir(String foto);

//	public String getUrl(String foto);

	default String renomearArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

		
}
