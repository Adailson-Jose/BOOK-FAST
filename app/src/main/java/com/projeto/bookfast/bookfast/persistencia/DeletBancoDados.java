package com.projeto.bookfast.bookfast.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projeto.bookfast.bookfast.dominio.Livro;
import com.projeto.bookfast.bookfast.dominio.Pessoa;

/**
 * Created by oi on 18/07/2017.
 */
public class DeletBancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = CreatBancoDados.getNomeBanco();

    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/" + CreatBancoDados.getNomeBanco();
    private Context meuContext;
    private SQLiteDatabase db;

    public DeletBancoDados(Context context) {
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

    public boolean deleteTablePessoa() {
        openDB();
        String deletTable = "DROP TABLE IF EXISTS " + CreatBancoDados.getNomeTabelaPessoa();
        try {
            db.execSQL(deletTable);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean deletePessoa(Pessoa pessoa) {
        openDB();

        String deletePessoa = "cpf = '" + Long.toString(pessoa.getCpf()) + "'";

        try {
            db.delete(CreatBancoDados.getNomeTabelaPessoa(), deletePessoa, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }

    }

    public boolean deleteTabelaLivro() {
        openDB();
        String deletTabelaLivro = "DROP TABLE IF EXISTS " + CreatBancoDados.getNomeTabelaLivro();
        try {
            db.execSQL(deletTabelaLivro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean deleteLivro(Livro livro) {
        openDB();

        String deleteLivro = "isbn = '" + Long.toString(livro.getIsbn()) + "'";

        try {
            db.delete(CreatBancoDados.getNomeTabelaLivro(), deleteLivro, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }

    }

    private void openDB() {
        if (!db.isOpen()) {
            db = meuContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }


}


