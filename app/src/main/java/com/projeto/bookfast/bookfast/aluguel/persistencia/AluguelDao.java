package com.projeto.bookfast.bookfast.aluguel.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

/**
 * Created by oi on 17/08/2017.
 */

public class AluguelDao {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public AluguelDao(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public boolean deleteAluguel(Aluguel aluguel) {
        String deletarAluguel = "id = '" + aluguel.getId() + "'";
        db.delete(CreatBancoDados.getTabelaAluguel(), deletarAluguel, null);
        db.close();
        return true;
    }

    public Aluguel getAluguel(int idPessoa, int idLivro) {
        db = dbHelper.getReadableDatabase();
        String getIdDoLivro = "SELECT * FROM " + CreatBancoDados.getTabelaAluguel() + " WHERE " + CreatBancoDados.getColunaPessoaAluguel() + " = " + idPessoa + " and " +
                CreatBancoDados.getColunaLivroAluguel() + " = " + idLivro + " and " + CreatBancoDados.getColunaStatusAluguel() + " = 1";
        Cursor cursor = db.rawQuery(getIdDoLivro, null);
        if (cursor != null && cursor.moveToFirst()) {
            Aluguel aluguel = new Aluguel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6));
            cursor.close();
            db.close();
            return aluguel;
        } else {
            db.close();
            return null;
        }
    }

    public Aluguel getAluguel(int idAluguel) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CreatBancoDados.getTabelaAluguel(), new String[]{CreatBancoDados.getColunaIdAluguel(),
                        CreatBancoDados.getColunaPessoaAluguel(), CreatBancoDados.getColunaLivroAluguel(),
                        CreatBancoDados.getColunaData(), CreatBancoDados.getColunaDataEntrega(),
                        CreatBancoDados.getColunaMultaEntrega(), CreatBancoDados.getColunaStatusAluguel()},
                CreatBancoDados.getColunaIdAluguel() + " = ?", new String[]{String.valueOf(idAluguel)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Aluguel aluguel = new Aluguel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6));
            cursor.close();
            db.close();
            return aluguel;
        } else {
            db.close();
            return null;
        }
    }

    public boolean insertAluguel(Aluguel aluguel) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoaAluguel(), aluguel.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAluguel(), aluguel.getIdLivro());
        valores.put(CreatBancoDados.getColunaData(), aluguel.getDate());
        valores.put(CreatBancoDados.getColunaDataEntrega(), aluguel.getDataEntrega());
        valores.put(CreatBancoDados.getColunaMultaEntrega(), aluguel.getMulta());
        valores.put(CreatBancoDados.getColunaStatusAluguel(), aluguel.getStatus());
        db.insert(CreatBancoDados.getTabelaAluguel(), null, valores);
        db.close();
        return true;
    }

    public boolean updateAluguel(Aluguel aluguel) {
        db = dbHelper.getWritableDatabase();
        String where = "ID_PESSOA_ALUGUEL = '" + Long.toString(aluguel.getIdPessoa()) + "'";
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaPessoaAluguel(), aluguel.getIdPessoa());
        valores.put(CreatBancoDados.getColunaLivroAluguel(), aluguel.getIdLivro());
        valores.put(CreatBancoDados.getColunaData(), aluguel.getDate());
        valores.put(CreatBancoDados.getColunaDataEntrega(), aluguel.getDataEntrega());
        valores.put(CreatBancoDados.getColunaMultaEntrega(), aluguel.getMulta());
        valores.put(CreatBancoDados.getColunaStatusAluguel(), aluguel.getStatus());
        db.update(CreatBancoDados.getTabelaAluguel(), valores, where, null);
        db.close();
        return true;
    }


}
