package com.projeto.bookfast.bookfast.dominio.livro.percistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

import java.util.ArrayList;

/**
 * Created by oi on 01/08/2017.
 */

public class ReadLivro extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = CreatBancoDados.getNomeBanco();
    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/" + CreatBancoDados.getNomeBanco();
    private Context meuContext;
    private SQLiteDatabase db;

    public ReadLivro(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.meuContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // logica pra atualiza db

    }

    public ArrayList<Livro> getListaLivro() {
        openDB();
        ArrayList<Livro> livroArray = new ArrayList<>();
        String getLivro = "SELECT * FROM " + CreatBancoDados.getNomeTabelaLivro();

        try {
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
                    livroArray.add(livro);

                } while (cursor.moveToNext());
                cursor.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }

        return livroArray;
    }
    //Obter livro pelo isbn

    public Livro getLivro(Long isbn) {
        openDB();

        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaLivro(), new String[]{CreatBancoDados.getColunaIdLivro(),
                        CreatBancoDados.getColunaIsbn(), CreatBancoDados.getColunaNomeLivro(),
                        CreatBancoDados.getColunaQtdAlugado(), CreatBancoDados.getColunaAutor(),
                        CreatBancoDados.getColunaGenero(), CreatBancoDados.getColunaQtdTotal(),
                        CreatBancoDados.getColunaAno(), CreatBancoDados.getColunaNEdicao()},
                CreatBancoDados.getColunaIsbn() + " = ?",
                new String[]{String.valueOf(isbn)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Livro livro = new Livro(cursor.getInt(0), cursor.getLong(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8));
            cursor.close();
            db.close();
            return livro;
        } else {
            db.close();
            return null;
        }

    }

    private void openDB() {
        if (!db.isOpen()) {
            db = meuContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }
}
