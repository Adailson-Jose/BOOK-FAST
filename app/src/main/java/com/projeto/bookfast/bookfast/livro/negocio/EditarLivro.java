package com.projeto.bookfast.bookfast.livro.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.livro.persistencia.UpdateLivro;

/**
 * Created by oi on 21/08/2017.
 */

public class EditarLivro {
    private Context context;
    private ReadLivro buscarLivro;
    private UpdateLivro updateLivro;

    public EditarLivro(Context context) {
        this.context = context;
        buscarLivro = new ReadLivro(this.context);
        updateLivro = new UpdateLivro(this.context);
    }

    public boolean editarLivro(long isbn, String nome, int quanitdadeAlugada, String autor, String genero, int quantidadeTotal, int ano, int edicao, byte[] foto) {
        Livro livro = buscarLivro.getLivro(isbn);
        if (livro != null) {
            livro.setIsbn(isbn);
            livro.setNumEdicao(edicao);
            livro.setAno(ano);
            livro.setQtdTotal(quantidadeTotal);
            livro.setQtdAlugado(quanitdadeAlugada);
            livro.setNome(nome);
            livro.setGenero(genero);
            livro.setAutor(autor);
            livro.setFotoLivro(foto);
            updateLivro.updateLivro(livro);
            return true;
        } else {
            return false;
        }
    }
}
