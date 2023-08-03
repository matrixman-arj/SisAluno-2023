package br.mil.eb.decex.sisaluno.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "aluno_om")
public class AlunoOm {

	@EmbeddedId
	private AlunoOmId id;
	
	
	public AlunoOmId getId() {
		return id;
	}

	public void setId(AlunoOmId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlunoOm other = (AlunoOm) obj;
		return Objects.equals(id, other.id);
	}		
	
}
