package com.projeto.bookfast.bookfast.livro.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

/**
 * Created by oi on 01/08/2017.
 */

public class DeletLivro {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public DeletLivro(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public boolean deleteTabelaLivro() {
        String deletTabelaLivro = "DROP TABLE IF EXISTS " + CreatBancoDados.getNomeTabelaLivro();
        db.execSQL(deletTabelaLivro);
        db.close();
        return true;
    }

    public boolean deleteLivro(Livro livro) {
        String deleteLivro = "isbn = '" + Long.toString(livro.getIsbn()) + "'";
        db.delete(CreatBancoDados.getNomeTabelaLivro(), deleteLivro, null);
        db.close();
        return true;
    }
}


