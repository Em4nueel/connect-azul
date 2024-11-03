package com.connect.jpa.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato")
public class ContatoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private Double telefone;

	private String site;

	private String redeSocial;

	ContatoModel() {

	}

	public ContatoModel(Long id, Double telefone, String site, String redeSocial) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.site = site;
		this.redeSocial = redeSocial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTelefone() {
		return telefone;
	}

	public void setTelefone(Double telefone) {
		this.telefone = telefone;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, redeSocial, site, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContatoModel other = (ContatoModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(redeSocial, other.redeSocial)
				&& Objects.equals(site, other.site) && Objects.equals(telefone, other.telefone);
	}

	@Override
    public String toString() {
        return "ContatoModel{" +
               "Telefone='" + telefone + '\'' +
               ", Site=" + site +
               '}';
    }

}
