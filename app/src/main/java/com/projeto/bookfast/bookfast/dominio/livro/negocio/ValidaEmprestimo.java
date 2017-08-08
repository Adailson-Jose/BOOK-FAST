package com.projeto.bookfast.bookfast.dominio.livro.negocio;

import android.widget.Toast;

import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.gui.TelaInicialUsuarioComum;


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