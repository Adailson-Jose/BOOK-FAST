package com.projeto.bookfast.bookfast.livro.dominio;

/**
 * Created by CIRANDA DA CIÊNCIA on 14/08/2017.
 */

public class ItemLivro {
    private int id;
    private int idLivro;
    private int idTtemAluguel;

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdTtemAluguel() {
        return idTtemAluguel;
    }

    public void setIdTtemAluguel(int idTtemAluguel) {
        this.idTtemAluguel = idTtemAluguel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


