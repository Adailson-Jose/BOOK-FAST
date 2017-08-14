package com.projeto.bookfast.bookfast.livro.percistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

import java.util.ArrayList;

/**
 * Created by oi on 01/08/2017.
 */

public class ReadLivro {
    private SQLiteDatabase db;
    private CreatBancoDados dbHelper;


    public ReadLivro(Context context) {
        dbHelper = new CreatBancoDados(context);
    }

    public ReadLivro() {

    }

    public ArrayList<Livro> getListaLivro() {
        db = dbHelper.getReadableDatabase();
        ArrayList<Livro> livroArray = new ArrayList<>();
        String getLivro = "SELECT * FROM " + CreatBancoDados.getNomeTabelaLivro();
        Cursor cursor = db.rawQuery(getLivro, null);
        if (cursor.moveToFirst()) {
            do {
                Livro livro = new Livro();
                livro.setId(Integer.parseInt(cursor.getString(0)));
                livro.setIsbn(Long.parseLong(cursor.getString(1)));
                livro.setNome(cursor.getString(2));
                livro.setQtdAlugado(Integer.parseInt(cursor.getString(3)));
                livro.setAutor(cursor.getString(4));
                livro.setGenero(cursor.getString(5));
                livro.setQtdTotal(Integer.parseInt(cursor.getString(6)));
                livro.setAno(Integer.parseInt(cursor.getString(7)));
                livro.setNumEdicao(Integer.parseInt(cursor.getString(8)));
                livro.setFotoLivro(cursor.getBlob(9));
                livroArray.add(livro);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return livroArray;
    }
    //Obter livro pelo isbn
    public Livro getLivro(Long isbn) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaLivro(), new String[]{CreatBancoDados.getColunaIdLivro(),
                        CreatBancoDados.getColunaIsbn(), CreatBancoDados.getColunaNomeLivro(),
                        CreatBancoDados.getColunaQtdAlugado(), CreatBancoDados.getColunaAutor(),
                        CreatBancoDados.getColunaGenero(), CreatBancoDados.getColunaQtdTotal(),
                        CreatBancoDados.getColunaAno(), CreatBancoDados.getColunaNEdicao(), CreatBancoDados.getColunaFotoLivro()},
                CreatBancoDados.getColunaIsbn() + " = ?",
                new String[]{String.valueOf(isbn)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Livro livro = new Livro(cursor.getInt(0), cursor.getLong(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getBlob(9));
            cursor.close();
            db.close();
            return livro;
        } else {
            db.close();
            return null;
        }
    }

    public Livro getLivro(int id) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaLivro(), new String[]{CreatBancoDados.getColunaIdLivro(),
                        CreatBancoDados.getColunaIsbn(), CreatBancoDados.getColunaNomeLivro(),
                        CreatBancoDados.getColunaQtdAlugado(), CreatBancoDados.getColunaAutor(),
                        CreatBancoDados.getColunaGenero(), CreatBancoDados.getColunaQtdTotal(),
                        CreatBancoDados.getColunaAno(), CreatBancoDados.getColunaNEdicao(), CreatBancoDados.getColunaFotoLivro()},
                CreatBancoDados.getColunaIdLivro() + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Livro livro = new Livro(cursor.getInt(0), cursor.getLong(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getBlob(9));
            cursor.close();
            db.close();
            return livro;
        } else {
            db.close();
            return null;
        }
    }
}
