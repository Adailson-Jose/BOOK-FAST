package com.projeto.bookfast.bookfast.pessoa.percistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;

import java.util.ArrayList;

/**
 * Created by oi on 01/08/2017.
 */

public class ReadPessoa {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public ReadPessoa(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public ArrayList<Pessoa> getListaPessoas() {
        db = dbHelper.getReadableDatabase();
        ArrayList<Pessoa> pessoaArray = new ArrayList<>();
        String getPessoas = "SELECT * FROM " + CreatBancoDados.getNomeTabelaPessoa();
        try {
            Cursor cursor = db.rawQuery(getPessoas, null);
            if (cursor.moveToFirst()) {
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(cursor.getInt(0));
                    pessoa.setCpf(cursor.getLong(1));
                    pessoa.setNome(cursor.getString(2));
                    pessoa.setEmail(cursor.getString(3));
                    pessoa.setSenha(cursor.getString(4));
                    pessoa.setLivros(cursor.getString(5));
                    pessoaArray.add(pessoa);
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
        return pessoaArray;
    }
    //Obter pessoa pelo cpf

    public Pessoa getPessoa(Long cpf) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaPessoa(), new String[]{CreatBancoDados.getColunaId(), CreatBancoDados.getColunaCpf(),
                        CreatBancoDados.getColunaNome(), CreatBancoDados.getColunaEmail(), CreatBancoDados.getColunaSenha(), CreatBancoDados.getColunaIdsLivros()}, CreatBancoDados.getColunaCpf() + " = ?",
                new String[]{String.valueOf(cpf)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Pessoa pessoa = new Pessoa(cursor.getInt(0), cursor.getLong(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
            cursor.close();
            db.close();
            return pessoa;
        } else {
            db.close();
            return null;
        }

    }

}
