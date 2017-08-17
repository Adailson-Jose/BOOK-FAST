package com.projeto.bookfast.bookfast.pessoa.dominio;

import java.util.Date;

/**
 * Created by CIRANDA DA CIÊNCIA on 14/08/2017.
 */

public class Aluguel {
    private int id;
    private int idPessoa;
    private int date;
    private int dataEntrega;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(int dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}

