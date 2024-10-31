package com.connect.jpa.model;

import java.io.Serializable;
import java.util.Objects;

import com.connect.jpa.model.enums.FaixaEtariaModel;
import com.connect.jpa.model.enums.TipoProfissionalModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profissional_especialista")
public class ProfissionalEspecialistaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	@Enumerated(EnumType.STRING)
	private TipoProfissionalModel especialista;

	@Enumerated(EnumType.STRING)
	private FaixaEtariaModel idadePaciente;

	private Boolean experiencia;

	ProfissionalEspecialistaModel() {

	}

	public ProfissionalEspecialistaModel(Long id, String email, TipoProfissionalModel especialista,
			FaixaEtariaModel idadePaciente, Boolean experiencia) {
		super();
		this.id = id;
		this.email = email;
		this.especialista = especialista;
		this.idadePaciente = idadePaciente;
		this.experiencia = experiencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoProfissionalModel getEspecialista() {
		return especialista;
	}

	public void setEspecialista(TipoProfissionalModel especialista) {
		this.especialista = especialista;
	}

	public FaixaEtariaModel getIdadePaciente() {
		return idadePaciente;
	}

	public void setIdadePaciente(FaixaEtariaModel idadePaciente) {
		this.idadePaciente = idadePaciente;
	}

	public Boolean getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Boolean experiencia) {
		this.experiencia = experiencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, especialista, experiencia, id, idadePaciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfissionalEspecialistaModel other = (ProfissionalEspecialistaModel) obj;
		return Objects.equals(email, other.email) && especialista == other.especialista
				&& Objects.equals(experiencia, other.experiencia) && Objects.equals(id, other.id)
				&& idadePaciente == other.idadePaciente;
	}

}
