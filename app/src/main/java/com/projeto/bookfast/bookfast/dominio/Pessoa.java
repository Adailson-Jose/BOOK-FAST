package com.projeto.bookfast.bookfast.dominio;


public class Pessoa {
    private long id;
    private long cpf;
    private String nome;
    private String email;
    private String senha;


    //Construtor para instanciar
    public Pessoa(){

    }
    //Construtor para atualizar
    public Pessoa(long _id, long _cpf, String _nome, String _email, String _senha) {
        this.id = _id;
        this.cpf = _cpf;
        this.nome = _nome;
        this.email = _email;
        this.senha = _senha;
    }
    //Construtor para inserir
    public Pessoa(long _cpf, String _nome, String _email, String _senha) {
        this.cpf = _cpf;
        this.nome = _nome;
        this.email = _email;
        this.senha = _senha;
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
