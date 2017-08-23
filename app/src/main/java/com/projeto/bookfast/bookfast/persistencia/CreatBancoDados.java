
package com.projeto.bookfast.bookfast.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oi on 18/07/2017.
 */
public class CreatBancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "db_biblioteca";
    //VARIAVEIS DA TABELA ALLUGUEL
    private static final String TABELA_ALUGUEL = "TB_ALUGUEL";
    private static final String COLUNA_ID_ALUGUEL = "ID_ALUGEL";
    private static final String COLUNA_PESSOA_ALUGUEL = "ID_PESSOA_ALUGUEL";
    private static final String COLUNA_LIVRO_ALUGUEL = "ID_LIVRO_ALUGADO";
    private static final String COLUNA_DATA = "DATA";
    private static final String COLUNA_DATA_ENTREGA = "DATA_ENTREGA";
    private static final String COLUNA_MULTA_ENTREGA = "MULTA";
    private static final String COLUNA_STATUS_ALUGUEL = "STATOS_ALUGUEL";
    //VARIAVES DA TABELA PESSOA
    private static final String TABELA_PESSOA = "TB_PESSOA";
    private static final String COLUNA_ID = "ID";
    private static final String COLUNA_CPF = "CPF";
    private static final String COLUNA_NOME = "NOME";
    private static final String COLUNA_EMAIL = "EMAIL";
    private static final String COLUNA_SENHA = "SENHA";
    private static final String COLUNA_IDS_ALUGUEL = "IDS_ALUGUEL";
    private static final String COLUNA_STATUS_PESSOA = "STATOS_PESSOA";
    private static final String COLUNA_CURSO_PESSOA = "CURSO";
    //VARIAVES DA TABELA LIVRO
    private static final String TABELA_LIVRO = "TB_LIVRO";
    private static final String COLUNA_ID_LIVRO = "ID";
    private static final String COLUNA_ISBN = "ISBN";
    private static final String COLUNA_NOME_LIVRO = "NOME";
    private static final String COLUNA_AUTOR = "AUTOR";
    private static final String COLUNA_QTD_ALUGADO = "QTD_ALUGADO";
    private static final String COLUNA_QTD_TOTAL = "QTD_TOTAL";
    private static final String COLUNA_N_EDICAO = "N_EDICAO";
    private static final String COLUNA_ANO = "ANO";
    private static final String COLUNA_GENERO = "GENERO";
    private static final String COLUNA_FOTO_LIVRO = "FOTO_LIVRO";
    // private static final String COLUNA_IMAGEM = "IMAGEM";
    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/" + NOME_BANCO;

    public CreatBancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIA A TABELA PESSOA
        db.execSQL("create table " + TABELA_PESSOA + "(" + COLUNA_ID + " integer primary key autoincrement, "
                + COLUNA_CPF + " integer, " + COLUNA_NOME + " text not null, " + COLUNA_EMAIL + " text not null, "
                + COLUNA_IDS_ALUGUEL + " text not null, " + COLUNA_SENHA + " text not null, "
                + COLUNA_STATUS_PESSOA + " text not null, " + COLUNA_CURSO_PESSOA + " text not null)");

        //ADD USER ADMIN
        db.execSQL("INSERT INTO " + TABELA_PESSOA + "(" + COLUNA_CPF + "," + COLUNA_NOME + ","
                + COLUNA_EMAIL + "," + COLUNA_IDS_ALUGUEL + "," + COLUNA_SENHA + "," + COLUNA_STATUS_PESSOA + ","
                + COLUNA_CURSO_PESSOA + ") VALUES('19928810303', 'admin', 'admin@email.com', '', 'admin', '1', 'curso exemplo')");

        db.execSQL("INSERT INTO " + TABELA_PESSOA + "(" + COLUNA_CPF + "," + COLUNA_NOME + ","
                + COLUNA_EMAIL + "," + COLUNA_IDS_ALUGUEL + "," + COLUNA_SENHA + "," + COLUNA_STATUS_PESSOA + ","
                + COLUNA_CURSO_PESSOA + ") VALUES('10187805482', 'Leandro Braz', 'leandro_braz1992@hotmail.com', '', 'ando', '1','')");

        //CRIA TABELA LIVRO
        db.execSQL("create table " + TABELA_LIVRO + "(" + COLUNA_ID_LIVRO + " integer primary key autoincrement, " + COLUNA_ISBN
                + " integer, " + COLUNA_NOME_LIVRO + " text not null, " + COLUNA_QTD_ALUGADO + " integer, "
                + COLUNA_AUTOR + " text not null, " + COLUNA_GENERO + " text not null, " + COLUNA_QTD_TOTAL + " integer, "
                + COLUNA_ANO + " integer, " + COLUNA_N_EDICAO + " integer, " + COLUNA_FOTO_LIVRO + " blob)");

        //ADD LIVRO EXEMPLO
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788534610735', 'Banco de dados', '0', 'Abraham Silberschatz', 'didatico', '3', '2000', '2', '')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788565042307', 'Fundamentos da Fisica', '0', 'David Halliday', 'didatico', '3', '2010', '2', '')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788535267938', 'Use a Cabeça Java', '0', 'David Griffiths', 'didatico', '3', '1999', '2', '')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788586469510', 'Java Como programar', '0', 'Paul Dietel', 'didatico', '3', '1996', '2', '')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788555191862', 'C completo e Total', '0', 'Herb Schildt', 'didatico', '3', '1992', '2', '')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788599519967', 'Game Of thrones', '0', 'George R. R. Martin', 'Drama', '3', '1996', '2', '')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788580551044', 'A Clash of Kings', '0', 'George R. R. Martin', 'Drama', '3', '1998', '2','')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788521632566', 'A Storm Of Swords', '0', 'George R. R. Martin', 'Drama', '3', '2000', '2','')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788535275315', 'A Feast for Crows', '0', 'George R. R. Martin', 'Drama', '3', '2005', '2','')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788522507917', 'A Dance winth Dragons', '0', 'George R. R. Martin', 'Drama', '3', '2011', '2','')");
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + "," + COLUNA_FOTO_LIVRO +
                ") VALUES('9788525052407', 'A Riqueza das Nações', '0', 'Adam Sminth', 'Economia', '3', '1776', '2','')");

        //CRIA TABELA ALUGUEL
        db.execSQL("create table " + TABELA_ALUGUEL + "(" + COLUNA_ID_ALUGUEL + " integer primary key autoincrement, " + COLUNA_PESSOA_ALUGUEL + " integer, "
                + COLUNA_LIVRO_ALUGUEL + " integer, " + COLUNA_DATA + " text not null, " + COLUNA_DATA_ENTREGA + " text not null, "
                + COLUNA_MULTA_ENTREGA + " integer, " + COLUNA_STATUS_ALUGUEL + " text not null)");
        //ADD ALUGUEL EXEMPLO
        db.execSQL("INSERT INTO " + TABELA_ALUGUEL + "(" + COLUNA_ID_ALUGUEL + "," + COLUNA_PESSOA_ALUGUEL + ","
                + COLUNA_LIVRO_ALUGUEL + "," + COLUNA_DATA + "," + COLUNA_DATA_ENTREGA + "," + COLUNA_MULTA_ENTREGA + ","
                + COLUNA_STATUS_ALUGUEL + ") VALUES('100', '1', '2', '11/08/2017', '11/09/2017', '100', '1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // logica pra atualiza db
        db.execSQL("DROP TABLE IF EXISTS" + TABELA_PESSOA);
        db.execSQL("DROP TABLE IF EXISTS" + TABELA_LIVRO);
        db.execSQL("DROP TABLE IF EXISTS" + TABELA_ALUGUEL);

        onCreate(db);
    }

    //GETS TABELA PESSOA E BANCO
    public static String getColunaId() {
        return COLUNA_ID;
    }

    public static String getColunaCpf() {
        return COLUNA_CPF;
    }

    public static String getColunaNome() {
        return COLUNA_NOME;
    }

    public static String getColunaEmail() {
        return COLUNA_EMAIL;
    }

    public static String getColunaSenha() {
        return COLUNA_SENHA;
    }

    public static String getColunaIdsAluguel() {
        return COLUNA_IDS_ALUGUEL;
    }

    public static String getNomeTabelaPessoa() {
        return TABELA_PESSOA;
    }

    public static String getColunaStatusPessoa() {
        return COLUNA_STATUS_PESSOA;
    }

    public static String getColunaCursoPessoa() {
        return COLUNA_CURSO_PESSOA;
    }
    //GETS TABELA LIVRO

    public static String getNomeTabelaLivro() {
        return TABELA_LIVRO;
    }

    public static String getColunaIdLivro() {
        return COLUNA_ID_LIVRO;
    }

    public static String getColunaIsbn() {
        return COLUNA_ISBN;
    }

    public static String getColunaNomeLivro() {
        return COLUNA_NOME_LIVRO;
    }

    public static String getColunaAutor() {
        return COLUNA_AUTOR;
    }

    public static String getColunaQtdAlugado() {
        return COLUNA_QTD_ALUGADO;
    }

    public static String getColunaQtdTotal() {
        return COLUNA_QTD_TOTAL;
    }

    public static String getColunaNEdicao() {
        return COLUNA_N_EDICAO;
    }

    public static String getColunaAno() {
        return COLUNA_ANO;
    }

    public static String getColunaGenero() {
        return COLUNA_GENERO;
    }

    public static String getColunaFotoLivro() {
        return COLUNA_FOTO_LIVRO;
    }

    public static String getColunaIdAluguel() {
        return COLUNA_ID_ALUGUEL;
    }

    public static String getColunaPessoaAluguel() {
        return COLUNA_PESSOA_ALUGUEL;
    }

    public static String getColunaData() {
        return COLUNA_DATA;
    }

    public static String getColunaDataEntrega() {
        return COLUNA_DATA_ENTREGA;
    }

    public static String getTabelaAluguel() {
        return TABELA_ALUGUEL;
    }

    public static String getColunaLivroAluguel() {
        return COLUNA_LIVRO_ALUGUEL;
    }

    public static String getColunaMultaEntrega() {
        return COLUNA_MULTA_ENTREGA;
    }

    public static String getColunaStatusAluguel() {
        return COLUNA_STATUS_ALUGUEL;
    }

}

