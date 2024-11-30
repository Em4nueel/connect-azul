package com.connect.jpa.model.enums;

public enum FaixaEtariaModel {
    JOVEM("JOVEM(13 - 17)"),
    INFANTIL("INFANTIL(00 - 12)"),
    ADULTO("ADULTO(18 - 59)"),
    IDOSO("IDOSO(60++)");

    private final String nome;

    FaixaEtariaModel(String nome) {
        this.nome = nome;
    }

    public String getnome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}