package com.projeto.bookfast.bookfast.dominio.livro.percistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;

/**
 * Created by oi on 01/08/2017.
 */

public class UpdateLivro extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = CreatBancoDados.getNomeBanco();
    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/" + CreatBancoDados.getNomeBanco();
    private Context meuContext;
    private SQLiteDatabase db;

    public UpdateLivro(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.meuContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // logica pra atualizar db
    }

    public boolean insertLivro(Livro livro) {
        openDB();
        try {
            ContentValues valores = new ContentValues();
            valores.put(CreatBancoDados.getColunaIsbn(), livro.getIsbn());
            valores.put(CreatBancoDados.getColunaAno(), livro.getAno());
            valores.put(CreatBancoDados.getColunaAutor(), livro.getAutor());
            valores.put(CreatBancoDados.getColunaGenero(), livro.getGenero());
            valores.put(CreatBancoDados.getColunaNomeLivro(), livro.getNome());
            valores.put(CreatBancoDados.getColunaNEdicao(), livro.getNumEdicao());
            valores.put(CreatBancoDados.getColunaQtdAlugado(), livro.getQtdAlugado());
            valores.put(CreatBancoDados.getColunaQtdTotal(), livro.getQtdTotal());
            db.insert(CreatBancoDados.getNomeTabelaLivro(), null, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean updateLivro(Livro livro) {
        openDB();
        try {
            String where = "isbn = '" + Long.toString(livro.getIsbn()) + "'";
            ContentValues valores = new ContentValues();
            valores.put(CreatBancoDados.getColunaIsbn(), livro.getIsbn());
            valores.put(CreatBancoDados.getColunaAno(), livro.getAno());
            valores.put(CreatBancoDados.getColunaAutor(), livro.getAutor());
            valores.put(CreatBancoDados.getColunaGenero(), livro.getGenero());
            valores.put(CreatBancoDados.getColunaNomeLivro(), livro.getNome());
            valores.put(CreatBancoDados.getColunaNEdicao(), livro.getNumEdicao());
            valores.put(CreatBancoDados.getColunaQtdAlugado(), livro.getQtdAlugado());
            valores.put(CreatBancoDados.getColunaQtdTotal(), livro.getQtdTotal());
            db.update(CreatBancoDados.getNomeTabelaLivro(), valores, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    private void openDB() {
        if (!db.isOpen()) {
            db = meuContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }
}