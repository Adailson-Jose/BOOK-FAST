package com.projeto.bookfast.bookfast.pessoa.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;

/**
 * classe DeletPessoa cria associações das classes SQLiteDatabase e CreatBancoDados.
 */

public class DeletPessoa {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    /**
     * Construtor da classe DeletPessoa
     */
    public DeletPessoa(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    /**
     * método deleteTablePessoa deleta a pessoa da tabela pessoa.
     */
    public boolean deleteTablePessoa() {
        String deletTable = "DROP TABLE IF EXISTS " + CreatBancoDados.getNomeTabelaPessoa();
        db.execSQL(deletTable);
        db.close();
        return true;
    }

    /** método deletePessoa deleta a pessoa passando como parametro um objeto pessoa*/
    public boolean deletePessoa(Pessoa pessoa) {
        String deletePessoa = "cpf = '" + Long.toString(pessoa.getCpf()) + "'";
        db.delete(CreatBancoDados.getNomeTabelaPessoa(), deletePessoa, null);
        db.close();
        return true;
    }
}


