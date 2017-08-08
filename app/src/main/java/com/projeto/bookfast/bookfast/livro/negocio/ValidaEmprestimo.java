package com.projeto.bookfast.bookfast.livro.negocio;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;


/**
 * Created by Leticia Quedma on 08/08/2017.
 */

public class ValidaEmprestimo {

    public void pediemprestimo(Livro livro, Pessoa pessoa){
        if(verDisponibilidade(livro)) {
            livro.setQtdTotal(livro.getQtdTotal() - 1);
        pessoa.setLivros(livro.getNome());
        }

    }

    public boolean verDisponibilidade(Livro livro) {
        return (livro.getQtdTotal() > 0)? true : false;
    }
}