package br.mil.eb.decex.sisaluno.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "curso_om")
public class CursoOm {

	@EmbeddedId
	private CursoOmId id;
	
	
	public CursoOmId getId() {
		return id;
	}

	public void setId(CursoOmId id) {
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
		CursoOm other = (CursoOm) obj;
		return Objects.equals(id, other.id);
	}		
	
}
