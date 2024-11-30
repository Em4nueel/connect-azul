package com.connect.jpa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import com.connect.jpa.model.enums.FaixaEtariaModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profissional_especialista")
public class ProfissionalEspecialistaModel extends PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "especialidade_id")
	private EspecialidadeModel especialidade;

    @Enumerated(EnumType.STRING)
    private FaixaEtariaModel faixaEtaria;

    private Boolean experiencia;

    public ProfissionalEspecialistaModel() {
    }

    public ProfissionalEspecialistaModel(UserInfo usuario, EspecialidadeModel especialidade,
                                          FaixaEtariaModel idadePaciente, Boolean experiencia,
                                          String nomeCompleto, String cpf, Date dataNascimento,
                                          EnderecoModel endereco) {
        super(nomeCompleto, cpf, dataNascimento, endereco);
        this.especialidade = especialidade;
        this.faixaEtaria = idadePaciente;
        this.experiencia = experiencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EspecialidadeModel getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeModel especialidade) {
        this.especialidade = especialidade;
    }

    public FaixaEtariaModel getFaixaEtaria() {
        return faixaEtaria;
    }

    public Boolean getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Boolean experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(especialidade, experiencia, id, faixaEtaria);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProfissionalEspecialistaModel other = (ProfissionalEspecialistaModel) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(especialidade, other.especialidade)
                && Objects.equals(experiencia, other.experiencia)
                && faixaEtaria == other.faixaEtaria;
    }
}
