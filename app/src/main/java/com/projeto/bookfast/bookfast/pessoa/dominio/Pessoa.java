package com.projeto.bookfast.bookfast.pessoa.dominio;

public class Pessoa {
    private int id;
    private long cpf;
    private String nome;
    private String email;
    private String senha;
    private String listaAluguel;
    private String status = "0";
    private String curso;
    //Construtor para instanciar
    public Pessoa(){
    }

    //Construtor para buscar no banco
    public Pessoa(int id, long cpf, String nome, String email, String senha, String listaAluguel, String status, String curso) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaAluguel = listaAluguel;
        this.status = status;
        this.curso = curso;
    }
    //Construtor para inserir
    public Pessoa(long cpf, String nome, String email, String senha, String listaAlugue, String status, String curso) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaAluguel = listaAlugue;
        this.status = status;
        this.curso = curso;
    }

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

    public String getListaAluguel() {
        return listaAluguel;
    }

    public void setListaAluguel(String listaAluguel) {
        this.listaAluguel = listaAluguel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
