package com.connect.jpa.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinica_profissional")
public class ClinicaProfissionalModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_clinica", nullable = false)
    private Long clinicaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_profissional", nullable = false)
    private ProfissionalEspecialistaModel profissional;

    @Column(name = "especialidade")
    private String especialidade;

    public ClinicaProfissionalModel() {
    }

    public ClinicaProfissionalModel(Long clinicaId, ProfissionalEspecialistaModel profissional) {
        this.clinicaId = clinicaId;
        this.profissional = profissional;
    }

    public Long getId() {
        return id;
    }

    public Long getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Long clinicaId) {
        this.clinicaId = clinicaId;
    }

    public ProfissionalEspecialistaModel getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalEspecialistaModel profissional) {
        this.profissional = profissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clinicaId, id, profissional, especialidade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ClinicaProfissionalModel other = (ClinicaProfissionalModel) obj;
        return Objects.equals(clinicaId, other.clinicaId) && Objects.equals(id, other.id)
                && Objects.equals(profissional, other.profissional)
                && Objects.equals(especialidade, other.especialidade);
    }
}
