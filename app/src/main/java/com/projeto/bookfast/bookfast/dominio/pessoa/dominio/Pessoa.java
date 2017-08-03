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
    public Pessoa(int _id, long _cpf, String _nome, String _email, String _senha, String _livros) {
        this.id = _id;
        this.cpf = _cpf;
        this.nome = _nome;
        this.email = _email;
        this.senha = _senha;
        this.livros = _livros;
    }
    //Construtor para inserir
    public Pessoa(long _cpf, String _nome, String _email, String _senha, String _livros) {
        this.cpf = _cpf;
        this.nome = _nome;
        this.email = _email;
        this.senha = _senha;
        this.livros = _livros;
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
