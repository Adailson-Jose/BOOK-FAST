package com.projeto.bookfast.bookfast.recomendacao.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oi on 23/08/2017.
 */

public class BuildDiffMatrix {
    private Context context;
    private AluguelDao aluguelDao;
    private ReadPessoa readPessoa;
    int maxItemsId = 0;
    float mRatings[][];
    int mFreq[][];
    Map<Long, Map<Integer, Float>> usersMatrix;
    ArrayList<Long> listaCpf;
    ArrayList<Aluguel> listaAluguel;

    public BuildDiffMatrix(Context context) {
        this.context = context;
        aluguelDao = new AluguelDao(this.context);
        readPessoa = new ReadPessoa(this.context);
        listaAluguel = aluguelDao.getListaIdAluguel();
        listaCpf = readPessoa.getListaCpfPessoas();
        maxItemsId = aluguelDao.getMaiorId();
        usersMatrix = new HashMap<Long, Map<Integer, Float>>();
    }

    public void povoaMap() {
        for (Long cpf : listaCpf) {
            usersMatrix.put(cpf, new HashMap<Integer, Float>());

        }
    }

    /*public static void main(String args[]) {
        long start = System.currentTimeMillis();
        BuildDiffMatrix so = new BuildDiffMatrix();
        //Estimates time
        long end = System.currentTimeMillis();
        System.out.println("\nExecution time was " + (end - start) + " ms.");
    }*/

    /*
     * Function BuildDiffMatrix()
     * Calculates the DiffMatrix for all items
     *
     */
    public void buildDiffMatrix() {

        mRatings = new float[maxItemsId + 1][maxItemsId + 1];
        mFreq = new int[maxItemsId + 1][maxItemsId + 1];

        for (int i = 1; i <= maxItemsId; i++)
            for (int j = 1; j <= maxItemsId; j++) {
                mRatings[i][j] = 0;
                mFreq[i][j] = 0;
            }

        /* Iterate through all users, and then, through all items do calculate the diffs */
        for (Long cUser : usersMatrix.keySet()) { // lista de Pessoa
            for (int i : usersMatrix.get(cUser).keySet()) { //  lista de id do aluguel
                for (int j : usersMatrix.get(cUser).keySet()) {// lista de id do aluguel
                    mRatings[i][j] = mRatings[i][j] + (usersMatrix.get(cUser).get(i).floatValue() - (usersMatrix.get(cUser).get(j).floatValue()));
                    mFreq[i][j] = mFreq[i][j] + 1;
                }
            }
        }

        /*  Calculate the averages (diff/freqs) */
        for (int i = 1; i <= maxItemsId; i++) {
            for (int j = i; j <= maxItemsId; j++) {
                if (mFreq[i][j] > 0) {
                    mRatings[i][j] = mRatings[i][j] / mFreq[i][j];
                }
            }
        }
    }
}
