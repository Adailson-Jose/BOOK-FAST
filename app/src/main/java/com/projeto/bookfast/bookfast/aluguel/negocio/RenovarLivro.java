package com.projeto.bookfast.bookfast.aluguel.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;

/**
 * classe RenovarLivro cria uma associação de Context e outra de AluguelDao.
 */

public class RenovarLivro {
    private Context context;
    private AluguelDao aluguelDao;

    /**
     * Construtor da classe RenovarLivro
     */
    public RenovarLivro(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
    }

    public boolean renovarLivro(Livro livro, Pessoa pessoa) {
        Aluguel aluguel = null;
        String[] idsAluguel = pessoa.getListaAluguel().trim().split(" ");
        String temp = String.valueOf(livro.getId());
        String temp2 = "0";
        for (String idAluguel : idsAluguel) {
            if (idAluguel.trim().equals("")) {
                continue;
            }
            Aluguel temporario = aluguelDao.getAluguel(Integer.parseInt(idAluguel));
            if (temp.equals(String.valueOf(temporario.getIdLivro()))) {
                aluguel = temporario;
                temp2 = "1";
            }
        }
        if (temp2.equals("1")) {
            aluguel.setDate(DataLivro.getDataAtual());
            aluguel.setDataEntrega(DataLivro.getDataDevolucao());
            aluguelDao.updateAluguel(aluguel);
            return true;
        } else {
            return false;
        }
    }
}
