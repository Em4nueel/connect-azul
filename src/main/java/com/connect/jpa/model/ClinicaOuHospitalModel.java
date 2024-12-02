package com.connect.jpa.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clinica")
    private List<ContatoModel> contatos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_clinica")
    private List<EnderecoModel> enderecos;

    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

    public ClinicaOuHospitalModel() {}

    public ClinicaOuHospitalModel(String nome, String cnpj, List<ContatoModel> contatos, List<EnderecoModel> enderecos, Usuario usuario) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.contatos = contatos;
        this.enderecos = enderecos;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
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

    public List<ContatoModel> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoModel> contatos) {
        this.contatos = contatos;
    }

    public List<EnderecoModel> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, contatos, enderecos, id, nome);
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
        return Objects.equals(cnpj, other.cnpj) && Objects.equals(contatos, other.contatos)
                && Objects.equals(enderecos, other.enderecos) && Objects.equals(id, other.id)
                && Objects.equals(nome, other.nome);
    }
}
