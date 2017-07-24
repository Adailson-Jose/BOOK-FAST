package com.projeto.bookfast.bookfast.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                    pessoa.setId(Integer.parseInt(cursor.getString(0)));
                    pessoa.setCpf(Integer.parseInt(cursor.getString(1)));
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
        Cursor cursor = db.query(CreatBancoDados.getNomeTabelaPessoa(), new String[]{CreatBancoDados.getColunaId(), CreatBancoDados.getColunaCpf(),
                        CreatBancoDados.getColunaNome(), CreatBancoDados.getColunaEmail(), CreatBancoDados.getColunaSenha()}, CreatBancoDados.getColunaCpf() + " = ?",
                new String[]{String.valueOf(cpf)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Pessoa pessoa = new Pessoa(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return pessoa;
    }

    private void openDB() {
        if (!db.isOpen()) {
            db = meuContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }

}
