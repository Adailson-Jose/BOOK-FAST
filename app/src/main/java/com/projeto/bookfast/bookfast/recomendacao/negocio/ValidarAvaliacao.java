package com.projeto.bookfast.bookfast.recomendacao.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.recomendacao.dominio.Avaliacao;
import com.projeto.bookfast.bookfast.recomendacao.persistencia.AvaliacaoDao;

/**
 * Created by oi on 24/08/2017.
 */

public class ValidarAvaliacao {

    private Context context;
    private AvaliacaoDao avaliacaoDao;
    private ReadLivro buscaLivro;
    private ReadPessoa buscaPessoa;

    public ValidarAvaliacao(Context context) {
        this.context = context;
        avaliacaoDao = new AvaliacaoDao(this.context);
        buscaLivro = new ReadLivro(this.context);
        buscaPessoa = new ReadPessoa(this.context);
    }

    public boolean validaAvaliacao(String avaliacao, Pessoa pessoa, Livro livro) {
        boolean retorno = false;
        Double avaliacaoInt = 0.0;
        if (avaliacao.equals("Ruim")) {
            avaliacaoInt = 1.0;
        } else if (avaliacao.equals("Médio")) {
            avaliacaoInt = 2.0;
        } else if (avaliacao.equals("Bom")) {
            avaliacaoInt = 3.0;
        } else if (avaliacao.equals("Ótimo")) {
            avaliacaoInt = 4.0;
        }
        if (avaliacaoDao.getAvaliacaoIdPessoIdLivro(pessoa.getId(), livro.getId()) != null) {
            retorno = false;// Pessoa já avalior antes esse livro.
        } else {
            Avaliacao avaliacao2 = new Avaliacao(pessoa.getId(), livro.getId(), avaliacaoInt);
            if (avaliacaoDao.insertAvaliacaoDao(avaliacao2)) {
                retorno = true;
            }
        }
        return retorno;
    }
}