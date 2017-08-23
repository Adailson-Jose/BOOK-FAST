package com.projeto.bookfast.bookfast.recomendacao.negocio;

import android.content.Context;

import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

import java.util.Map;

/**
 * Created by oi on 23/08/2017.
 */

public class SlopeOne {
    private Context context;
    private ReadLivro buscaMaiorIdLivro;
    private ReadPessoa buscaListaPessoa;
    int maxItemsId = 0;
    float mteste[][];
    int mFreq[][];
    Map<Integer, Map<Integer, Float>> usersMatrix;

    public SlopeOne(Context context) {
        this.context = context;
        buscaMaiorIdLivro = new ReadLivro(this.context);
        buscaListaPessoa = new ReadPessoa(this.context);
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        SlopeOne so = new SlopeOne();
        /* Estimates time */
        long end = System.currentTimeMillis();
        System.out.println("\nExecution time was " + (end - start) + " ms.");
    }

    public SlopeOne() {
        buildDiffMatrix();
    }

    /*
     * Function SlopeOne()
     * Calculates the DiffMatrix for all items
     *
     */
    public void buildDiffMatrix() {

        mteste = new float[maxItemsId + 1][maxItemsId + 1];
        mFreq = new int[maxItemsId + 1][maxItemsId + 1];

        for (int i = 1; i <= maxItemsId; i++)
            for (int j = 1; j <= maxItemsId; j++) {
                mteste[i][j] = 0;
                mFreq[i][j] = 0;
            }

        /* Iterate through all users, and then, through all items do calculate the diffs */
        for (int cUser : usersMatrix.keySet()) {
            for (int i : usersMatrix.get(cUser).keySet()) {
                for (int j : usersMatrix.get(cUser).keySet()) {
                    mteste[i][j] = mteste[i][j] +
                            (usersMatrix.get(cUser).get(i).floatValue() - (usersMatrix.get(cUser).get(j).floatValue()));
                    mFreq[i][j] = mFreq[i][j] + 1;
                }
            }
        }

        /*  Calculate the averages (diff/freqs) */
        for (int i = 1; i <= maxItemsId; i++) {
            for (int j = i; j <= maxItemsId; j++) {
                if (mFreq[i][j] > 0) {
                    mteste[i][j] = mteste[i][j] / mFreq[i][j];
                }
            }
        }
    }
}
