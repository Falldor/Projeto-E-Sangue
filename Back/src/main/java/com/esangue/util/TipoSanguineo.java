package com.esangue.util;

public enum TipoSanguineo {

    Apositivo("A+"),
    Anegativo("A-"),
    Bpositivo("B+"),
    Bnegativo("B-"),
    ABpositivo("AB+"),
    ABnegativo("AB-"),
    Opositivo("O+"),
    Onegativo("O-");

    private String descricao;

    TipoSanguineo(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }

}
