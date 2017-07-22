package com.projeto.bookfast.bookfast.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.projeto.bookfast.bookfast.dominio.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adailson on 21/07/2017.
 */

public class PessoaDao {
    private SQLiteDatabase db;

    public PessoaDao(Context context) {
        BancoDados bancoDados = new BancoDados(context);
        db = bancoDados.getWritableDatabase();
    }

    public void addPessoa(Pessoa pessoa) {

        ContentValues valores = new ContentValues();

        String colunaCpf = BancoDados.getColunaCpf();
        valores.put(colunaCpf, pessoa.getCpf());

        String colunaId = BancoDados.getColunaId();
        valores.put(colunaId, pessoa.getId());

        String colunaNome = BancoDados.getColunaNome();
        valores.put(colunaNome, pessoa.getNome());

        String colunaEmail = BancoDados.getColunaEmail();
        valores.put(colunaEmail, pessoa.getEmail());

        String colunaSenha = BancoDados.getColunaSenha();
        valores.put(colunaSenha, pessoa.getSenha());

        db.insert(BancoDados.getNomeBanco(), null, valores);
        db.close();
    }

    public void deletarPessoa(Pessoa pessoa) {
        db.delete(BancoDados.getNomeTabelaPessoa(), BancoDados.getColunaId() + " = ?", new String[]{String.valueOf(pessoa.getId())});
        db.close();
    }

    //Obter pessoa pelo ID
    public Pessoa getPessoa(long id) {
        Cursor cursor = db.query(BancoDados.getNomeTabelaPessoa(), new String[]{BancoDados.getColunaId(), BancoDados.getColunaCpf(),
                        BancoDados.getColunaNome(), BancoDados.getColunaEmail(), BancoDados.getColunaSenha()}, BancoDados.getColunaCpf() + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Pessoa pessoa = new Pessoa(Long.parseLong(cursor.getString(0)), Long.parseLong(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return pessoa;
    }

    public void atualizarPessoa(Pessoa pessoa) {
        //db = bancodados.getReadableDatabase();

        ContentValues valores = new ContentValues();
        //valores.put(BancoDados.getColunaId(), pessoa.getId());
        valores.put(BancoDados.getColunaCpf(), pessoa.getCpf());
        valores.put(BancoDados.getColunaNome(), pessoa.getNome());
        valores.put(BancoDados.getColunaEmail(), pessoa.getEmail());
        valores.put(BancoDados.getColunaSenha(), pessoa.getSenha());
        db.update(BancoDados.getNomeTabelaPessoa(), valores, BancoDados.getColunaId() + " = ?",
                new String[]{String.valueOf(pessoa.getId())});
        db.close();
    }

    public List<Pessoa> getListaPessoas() {
        List<Pessoa> listaPessoa = new ArrayList<Pessoa>();

        String quary = "SELECT * FROM " + BancoDados.getNomeTabelaPessoa();

        //db = bancoDados.getWritableDatabase();
        Cursor cursor = db.rawQuery(quary, null);

        if (cursor.moveToFirst()) do {
            Pessoa pessoa = new Pessoa(Long.parseLong(cursor.getString(0)), Long.parseLong(cursor.getString(0)), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            pessoa.setId(Long.parseLong(cursor.getString(0)));
            pessoa.setCpf(Long.parseLong(cursor.getString(1)));
            pessoa.setNome(cursor.getString(2));
            pessoa.setEmail(cursor.getString(3));
            pessoa.setSenha(cursor.getString(4));

            listaPessoa.add(pessoa);
        } while (cursor.moveToNext());
        return listaPessoa;

    }


}