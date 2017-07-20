package com.projeto.bookfast.book_fast.dominio;

/**
 * Created by oi on 19/07/2017.
 */

public class Pessoa {
    private int cpf;
    private String nome;
    private String email;
    private String senha;

    //Construtor para instanciar
    public Pessoa(){

    }
    //Construtor para atualizar
    public Pessoa(int _cpf, String _nome,String _email, String _senha){
        this.cpf = _cpf;
        this.nome = _nome;
        this.email = _email;
        this.senha = _senha;
    }
    //Construtor para inserir
    public Pessoa(String _nome,String _email, String _senha){
        this.nome = _nome;
        this.email = _email;
        this.senha = _senha;
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
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
