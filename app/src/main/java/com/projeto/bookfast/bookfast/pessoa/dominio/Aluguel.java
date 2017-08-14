package com.projeto.bookfast.bookfast.pessoa.dominio;

/**
 * Created by CIRANDA DA CIÊNCIA on 14/08/2017.
 */

public class Aluguel {
    private int id;
    private int idPessoa;
    private int idItemAluguel;

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

    public int getIdItemAluguel() {
        return idItemAluguel;
    }

    public void setIdItemAluguel(int idItemAluguel) {
        this.idItemAluguel = idItemAluguel;
    }
}
