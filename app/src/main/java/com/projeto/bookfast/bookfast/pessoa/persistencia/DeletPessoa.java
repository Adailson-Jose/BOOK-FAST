package com.projeto.bookfast.bookfast.pessoa.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;

/**
 * Created by oi on 01/08/2017.
 */

public class DeletPessoa {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    public DeletPessoa(Context context) {
        dbHelper = new CreatBancoDados(context);
    }
    public boolean deleteTablePessoa() {
        String deletTable = "DROP TABLE IF EXISTS " + CreatBancoDados.getNomeTabelaPessoa();
        db.execSQL(deletTable);
        db.close();
        return true;
    }

    public boolean deletePessoa(Pessoa pessoa) {
        String deletePessoa = "cpf = '" + Long.toString(pessoa.getCpf()) + "'";
        db.delete(CreatBancoDados.getNomeTabelaPessoa(), deletePessoa, null);
        db.close();
        return true;
    }
}


