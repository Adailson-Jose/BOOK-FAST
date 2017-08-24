package com.projeto.bookfast.bookfast.recomendacao.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.recomendacao.dominio.Avaliacao;

/**
 * Created by oi on 24/08/2017.
 */

public class AvaliacaoDao {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public AvaliacaoDao(Context context) {
        dbHelper = new CreatBancoDados(context);
    }


    public Avaliacao getAvaliacaoDao(int idPessoa, int idLivro) {
        db = dbHelper.getReadableDatabase();
        String getIdAvaliacaoDao = "SELECT * FROM " + CreatBancoDados.getTabelaAvaliacao() + " WHERE " + CreatBancoDados.getColunaPessoAvaliacao() + " = " + idPessoa + " and " +
                CreatBancoDados.getColunaLivroAvaliacao() + " = " + idLivro;
        Cursor cursor = db.rawQuery(getIdAvaliacaoDao, null);
        if (cursor != null && cursor.moveToFirst()) {
            Avaliacao avaliacao = new Avaliacao(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
            cursor.close();
            db.close();
            return avaliacao;
        } else {
            db.close();
            return null;
        }
    }

    public Avaliacao getAvaliacaoDao(int idAvaliacao) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CreatBancoDados.getTabelaAvaliacao(), new String[]{CreatBancoDados.getColunaIdAvaliacao(),
                CreatBancoDados.getColunaPessoAvaliacao(), CreatBancoDados.getColunaLivroAvaliacao(),
                CreatBancoDados.getColunaValorAvaliacao()}, CreatBancoDados.getColunaIdAvaliacao() + " = ?", new String[]{String.valueOf(idAvaliacao)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Avaliacao avaliacao = new Avaliacao(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
            cursor.close();
            db.close();
            return avaliacao;
        } else {
            db.close();
            return null;
        }
    }


    public boolean insertAvaliacaoDao(Avaliacao avaliacao) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoAvaliacao(), avaliacao.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAvaliacao(), avaliacao.getIdLivro());
        valores.put(CreatBancoDados.getColunaValorAvaliacao(), avaliacao.getAvaliacao());
        db.insert(CreatBancoDados.getTabelaAluguel(), null, valores);
        db.close();
        return true;
    }

    public boolean updateAvaliacaoDao(Avaliacao avaliacao) {
        db = dbHelper.getWritableDatabase();
        String where = CreatBancoDados.getColunaIdAvaliacao() + " = '" + Long.toString(avaliacao.getId()) + "'";
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoAvaliacao(), avaliacao.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAvaliacao(), avaliacao.getIdLivro());
        valores.put(CreatBancoDados.getColunaValorAvaliacao(), avaliacao.getAvaliacao());
        db.update(CreatBancoDados.getTabelaAluguel(), valores, where, null);
        db.close();
        return true;
    }
}