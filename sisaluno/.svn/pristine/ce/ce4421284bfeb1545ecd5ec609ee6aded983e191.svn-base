package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import org.springframework.context.ApplicationEventPublisher;

import br.mil.eb.decex.sisaluno.enumerated.StatusUsuario;
import br.mil.eb.decex.sisaluno.model.Usuario;
import br.mil.eb.decex.sisaluno.repository.Usuarios;
import br.mil.eb.decex.sisaluno.service.event.usuario.UsuarioSalvoEvent;
import br.mil.eb.decex.sisaluno.service.exception.IdentidadeJaCadastradaException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.SenhaObrigatoriaUsuarioException;
import br.mil.eb.decex.sisaluno.storage.FotoStorage;

@Service
public class CadastroUsuarioService {
		
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
		
	@Transactional
	public void salvar(Usuario usuario) {
				
		Optional<Usuario> usuarioExistente = usuarios.findByIdentidade(usuario.getIdentidade().replaceAll("\\.|-", ""));		
				
		//Se tiver um usuário e a identidade for igual a do usuário  que tentamos salvar, mostra a mensagem ...
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new IdentidadeJaCadastradaException("Identidade já cadastrada!");
		}
		//Se usuário for novo e não tiver inserido uma senha...
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		//Se usuário for novo e tiver inserido uma senha, pega-se essa senha e encoda...
		} else if(usuario.isNovo() && !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}
		//Se usuário não for novo(update) e tiver inserido uma senha, pega-se essa senha e encoda...
		if (!usuario.isNovo() && !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		//Se não se usuário não for novo (update) e não tiver inserido uma senha, pega-se a senha desse usuario e insere do jeito que está...
		} else if(!usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}

		usuario.setConfirmacaoSenha(usuario.getSenha());

		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarios.save(usuario);				
		publisher.publishEvent(new UsuarioSalvoEvent(usuario));
	}
	
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}
	
	@Transactional
	public void excluir(Usuario usuario ) {
		try {
			
			String foto = usuario.getFoto();
			usuarios.delete(usuario);
			usuarios.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar usuario. Já foi usada em alguma entidade.");
		}
	}

}
