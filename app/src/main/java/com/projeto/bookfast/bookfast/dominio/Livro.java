package com.projeto.bookfast.bookfast.dominio;

public class Livro {
    private String nome;
    private String autor;
    private String isbn;
    private int ano;
    private int numEdicao;
    private String genero;
    private int qtdTotal;
    private int qtdDisponivel;

    public Livro(String nome, String autor, String isbn, int ano, int numEdicao, String genero, int qtdTotal, int qtdDisponivel) {
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.numEdicao = numEdicao;
        this.genero = genero;
        this.qtdTotal = qtdTotal;
        this.qtdDisponivel = qtdDisponivel;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }
}
