package com.connect.jpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "endereco")
public class EnderecoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Identificador único do endereço", example = "1")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 255)
    @Schema(description = "Nome da rua do endereço", example = "Rua das Palmeiras")
    private String rua;

    @NotNull
    @Column(nullable = false)
    @Schema(description = "Número do endereço", example = "123")
    private Double numero;

    @NotNull
    @Column(nullable = false, length = 100)
    @Schema(description = "Nome do bairro", example = "Centro")
    private String bairro;

    @NotNull
    @Column(nullable = false, length = 20)
    @Schema(description = "CEP do endereço", example = "59000-000")
    private String cep;

    @NotNull
    @Column(nullable = false, length = 100)
    @Schema(description = "Cidade do endereço", example = "Natal")
    private String cidade;

    @Column(nullable = false, length = 2)
    @Schema(description = "Estado do endereço (focado no RN)", example = "RN")
    private String estado = "RN";  // Fixo para o RN

    @Column(length = 100)
    @Schema(description = "Complemento do endereço", example = "Apartamento 101")
    private String complemento;

    // Construtores
    public EnderecoModel() {}

    public EnderecoModel(Long id, String rua, Double numero, String bairro, String cep, String cidade, String complemento) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
    }

    // Getters e Setters
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    // Métodos equals e hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, rua, numero, bairro, cep, cidade, estado, complemento);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EnderecoModel that = (EnderecoModel) obj;
        return Objects.equals(id, that.id) &&
               Objects.equals(rua, that.rua) &&
               Objects.equals(numero, that.numero) &&
               Objects.equals(bairro, that.bairro) &&
               Objects.equals(cep, that.cep) &&
               Objects.equals(cidade, that.cidade) &&
               Objects.equals(estado, that.estado) &&
               Objects.equals(complemento, that.complemento);
    }

    @Override
    public String toString() {
        return "EnderecoModel{" +
               "id=" + id +
               ", rua='" + rua + '\'' +
               ", numero=" + numero +
               ", bairro='" + bairro + '\'' +
               ", cep='" + cep + '\'' +
               ", cidade='" + cidade + '\'' +
               ", estado='" + estado + '\'' +
               ", complemento='" + complemento + '\'' +
               '}';
    }
}
