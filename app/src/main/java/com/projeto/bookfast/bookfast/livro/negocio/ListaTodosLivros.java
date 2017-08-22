package com.projeto.bookfast.bookfast.livro.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;

import java.util.ArrayList;

/**
 * Created by oi on 21/08/2017.
 */

public class ListaTodosLivros {
    private Context context;
    private AluguelDao aluguelDao;

    public ListaTodosLivros(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
    }

    public ArrayList<Livro> getListaLivros() {
        ReadLivro buscarLivro = new ReadLivro(context);
        return buscarLivro.getListaLivro();
    }

}
