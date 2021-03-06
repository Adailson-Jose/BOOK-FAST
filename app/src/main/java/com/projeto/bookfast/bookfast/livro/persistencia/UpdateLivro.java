package com.projeto.bookfast.bookfast.livro.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

/**
 * classe UpdateLivro cria associações das classes SQLiteDatabase e CreatBancoDados
 */

public class UpdateLivro {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    /**
     * Construtor da classe UpdateLivro
     */
    public UpdateLivro(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    /**
     * Método insertLivro insere no banco de dados o livro cadastrado e retorna True se feito
     */
    public boolean insertLivro(Livro livro) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaIsbn(), livro.getIsbn());
        valores.put(CreatBancoDados.getColunaAno(), livro.getAno());
        valores.put(CreatBancoDados.getColunaAutor(), livro.getAutor());
        valores.put(CreatBancoDados.getColunaGenero(), livro.getGenero());
        valores.put(CreatBancoDados.getColunaNomeLivro(), livro.getNome());
        valores.put(CreatBancoDados.getColunaNEdicao(), livro.getNumEdicao());
        valores.put(CreatBancoDados.getColunaQtdAlugado(), livro.getQtdAlugado());
        valores.put(CreatBancoDados.getColunaQtdTotal(), livro.getQtdTotal());
        valores.put((CreatBancoDados.getColunaFotoLivro()), livro.getFotoLivro());
        db.insert(CreatBancoDados.getNomeTabelaLivro(), null, valores);
        db.close();
        return true;
    }

    /** Método updateLivro atualiza no banco de dados o livro cadastrado e retorna True se feito */
    public boolean updateLivro(Livro livro) {
        db = dbHelper.getWritableDatabase();
        String where = "isbn = '" + Long.toString(livro.getIsbn()) + "'";
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaIsbn(), livro.getIsbn());
        valores.put(CreatBancoDados.getColunaAno(), livro.getAno());
        valores.put(CreatBancoDados.getColunaAutor(), livro.getAutor());
        valores.put(CreatBancoDados.getColunaGenero(), livro.getGenero());
        valores.put(CreatBancoDados.getColunaNomeLivro(), livro.getNome());
        valores.put(CreatBancoDados.getColunaNEdicao(), livro.getNumEdicao());
        valores.put(CreatBancoDados.getColunaQtdAlugado(), livro.getQtdAlugado());
        valores.put(CreatBancoDados.getColunaQtdTotal(), livro.getQtdTotal());
        valores.put((CreatBancoDados.getColunaFotoLivro()), livro.getFotoLivro());
        db.update(CreatBancoDados.getNomeTabelaLivro(), valores, where, null);
        db.close();
        return true;
    }
}
