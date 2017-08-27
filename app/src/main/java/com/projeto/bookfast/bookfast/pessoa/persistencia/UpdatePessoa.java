package com.projeto.bookfast.bookfast.pessoa.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;

/**
 * classe UpdatePessoa, cria uma associação das classes SQLiteDatabase e CreatBancoDados.
 */

public class UpdatePessoa {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;

    /**
     * Construtor da classe UpdatePessoa
     */
    public UpdatePessoa(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    /**
     * método insertPessoa recebe um objeto do tipo pessoa e insere no banco de dados
     */
    public boolean insertPessoa(Pessoa pessoa) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaCpf(), pessoa.getCpf());
        valores.put(CreatBancoDados.getColunaNome(), pessoa.getNome());
        valores.put(CreatBancoDados.getColunaEmail(), pessoa.getEmail());
        valores.put(CreatBancoDados.getColunaSenha(), pessoa.getSenha());
        valores.put(CreatBancoDados.getColunaIdsAluguel(), pessoa.getListaAluguel());
        valores.put(CreatBancoDados.getColunaStatusPessoa(), pessoa.getStatus());
        valores.put(CreatBancoDados.getColunaCursoPessoa(), pessoa.getCurso());
        db.insert(CreatBancoDados.getNomeTabelaPessoa(), null, valores);
        db.close();
        return true;
    }

    /** método updatePessoa recebe um objeto do tipo pessoa e altera no banco de dados*/
    public boolean updatePessoa(Pessoa pessoa) {
        db = dbHelper.getWritableDatabase();
        String where = "cpf = '" + Long.toString(pessoa.getCpf()) + "'";
        ContentValues valores = new ContentValues();
        valores.put(CreatBancoDados.getColunaCpf(), pessoa.getCpf());
        valores.put(CreatBancoDados.getColunaNome(), pessoa.getNome());
        valores.put(CreatBancoDados.getColunaEmail(), pessoa.getEmail());
        valores.put(CreatBancoDados.getColunaSenha(), pessoa.getSenha());
        valores.put(CreatBancoDados.getColunaIdsAluguel(), pessoa.getListaAluguel());
        valores.put(CreatBancoDados.getColunaStatusPessoa(), pessoa.getStatus());
        valores.put(CreatBancoDados.getColunaCursoPessoa(), pessoa.getCurso());
        db.update(CreatBancoDados.getNomeTabelaPessoa(), valores, where, null);
        db.close();
        return true;
    }
}

