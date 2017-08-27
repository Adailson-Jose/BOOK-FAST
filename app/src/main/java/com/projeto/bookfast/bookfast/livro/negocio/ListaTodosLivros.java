package com.projeto.bookfast.bookfast.livro.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;

import java.util.ArrayList;

/**
 * a CLASSE ListaTodosLivros instancia um objeto da classe aluguelDao  e tem um atributo context.
 */

public class ListaTodosLivros {
    private Context context;
    private AluguelDao aluguelDao;

    /**
     * Construtor da CLASSE ListaTodosLivros.
     */
    public ListaTodosLivros(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
    }

    /**
     * a CLASSE ListaTodosLivros tem o método getListaLivros
     */
    public ArrayList<Livro> getListaLivros() {
        ReadLivro buscarLivro = new ReadLivro(context);
        return buscarLivro.getListaLivro();
    }

}
