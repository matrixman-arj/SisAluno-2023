package br.mil.eb.decex.sisaluno.repository.filter;

import java.util.List;

import br.mil.eb.decex.sisaluno.enumerated.PostoGraduacao;
import br.mil.eb.decex.sisaluno.model.Grupo;
import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;

public class UsuarioFilter {
	
	private String identidade;
	private PostoGraduacao posto;
	private String nomeGuerra;
	private OrganizacaoMilitar om;	
	private String email;
	private List<Grupo> grupos;
	
	
	public String getIdentidade() {
		return identidade;
	}
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	
	public PostoGraduacao getPosto() {
		return posto;
	}
	public void setPosto(PostoGraduacao posto) {
		this.posto = posto;
	}
	
	public String getNomeGuerra() {
		return nomeGuerra;
	}
	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}
	
	public OrganizacaoMilitar getOm() {
		return om;
	}
	public void setOm(OrganizacaoMilitar om) {
		this.om = om;
	}
		
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}	
					
}
