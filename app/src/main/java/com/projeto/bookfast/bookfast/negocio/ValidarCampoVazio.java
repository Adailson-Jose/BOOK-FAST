package com.projeto.bookfast.bookfast.negocio;

import android.text.TextUtils;

/**
 * Created by oi on 01/08/2017.
 */

public class ValidarCampoVazio {
    public ValidarCampoVazio() {
    }

    public boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor.trim()));
        return resultado;
    }
}
