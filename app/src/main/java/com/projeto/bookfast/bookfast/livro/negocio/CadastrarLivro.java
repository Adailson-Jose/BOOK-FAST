package com.projeto.bookfast.bookfast.livro.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.livro.persistencia.UpdateLivro;

/**
 * Created by oi on 21/08/2017.
 */

public class CadastrarLivro {
    private Context context;
    private ReadLivro buscarLivro;
    private UpdateLivro insertLivro;

    public CadastrarLivro(Context context) {
        this.context = context;
        buscarLivro = new ReadLivro(this.context);
        insertLivro = new UpdateLivro(this.context);
    }

    public boolean cadastrarLivro(long isbn, String nome, int quanitdadeAlugada, String autor, String genero, int quantidadeTotal, int ano, int edicao, byte[] imagemBytes) {
        Livro livro = buscarLivro.getLivro(isbn);
        boolean resultado = false;
        if (livro != null) {
            resultado = false;
        } else {
            Livro livro2 = new Livro(isbn, nome, quanitdadeAlugada, autor, genero, quantidadeTotal, ano, edicao, imagemBytes);
            resultado = insertLivro.insertLivro(livro2);
        }
        return resultado;
    }
}
