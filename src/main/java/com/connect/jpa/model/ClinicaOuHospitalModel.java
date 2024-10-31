package com.connect.jpa.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinica_ou_hospital")
public class ClinicaOuHospitalModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome_clinica_hospital")
	private String nome;

	@Column(name = "cnpj", length = 14, nullable = false, unique = true)
	private String cnpj;

	@OneToMany
	@JoinColumn(name = "id_contato")
	private ContatoModel contato;

	@OneToMany
	@JoinColumn(name = "id_endereço")
	private EnderecoModel endereco;

	ClinicaOuHospitalModel() {

	}

	public ClinicaOuHospitalModel(Long id, String nome, String cnpj, ContatoModel contato, EnderecoModel endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.contato = contato;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public ContatoModel getContato() {
		return contato;
	}

	public void setContato(ContatoModel contato) {
		this.contato = contato;
	}

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, contato, endereco, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicaOuHospitalModel other = (ClinicaOuHospitalModel) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(contato, other.contato)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

}
