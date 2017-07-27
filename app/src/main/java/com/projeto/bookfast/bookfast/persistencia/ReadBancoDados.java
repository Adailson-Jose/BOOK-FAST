package com.projeto.bookfast.bookfast.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projeto.bookfast.bookfast.dominio.Livro;
import com.projeto.bookfast.bookfast.dominio.Pessoa;

import java.util.ArrayList;

/**
 * Created by oi on 18/07/2017.
 */
public class ReadBancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = CreatBancoDados.getNomeBanco();
    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/" + CreatBancoDados.getNomeBanco();
    private Context meuContext;
    private SQLiteDatabase db;

    public ReadBancoDados(Context context) {
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


    public ArrayList<Pessoa> getListaPessoas() {
        openDB();
        ArrayList<Pessoa> pessoaArray = new ArrayList<>();
        String getPessoas = "SELECT * FROM " + CreatBancoDados.getNomeTabelaPessoa();

        try {
            Cursor cursor = db.rawQuery(getPessoas, null);

            if (cursor.moveToFirst()) {
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(cursor.getInt(0));
                    pessoa.setCpf(cursor.getInt(0));
                    pessoa.setNome(cursor.getString(2));
                    pessoa.setEmail(cursor.getString(3));
                    pessoa.setSenha(cursor.getString(4));
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

    public Pessoa getPessoa(Integer cpf) {
        openDB();
        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaPessoa(), new String[]{CreatBancoDados.getColunaId(), CreatBancoDados.getColunaCpf(),
                        CreatBancoDados.getColunaNome(), CreatBancoDados.getColunaEmail(), CreatBancoDados.getColunaSenha()}, CreatBancoDados.getColunaCpf() + " = ?",
                new String[]{String.valueOf(cpf)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Pessoa pessoa = new Pessoa(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4));
            cursor.close();
            db.close();
            return pessoa;
        } else {
            db.close();
            return null;
        }

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
                    livro.setIsbn(Integer.parseInt(cursor.getString(1)));
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
    //Obter pessoa pelo cpf

    public Livro getLivro(Integer isbn) {
        openDB();

        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaLivro(), new String[]{CreatBancoDados.getColunaIdLivro(),
                        CreatBancoDados.getColunaIsbn(), CreatBancoDados.getColunaNomeLivro(),
                        CreatBancoDados.getColunaQtdAlugado(), CreatBancoDados.getColunaAutor(),
                        CreatBancoDados.getColunaGenero(), CreatBancoDados.getColunaQtdTotal(),
                        CreatBancoDados.getColunaAno(), CreatBancoDados.getColunaNEdicao()},
                CreatBancoDados.getColunaIsbn() + " = ?",
                new String[]{String.valueOf(isbn)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Livro livro = new Livro(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8));
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
