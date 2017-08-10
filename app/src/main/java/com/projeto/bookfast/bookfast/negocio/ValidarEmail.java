package com.projeto.bookfast.bookfast.negocio;

import android.util.Patterns;

/**
 * Created by oi on 01/08/2017.
 */

public class ValidarEmail {

    public ValidarEmail() {
    }

    public static boolean isEmailValido(String email) {
        boolean resultado = (!ValidarCampoVazio.isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

}
