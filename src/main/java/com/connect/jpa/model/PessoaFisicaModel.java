package com.connect.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisicaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	@Column(nullable = false)
	private String nomeCompleto;

	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(nullable = false)
	private Date dataNascimento;

	private EnderecoModel enderecoModel;

	private ContatoModel contatoModel;

	PessoaFisicaModel() {

	}

	public PessoaFisicaModel(Long id, String email, String nomeCompleto, String cpf, Date data,
			EnderecoModel enderecoModel, ContatoModel contatoModel) {
		super();
		this.id = id;
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = data;
		this.enderecoModel = enderecoModel;
		this.contatoModel = contatoModel;
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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnderecoModel getEndereco() {
		return enderecoModel;
	}

	public void setEndereco(EnderecoModel enderecoModel) {
		this.enderecoModel = enderecoModel;
	}

	public ContatoModel getContato() {
		return contatoModel;
	}

	public void setContato(ContatoModel contatoModel) {
		this.contatoModel = contatoModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contatoModel, cpf, dataNascimento, email, enderecoModel, id, nomeCompleto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisicaModel other = (PessoaFisicaModel) obj;
		return Objects.equals(contatoModel, other.contatoModel) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(enderecoModel, other.enderecoModel) && Objects.equals(id, other.id)
				&& Objects.equals(nomeCompleto, other.nomeCompleto);
	}

}
