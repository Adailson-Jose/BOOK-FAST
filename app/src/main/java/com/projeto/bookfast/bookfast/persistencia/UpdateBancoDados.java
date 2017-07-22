package com.projeto.bookfast.bookfast.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projeto.bookfast.bookfast.dominio.Pessoa;

/**
 * Created by oi on 18/07/2017.
 */
public class UpdateBancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "bd_biblioteca";
    private static final String PATH_DB = "/data/user/0/package com.projeto.bookfast.bookfast/databases/bd_biblioteca";
    private Context meuContext;
    private SQLiteDatabase db;

    public UpdateBancoDados(Context context) {
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

    public boolean insertPessoa(Pessoa pessoa) {
        openDB();
        try {
            ContentValues valores = new ContentValues();

            valores.put(CreatBancoDados.getColunaCpf(), pessoa.getCpf());
            valores.put(CreatBancoDados.getColunaNome(), pessoa.getNome());
            valores.put(CreatBancoDados.getColunaEmail(), pessoa.getEmail());
            valores.put(CreatBancoDados.getColunaSenha(), pessoa.getSenha());
            db.insert(CreatBancoDados.getNomeTabelaPessoa(), null, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean updatePessoa(Pessoa pessoa) {
        openDB();
        try {
            String where = "cpf = '" + Integer.toString(pessoa.getCpf()) + "'";
            ContentValues valores = new ContentValues();
            valores.put(CreatBancoDados.getColunaCpf(), pessoa.getCpf());
            valores.put(CreatBancoDados.getColunaNome(), pessoa.getNome());
            valores.put(CreatBancoDados.getColunaEmail(), pessoa.getEmail());
            valores.put(CreatBancoDados.getColunaSenha(), pessoa.getSenha());
            db.update(CreatBancoDados.getNomeTabelaPessoa(), valores, where, null);
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
