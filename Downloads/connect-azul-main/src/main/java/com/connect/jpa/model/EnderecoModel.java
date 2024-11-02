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
@Table(name = "endereco")
public class EnderecoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String rua;
	
	@Column(nullable = false)
	private Double numero;
	
	@Column(nullable = false)
	private String bairro;

	EnderecoModel() {

	}

	public EnderecoModel(Long id, String rua, Double numero, String bairro) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Double getNumero() {
		return numero;
	}

	public void setNumero(Double numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, id, numero, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoModel other = (EnderecoModel) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(id, other.id)
				&& Objects.equals(numero, other.numero) && Objects.equals(rua, other.rua);
	}

}
