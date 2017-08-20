package com.projeto.bookfast.bookfast.aluguel.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.persistecia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.percistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.UpdatePessoa;


/**
 * Created by Leticia Quedma on 08/08/2017.
 */

public class ValidaEmprestimo {
    private Context context;
    private AluguelDao aluguelDao;

    public ValidaEmprestimo(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
    }

    public boolean pediEmprestimo(Livro livro, Pessoa pessoa) {
        boolean retorno = false;
        if (verDisponibilidadeLivro(livro) && verDisponibilidadePessoa(pessoa)) {
            UpdateLivro atualizaLivro = new UpdateLivro(context);
            Aluguel aluguel = new Aluguel();
            UpdatePessoa atualizaPessoa = new UpdatePessoa(context);
            aluguel.setIdLivro(livro.getId());
            aluguel.setIdPessoa(pessoa.getId());
            aluguel.setDate("10/10/2000");
            aluguel.setDataEntrega("10/11/3000");
            aluguel.setMulta(0);
            aluguelDao.insertAluguel(aluguel);
            livro.setQtdTotal(livro.getQtdTotal() - 1);
            atualizaLivro.updateLivro(livro);
            aluguel = aluguelDao.getAluguelPessoa(pessoa.getId());
            pessoa.setListaAluguel(pessoa.getListaAluguel() + " " + aluguel.getId());
            atualizaPessoa.updatePessoa(pessoa);
            retorno = true;
        }
        return retorno;
    }

    public boolean verDisponibilidadeLivro(Livro livro) {
        return (livro.getQtdTotal() > 0);
    }

    public boolean verDisponibilidadePessoa(Pessoa pessoa) {
        boolean retorno = false;
        String[] ids = pessoa.getListaAluguel().trim().split(" ");
        int cont = 0;
        for (String idLivro : ids) {
            if (idLivro.equals("")) {
                //
            } else {
                cont += 1;
            }
        }
        if (pessoa.getListaAluguel().trim().equals("") || cont < 3) {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }
    public static boolean devolveLivro(Livro livro, Pessoa pessoa) {

        return false;
    }



}