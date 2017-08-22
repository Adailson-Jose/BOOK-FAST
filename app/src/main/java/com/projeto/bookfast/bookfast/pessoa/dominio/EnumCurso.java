package com.projeto.bookfast.bookfast.pessoa.dominio;

/**
 * Created by oi on 20/08/2017.
 */

public enum EnumCurso {
    SISTEMAS_DE_INFORMACAO("SISTEMAS DE INFORMAÇÃO"), MATEMATICA("MATEMÁTICA"), FISICA("FÍSICA"),
    CIENCIA_DA_COMPUTACAO("CIÊNCIA DA COMPUTAÇÃO"), OUTRO("OUTRO");
    private String descricao;

    EnumCurso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String capitalize() {
        return this.toString().substring(0, 1) + this.toString().substring(1).toLowerCase();
    }

    public static String[] listaEnumCurso() {
        EnumCurso[] listaCurso = EnumCurso.values();
        String[] lista = new String[listaCurso.length];
        for (int i = 0; i < listaCurso.length; i++) {
            lista[i] = listaCurso[i].capitalize();
        }
        return lista;
    }
}



