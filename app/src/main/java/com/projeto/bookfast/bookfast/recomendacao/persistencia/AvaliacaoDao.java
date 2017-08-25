package com.projeto.bookfast.bookfast.recomendacao.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.recomendacao.dominio.Avaliacao;

import java.util.ArrayList;

/**
 * Created by oi on 24/08/2017.
 */

public class AvaliacaoDao {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public AvaliacaoDao(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public Avaliacao getAvaliacaoIdPessoIdLivro(int idPessoa, int idLivro) {
        db = dbHelper.getReadableDatabase();
        String getIdAvaliacaoDao = "SELECT * FROM " + CreatBancoDados.getTabelaAvaliacao() + " WHERE " + CreatBancoDados.getColunaPessoAvaliacao() + " = " + idPessoa + " and " +
                CreatBancoDados.getColunaLivroAvaliacao() + " = " + idLivro;
        Cursor cursor = db.rawQuery(getIdAvaliacaoDao, null);
        if (cursor != null && cursor.moveToFirst()) {
            Avaliacao avaliacao = new Avaliacao(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getDouble(3));
            cursor.close();
            db.close();
            return avaliacao;
        } else {
            db.close();
            return null;
        }
    }

    public ArrayList<Avaliacao> getListaAvaliacaoPessoa(int idPessoa) {
        db = dbHelper.getReadableDatabase();
        ArrayList<Avaliacao> listaAvaliacaoPessoa = new ArrayList<>();
        String getIdAvaliacao = "SELECT * FROM " + CreatBancoDados.getTabelaAvaliacao() + " WHERE " + CreatBancoDados.getColunaPessoAvaliacao() + " = " + idPessoa;

        try {
            Cursor cursor = db.rawQuery(getIdAvaliacao, null);
            if (cursor.moveToFirst()) {
                do {
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setId(cursor.getInt(0));
                    avaliacao.setIdPessoa(cursor.getInt(1));
                    avaliacao.setIdLivro(cursor.getInt(2));
                    avaliacao.setAvaliacao(cursor.getDouble(3));
                    listaAvaliacaoPessoa.add(avaliacao);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
        return listaAvaliacaoPessoa;
    }

    public ArrayList<Avaliacao> getListaAvaliacao() {
        db = dbHelper.getReadableDatabase();
        ArrayList<Avaliacao> avaliacaooArray = new ArrayList<>();
        String getAvaliacao = "SELECT * FROM " + CreatBancoDados.getTabelaAvaliacao();
        Cursor cursor = db.rawQuery(getAvaliacao, null);
        if (cursor.moveToFirst()) {
            do {
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setId(cursor.getInt(0));
                avaliacao.setIdPessoa(cursor.getInt(1));
                avaliacao.setIdLivro(cursor.getInt(2));
                avaliacao.setAvaliacao(cursor.getDouble(3));
                avaliacaooArray.add(avaliacao);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return avaliacaooArray;
    }


    public boolean insertAvaliacaoDao(Avaliacao avaliacao) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoAvaliacao(), avaliacao.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAvaliacao(), avaliacao.getIdLivro());
        valores.put(CreatBancoDados.getColunaValorAvaliacao(), avaliacao.getAvaliacao());
        db.insert(CreatBancoDados.getTabelaAvaliacao(), null, valores);
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
        db.update(CreatBancoDados.getTabelaAvaliacao(), valores, where, null);
        db.close();
        return true;
    }
}