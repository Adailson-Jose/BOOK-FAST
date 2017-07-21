package com.projeto.bookfast.bookfast.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.dominio.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adailson on 21/07/2017.
 */

public class PessoaDao {
    private BancoDados bancoDados;
    private PessoaDao pessoa;
    private SQLiteDatabase db;

    public PessoaDao(Context context) {
        bancoDados = new BancoDados(context);
        pessoa = new PessoaDao(context);
    }

    public void addPessoa(Pessoa pessoa) {
        db = bancoDados.getReadableDatabase();
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
        db = bancoDados.getWritableDatabase();

        db.delete(bancoDados.getNomeTabelaPessoa(), bancoDados.getColunaId() + " = ?", new String[]{String.valueOf(pessoa.getId())});
        db.close();
    }

    //Obter pessoa pelo ID
    public Pessoa getPessoa(long id) {
        db = bancoDados.getReadableDatabase();
        Cursor cursor = db.query(bancoDados.getNomeTabelaPessoa(), new String[]{bancoDados.getColunaId(), bancoDados.getColunaCpf(),
                        bancoDados.getColunaNome(), bancoDados.getColunaEmail(), bancoDados.getColunaSenha()}, bancoDados.getColunaCpf() + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Pessoa pessoa = new Pessoa(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return pessoa;
    }

    public void atualizarPessoa(Pessoa pessoa) {
        db = bancoDados.getReadableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(bancoDados.getColunaId(), pessoa.getId());
        valores.put(bancoDados.getColunaCpf(), pessoa.getCpf());
        valores.put(bancoDados.getColunaNome(), pessoa.getNome());
        valores.put(bancoDados.getColunaEmail(), pessoa.getEmail());
        valores.put(bancoDados.getColunaSenha(), pessoa.getSenha());
        db.update(bancoDados.getNomeTabelaPessoa(), valores, bancoDados.getColunaId() + " = ?",
                new String[]{String.valueOf(pessoa.getId())});
        db.close();
    }

    public List<Pessoa> getListaPessoas() {
        List<Pessoa> listaPessoa = new ArrayList<Pessoa>();

        String quary = "SELECT * FROM " + bancoDados.getNomeTabelaPessoa();

        db = bancoDados.getWritableDatabase();
        Cursor cursor = db.rawQuery(quary, null);

        if (cursor.moveToFirst()) do {
            Pessoa pessoa = new Pessoa(Long.parseLong(cursor.getString(0), cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4));
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