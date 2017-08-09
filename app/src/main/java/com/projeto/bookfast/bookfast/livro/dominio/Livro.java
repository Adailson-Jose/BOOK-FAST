package com.projeto.bookfast.bookfast.livro.dominio;

public class Livro {
    private int id;
    private String nome;
    private String autor;
    private Long isbn;
    private int ano;
    private int numEdicao;
    private String genero;
    private int qtdTotal;
    private int qtdAlugado;

    //Construtor para instanciar
    public Livro() {

    }

    //Construtor para buscar no banco
    public Livro(int id, Long isbn, String nome, int qtdAlugado, String autor, String genero, int qtdTotal, int ano, int numEdicao) {
        this.id = id;
        this.isbn = isbn;
        this.nome = nome;
        this.qtdAlugado = qtdAlugado;
        this.autor = autor;
        this.genero = genero;
        this.qtdTotal = qtdTotal;
        this.ano = ano;
        this.numEdicao = numEdicao;
    }

    //Construtor para inserir
    public Livro(Long isbn, String nome, int qtdAlugado, String autor, String genero, int qtdTotal, int ano, int numEdicao) {
        this.isbn = isbn;
        this.nome = nome;
        this.qtdAlugado = qtdAlugado;
        this.autor = autor;
        this.genero = genero;
        this.qtdTotal = qtdTotal;
        this.ano = ano;
        this.numEdicao = numEdicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getNumEdicao() {
        return numEdicao;
    }

    public void setNumEdicao(int numEdicao) {
        this.numEdicao = numEdicao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(int qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public int getQtdAlugado() {
        return qtdAlugado;
    }

    public void setQtdAlugado(int qtdAlugado) {
        this.qtdAlugado = qtdAlugado;
    }

}
