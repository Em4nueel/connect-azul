package com.connect.jpa.model;
import java.sql.Date;
import java.io.Serializable;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class PacienteModel extends PessoaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	PacienteModel() {

	}

	public PacienteModel(Usuario usuario, String nomeCompleto, String cpf, Date dataNascimento, EnderecoModel endereco) {
		super(nomeCompleto, cpf, dataNascimento, endereco);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		if (!super.equals(obj)) return false;  // Chama o equals da classe base PessoaModel

		PacienteModel other = (PacienteModel) obj;
		return Objects.equals(id, other.id);
	}

}
