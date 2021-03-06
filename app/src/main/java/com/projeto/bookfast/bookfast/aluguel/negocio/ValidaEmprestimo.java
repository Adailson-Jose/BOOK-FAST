package com.projeto.bookfast.bookfast.aluguel.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.UpdatePessoa;


/**
 * A classe ValidaEmprestimo, cria uma associação das classes AluguelDao e Context, verifica se o emprestimo é válido ou não.
 */

public class ValidaEmprestimo {
    private Context context;
    private AluguelDao aluguelDao;

    public ValidaEmprestimo(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
    }

    /**
     * o método pediEmprestimo recebe dois objetos um da classe livro e outro da classe Pessoa
     */
    public boolean pediEmprestimo(Livro livro, Pessoa pessoa) {
        boolean retorno = false;
        if (verDisponibilidadeLivro(livro) && verDisponibilidadePessoa(pessoa, livro)) {
            UpdateLivro atualizaLivro = new UpdateLivro(context);
            Aluguel aluguel = new Aluguel();
            UpdatePessoa atualizaPessoa = new UpdatePessoa(context);
            aluguel.setIdLivro(livro.getId());
            aluguel.setIdPessoa(pessoa.getId());
            aluguel.setDate(DataLivro.getDataAtual());
            aluguel.setDataEntrega(DataLivro.getDataDevolucao());
            aluguel.setMulta(0);
            aluguel.setStatus("1");
            aluguelDao.insertAluguel(aluguel);
            livro.setQtdAlugado(livro.getQtdAlugado() + 1);
            atualizaLivro.updateLivro(livro);
            aluguel = aluguelDao.getAluguel(pessoa.getId(), livro.getId());
            pessoa.setListaAluguel(pessoa.getListaAluguel() + " " + aluguel.getId());
            atualizaPessoa.updatePessoa(pessoa);
            retorno = true;
        }
        return retorno;
    }

    /**
     * verifica se o livro está liberado
     */
    public boolean verDisponibilidadeLivro(Livro livro) {
        return ((livro.getQtdTotal() - livro.getQtdAlugado()) > 0);
    }

    /** verifica se a pessoa tem disponilidade */
    public boolean verDisponibilidadePessoa(Pessoa pessoa, Livro livro) {
        boolean retorno = false;
        String[] idsAluguel = pessoa.getListaAluguel().trim().split(" ");
        int cont = 0;
        int duplicate = 0;
        String temp = String.valueOf(livro.getId());
        AluguelDao buscaAluguel = new AluguelDao(context);
        for (String idAluguel : idsAluguel) {
            if (idAluguel.trim().equals("")) {
                continue;
            }
            cont += 1;
            Aluguel temporario = buscaAluguel.getAluguel(Integer.parseInt(idAluguel));
            if (temp.equals(String.valueOf(temporario.getIdLivro()).trim()) || cont > 3) {
                duplicate = 1;
                break;
            }
        }
        if (cont < 3 && duplicate == 0) {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }
}