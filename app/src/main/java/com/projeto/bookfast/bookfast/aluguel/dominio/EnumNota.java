package com.projeto.bookfast.bookfast.aluguel.dominio;

/**
 * Created by oi on 20/08/2017.
 */

public enum EnumNota {
    RUIM("Ruim"), MEDIO("Médio"), BOM("Bom"), OTIMO("Ótimo");

    private String descricao;

    EnumNota(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String[] listaEnumNotas() {
        EnumNota[] listaNotas = EnumNota.values();
        String[] lista = new String[listaNotas.length];
        for (int i = 0; i < listaNotas.length; i++) {
            lista[i] = listaNotas[i].getDescricao();
        }
        return lista;
    }
}



