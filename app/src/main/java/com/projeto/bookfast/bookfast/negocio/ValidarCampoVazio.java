package com.projeto.bookfast.bookfast.negocio;

import android.text.TextUtils;

/**
 * classe ValidarCampoVazio retorna um booleano se o campo está vazio ou não.
 */

public class ValidarCampoVazio {
    public ValidarCampoVazio() {
    }

    public static boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor.trim()));
        return resultado;
    }
}
