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

    private static final String TABELA_PESSOA = "tb_pessoa";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_CPF = "cpf";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_EMAIL = "email";
    private static final String COLUNA_SENHA = "senha";

    private static final String TABELA_LIVRO = "tb_livro";
    //Falta criar

    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/bd_biblioteca";
    private Context meuContext;
    private SQLiteDatabase db;

    public CreatBancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.meuContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // logica pra atualiza db

    }

    public boolean createTablePessoa() {
        openDB();
        String createTablePessoa = "CREATE TABLE IF NOT EXITS " + TABELA_PESSOA + " ( "
                + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_CPF + " INTEGER, "
                + COLUNA_NOME + " TEXT NOT NULL, " + COLUNA_EMAIL + " TEXT NOT NULL, "
                + COLUNA_SENHA + " TEXT NOT NULL)";
        try {
            db.execSQL(createTablePessoa);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }

    }

    public void createTableLivro() {
        //Fala criar a lógica
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
