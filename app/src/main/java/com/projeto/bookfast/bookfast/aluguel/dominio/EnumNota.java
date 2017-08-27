package com.projeto.bookfast.bookfast.aluguel.dominio;

/**
 * Método EnumNota tem as constantes Ruim, Regular, Bom e Ótimo.
 */

public enum EnumNota {
    RUIM("Ruim"), MEDIO("Regular"), BOM("Bom"), OTIMO("Ótimo");

    private String descricao;

    EnumNota(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Metodo listaEnumNotas retorna uma lista com os valores recebidos
     */
    public static String[] listaEnumNotas() {
        EnumNota[] listaNotas = EnumNota.values();
        String[] lista = new String[listaNotas.length];
        for (int i = 0; i < listaNotas.length; i++) {
            lista[i] = listaNotas[i].getDescricao();
        }
        return lista;
    }
}



