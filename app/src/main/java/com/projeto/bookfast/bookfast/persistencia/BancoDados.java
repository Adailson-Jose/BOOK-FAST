package com.projeto.bookfast.bookfast.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by oi on 18/07/2017.
 */
public class BancoDados extends SQLiteOpenHelper{
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "bd_biblioteca";

    private static final String TABELA_PESSOA = "tb_pessoa";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_CPF = "cpf";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_SENHA = "senha";

    public BancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA = "CREATE TABLE " + TABELA_PESSOA + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_CPF + "TEXT NOT NULL, "
                + COLUNA_NOME + " TEXT NOT NULL, " + COLUNA_EMAIL + " TEXT NOT NULL, "+ COLUNA_SENHA
                + " TEXT NOT NULL)";
        db.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABELA_PESSOA;
        db.execSQL(query);
        this.onCreate(db);
    }

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


}
