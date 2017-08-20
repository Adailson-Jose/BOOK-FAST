package com.projeto.bookfast.bookfast.pessoa.dominio;

/**
 * Created by oi on 20/08/2017.
 */

public enum EnuaStatus {
    ATIVO("Ativo"), INATIVO("Inativo");
    private String descricao;

    EnuaStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


