package com.projeto.bookfast.bookfast.recomendacao.dominio;

/**
 * classe Avaliacao recebe os atributos id, idPessoa, idLivro e avaliacao.
 */

public class Avaliacao {
    private int id;
    private int idPessoa;
    private int idLivro;
    private Double avaliacao;

    /**
     * Construtor para instanciar.
     */
    public Avaliacao() {
    }

    /**
     * Construtor para buscar no banco.
     */

    public Avaliacao(int id, int idPessoa, int idLivro, Double avaliacao) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idLivro = idLivro;
        this.avaliacao = avaliacao;
    }

    //Construtor para inserir.
    public Avaliacao(int idPessoa, int idLivro, Double avaliacao) {
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

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
