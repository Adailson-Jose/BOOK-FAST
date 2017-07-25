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

    private static final String TABELA_PESSOA = "TB_PESSOA";
    private static final String COLUNA_ID = "ID";
    private static final String COLUNA_CPF = "CPF";
    private static final String COLUNA_NOME = "NOME";
    private static final String COLUNA_EMAIL = "EMAIL";
    private static final String COLUNA_SENHA = "SENHA";

    private static final String TABELA_LIVRO = "tb_livro";
    //Falta criar

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
        db.execSQL("create table " + TABELA_PESSOA + "(" + COLUNA_ID + " integer primary key autoincrement, " + COLUNA_CPF + " integer, " + COLUNA_NOME + " text not null, " + COLUNA_EMAIL + " text not null, " + COLUNA_SENHA + " text not null)");

        //user admin
        db.execSQL("INSERT INTO " + TABELA_PESSOA + "(" + COLUNA_CPF + "," + COLUNA_NOME + ","
                + COLUNA_EMAIL + "," + COLUNA_SENHA + ") VALUES('1234567890', 'admin', 'admin@email.com', 'admin')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // logica pra atualiza db

    }

    public boolean createTablePessoa() {
        openDB();

        try {
            db.execSQL("create table " + TABELA_PESSOA + "(" + COLUNA_ID + " integer primary key autoincrement, " + COLUNA_CPF + " integer, " + COLUNA_NOME + " text not null, " + COLUNA_EMAIL + " text not null, " + COLUNA_SENHA + " text not null)");

            //user admin
            db.execSQL("INSERT INTO " + TABELA_PESSOA + "(" + COLUNA_CPF + "," + COLUNA_NOME + ","
                    + COLUNA_EMAIL + "," + COLUNA_SENHA + ") VALUES('1234567890', 'admin', 'admin@email.com', 'admin')");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }

    }

    public void createTableLivro() {
        //Falta criar a lógica
    }

    private void openDB() {
        if (!db.isOpen()) {
            db = meuContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

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
