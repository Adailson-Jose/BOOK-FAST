package com.projeto.bookfast.bookfast.dominio.pessoa.dominio;


public class Pessoa {
    private int id;
    private long cpf;
    private String nome;
    private String email;
    private String senha;
    private String livros;
    //Construtor para instanciar
    public Pessoa(){
    }

    //Construtor para buscar no banco
    public Pessoa(int id, long cpf, String nome, String email, String senha, String livros) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.livros = livros;
    }
    //Construtor para inserir
    public Pessoa(long cpf, String nome, String email, String senha, String livros) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.livros = livros;
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLivros() {
        return livros;
    }

    public void setLivros(String livros) {
        this.livros = livros;
    }
}
