package com.projeto.bookfast.bookfast.recomendacao.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.recomendacao.dominio.Avaliacao;
import com.projeto.bookfast.bookfast.recomendacao.persistencia.AvaliacaoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oi on 24/08/2017.
 */

public class SlopeOne {
    private Context context;
    private AvaliacaoDao buscaAvaliacao;
    private ReadPessoa buscaPessoa;
    private ReadLivro buscaLivro;

    private Map<Integer, Map<Integer, Double>> matrizDiferenca;
    private Map<Integer, Map<Integer, Integer>> matrizFrequencia;
    private Map<Integer, Map<Integer, Double>> data = new HashMap<Integer, Map<Integer, Double>>();

    private static List<Livro> todosLivros = new ArrayList<>();
    private static List<Pessoa> todosUsuarios = new ArrayList<>();
    private static List<Avaliacao> produtosClassificados = new ArrayList<>();
    private static List<Livro> produtosRecomendadosOrdenados;

    public SlopeOne(Context context) {
        buscaLivro = new ReadLivro(context);
        buscaPessoa = new ReadPessoa(context);
        buscaAvaliacao = new AvaliacaoDao(context);
    }

    public void leituraDados() {
        /** Início - Simulando a leitura dos dados do sistema para o cálculo das recomendações */
        todosLivros = buscaLivro.getListaLivro();
        todosUsuarios = buscaPessoa.getListaPessoas();
        //Criação da lista de notas dadas pelos usuários aos produtos
        for (Pessoa usuario : todosUsuarios) {
            HashMap<Integer, Double> notasUsuario = new HashMap<Integer, Double>();
            //Lista de Objetos do Domínio RecomendacaoLivro
            produtosClassificados = buscaAvaliacao.getListaAvaliacaoPessoa(usuario.getId());
            for (Avaliacao recomendacaoProduto : produtosClassificados) {
                notasUsuario.put(recomendacaoProduto.getIdLivro(), recomendacaoProduto.getAvaliacao());
            }
            data.put(usuario.getId(), notasUsuario);
        }
    }

    public void criarMatrizDiferenca(Map<Integer, Map<Integer, Double>> data) {
        matrizDiferenca = new HashMap<Integer, Map<Integer, Double>>();
        matrizFrequencia = new HashMap<Integer, Map<Integer, Integer>>();
        // first iterate through users
        for (Map<Integer, Double> user : data.values()) {
            // then iterate through user data
            for (Map.Entry<Integer, Double> entry : user.entrySet()) {
                if (!matrizDiferenca.containsKey(entry.getKey())) {
                    matrizDiferenca.put(entry.getKey(), new HashMap<Integer, Double>());
                    matrizFrequencia.put(entry.getKey(), new HashMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Double> entry2 : user.entrySet()) {
                    int oldcount = 0;
                    if (matrizFrequencia.get(entry.getKey()).containsKey(entry2.getKey()))
                        oldcount = matrizFrequencia.get(entry.getKey()).get(entry2.getKey()).intValue();
                    Double olddiff = 0.0;
                    if (matrizDiferenca.get(entry.getKey()).containsKey(entry2.getKey()))
                        olddiff = matrizDiferenca.get(entry.getKey()).get(entry2.getKey()).doubleValue();
                    Double observeddiff = entry.getValue() - entry2.getValue();
                    matrizFrequencia.get(entry.getKey()).put(entry2.getKey(), oldcount + 1);
                    matrizDiferenca.get(entry.getKey()).put(entry2.getKey(), olddiff + observeddiff);
                }
            }
        }
        for (Integer j : matrizDiferenca.keySet()) {
            for (Integer i : matrizDiferenca.get(j).keySet()) {
                Double oldvalue = matrizDiferenca.get(j).get(i).doubleValue();
                int count = matrizFrequencia.get(j).get(i).intValue();
                matrizDiferenca.get(j).put(i, oldvalue / count);
            }
        }
    }

    public Map<Integer, Double> predict(Map<Integer, Double> notasUsuario) {
        HashMap<Integer, Double> predictions = new HashMap<Integer, Double>();
        HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        for (Integer j : matrizDiferenca.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Integer j : notasUsuario.keySet()) {
            for (Integer k : matrizDiferenca.keySet()) {
                try {
                    Double novoValor = (matrizDiferenca.get(k).get(j).doubleValue() + notasUsuario.get(j).doubleValue()) * matrizFrequencia.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + novoValor);
                    frequencies.put(k, frequencies.get(k) + matrizFrequencia.get(k).get(j).intValue());
                } catch (NullPointerException e) {
                }
            }
        }
        HashMap<Integer, Double> cleanpredictions = new HashMap<Integer, Double>();
        for (Integer j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j).doubleValue() / frequencies.get(j).intValue());
            }
        }
        for (Integer j : notasUsuario.keySet()) {
            cleanpredictions.put(j, notasUsuario.get(j));
        }
        return cleanpredictions;
    }

    private List<Livro> calculaRecomendacoes(Map<Integer, Map<Integer, Double>> data, Pessoa usuarioLogado) {
        criarMatrizDiferenca(data);

        /*Set<Integer> listIdOrdenados = predict(data.get(usuarioLogado.getId()),usuarioLogado);

        List<Livro> produtosRecomendadosOrdenados = new ArrayList<>();
        for (Integer i : listIdOrdenados ) {
            Livro produtoClassificado = buscaLivro.getLivro(i);
            produtosRecomendadosOrdenados.add(produtoClassificado);
        }
        return produtosRecomendadosOrdenados;*/
        return null;
    }
}
