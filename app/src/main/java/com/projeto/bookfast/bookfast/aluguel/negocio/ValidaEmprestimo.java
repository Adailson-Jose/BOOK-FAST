package com.projeto.bookfast.bookfast.aluguel.negocio;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;


/**
 * Created by Leticia Quedma on 08/08/2017.
 */

public class ValidaEmprestimo {

    public static boolean pediEmprestimo(Livro livro, Pessoa pessoa) {
        if (verDisponibilidadeLivro(livro) && verDisponibilidadePessoa(pessoa)) {
            livro.setQtdTotal(livro.getQtdTotal() - 1);
            //Pessoa pessoa = new Pessoa();
            //pessoa.setLivros(livro.getNome());
        }
        return false;
    }

    public static boolean verDisponibilidadeLivro(Livro livro) {
        return (livro.getQtdTotal() > 0)? true : false;
    }

    public static boolean verDisponibilidadePessoa(Pessoa pessoa) {
        return false;
    }

    public static boolean devolveLivro(Livro livro, Pessoa pessoa) {
        return false;
    }



}