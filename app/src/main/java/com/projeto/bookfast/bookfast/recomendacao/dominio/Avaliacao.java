package com.projeto.bookfast.bookfast.recomendacao.dominio;

/**
 * Created by oi on 24/08/2017.
 */

public class Avaliacao {
    private int id;
    private int idPessoa;
    private int idLivro;
    private int avaliacao = 0;

    //Construtor para istanciar
    public Avaliacao() {
    }

    //Construtor para buscar no banco
    public Avaliacao(int id, int idPessoa, int idLivro, int avaliacao) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idLivro = idLivro;
        this.avaliacao = avaliacao;
    }

    //Construtor para inserir
    public Avaliacao(int idPessoa, int idLivro, int avaliacao) {
        this.idPessoa = idPessoa;
        this.idLivro = idLivro;
        this.avaliacao = avaliacao;
    }

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

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
}
