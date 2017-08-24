package com.projeto.bookfast.bookfast.aluguel.dominio;

/**
 * Created by CIRANDA DA CIÊNCIA on 14/08/2017.
 */

public class Aluguel {
    private int id;
    private int idPessoa;
    private int idLivro;
    private String date;
    private String dataEntrega;
    private int multa;
    private String status = "0";

    //Construtor para istanciar
    public Aluguel() {
    }
    //Construtor para buscar no banco
    public Aluguel(int id, int idPessoa, int idLivro, String date, String dataEntrega, int multa, String status) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idLivro = idLivro;
        this.date = date;
        this.dataEntrega = dataEntrega;
        this.multa = multa;
        this.status = status;
    }
    //Construtor para inserir
    public Aluguel(int idPessoa, int idLivro, String date, String dataEntrega, int multa, String status) {
        this.idPessoa = idPessoa;
        this.idLivro = idLivro;
        this.date = date;
        this.dataEntrega = dataEntrega;
        this.multa = multa;
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

