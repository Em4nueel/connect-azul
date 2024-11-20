package com.connect.jpa.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@MappedSuperclass
public class PessoaModel {

    private String nomeCompleto;
    private String cpf;
    private Date dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoModel endereco;

    public PessoaModel() {}

    public PessoaModel(String nomeCompleto, String cpf, Date dataNascimento, EnderecoModel enderecoModel) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = enderecoModel;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nome) {
        this.nomeCompleto = nome;
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
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaModel that = (PessoaModel) obj;
        return Objects.equals(cpf, that.cpf) &&
               Objects.equals(dataNascimento, that.dataNascimento) &&
               Objects.equals(nomeCompleto, that.nomeCompleto) &&
               Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto, cpf, dataNascimento, endereco);
    }
}
