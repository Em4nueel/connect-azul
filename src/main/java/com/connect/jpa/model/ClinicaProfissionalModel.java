package com.connect.jpa.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clinica_profissional")
public class ClinicaProfissionalModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_clinica", nullable = false)
    private ClinicaOuHospitalModel clinica;

    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false)
    private ProfissionalEspecialistaModel profissional;

    @Column(name = "especialidade")
    private String especialidade;

    public ClinicaProfissionalModel() {}

    public ClinicaProfissionalModel(ClinicaOuHospitalModel clinica, ProfissionalEspecialistaModel profissional) {
        this.clinica = clinica;
        this.profissional = profissional;
    }

    public Long getId() {
        return id;
    }


    public ClinicaOuHospitalModel getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaOuHospitalModel clinica) {
        this.clinica = clinica;
    }

    public ProfissionalEspecialistaModel getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalEspecialistaModel profissional) {
        this.profissional = profissional;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clinica, id, profissional, especialidade);
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
        return Objects.equals(clinica, other.clinica) && Objects.equals(id, other.id)
                && Objects.equals(profissional, other.profissional) && Objects.equals(especialidade, other.especialidade);
    }
}
