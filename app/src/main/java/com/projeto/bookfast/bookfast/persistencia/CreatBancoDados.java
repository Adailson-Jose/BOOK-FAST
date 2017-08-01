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
    //VARIAVES DA TABELA PESSOA
    private static final String TABELA_PESSOA = "TB_PESSOA";
    private static final String COLUNA_ID = "ID";
    private static final String COLUNA_CPF = "CPF";
    private static final String COLUNA_NOME = "NOME";
    private static final String COLUNA_EMAIL = "EMAIL";
    private static final String COLUNA_SENHA = "SENHA";
    // VARIAVES DA TAPELA USUARIO
    private static final String TABELA_USUARIO = "TB_USUARIO";
    private static final String COLUNA_ID_USER = "ID";
    private static final String COLUNA_ID_PESSOA = "ID_PESSOA";
    private static final String COLUNA_ATIVO = "ATIVO";
    private static final String COLUNA_LISTA_USUARIO = "LISTA_USUARIO";

    //VARIAVES DA TABELA PESSOA
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
    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/" + NOME_BANCO;
    private Context meuContext;
    private SQLiteDatabase db;

    public CreatBancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.meuContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIA A TABELA PESSOA
        db.execSQL("create table " + TABELA_PESSOA + "(" + COLUNA_ID + " integer primary key autoincrement, "
                + COLUNA_CPF + " integer, " + COLUNA_NOME + " text not null, " + COLUNA_EMAIL + " text not null, "
                + COLUNA_SENHA + " text not null)");

        //ADD USER ADMIN
        db.execSQL("INSERT INTO " + TABELA_PESSOA + "(" + COLUNA_CPF + "," + COLUNA_NOME + ","
                + COLUNA_EMAIL + "," + COLUNA_SENHA + ") VALUES('19928810303', 'admin', 'admin@email.com', 'admin')");

        //CRIA TABELA USUARIO
        db.execSQL("create table " + TABELA_USUARIO + "(" + COLUNA_ID_USER + " integer primary key autoincrement, " + COLUNA_ID_PESSOA
                + " integer, " + COLUNA_ATIVO + " integer, " + COLUNA_LISTA_USUARIO + " text not null)");

        //CRIA TABELA LIVRO
        db.execSQL("create table " + TABELA_LIVRO + "(" + COLUNA_ID_LIVRO + " integer primary key autoincrement, " + COLUNA_ISBN
                + " integer, " + COLUNA_NOME_LIVRO + " text not null, " + COLUNA_QTD_ALUGADO + " integer, "
                + COLUNA_AUTOR + " text not null, " + COLUNA_GENERO + " text not null, " + COLUNA_QTD_TOTAL + " integer, " + COLUNA_ANO + " integer, " + COLUNA_N_EDICAO + " integer)");

        //ADD LIVRO EXEMPLO
        db.execSQL("INSERT INTO " + TABELA_LIVRO + "(" + COLUNA_ISBN + "," + COLUNA_NOME_LIVRO + ","
                + COLUNA_QTD_ALUGADO + "," + COLUNA_AUTOR + "," + COLUNA_GENERO + "," + COLUNA_QTD_TOTAL + "," + COLUNA_ANO + "," + COLUNA_N_EDICAO + ") VALUES('1234567890', 'EXEMPLO', '10', 'AUTOR EXEMPLO', 'EXEMPLO GENERO', '50', '2017', '0')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // logica pra atualiza db

    }

    private void openDB() {
        if (!db.isOpen()) {
            db = meuContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

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

    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static String getNomeTabelaPessoa() {
        return TABELA_PESSOA;
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
}
