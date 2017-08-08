package com.projeto.bookfast.bookfast.dominio.livro.negocio;

import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;

/**
 * Created by Leticia Quedma on 08/08/2017.
 */

public class ValidaEmprestimo {
    //  Livro livro;

    public boolean VerDisponibilidade(Livro livro) {
        return (livro.getQtdTotal() > 0)? true : false;
    }
}