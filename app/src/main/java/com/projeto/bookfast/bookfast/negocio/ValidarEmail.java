package com.projeto.bookfast.bookfast.negocio;

import android.util.Patterns;

/**
 * Classe ValidarEmail é uma classe que verifica se o email digitado é válido e o campo é não vazio.
 */

public class ValidarEmail {

    public ValidarEmail() {
    }

    public static boolean isEmailValido(String email) {
        boolean resultado = (!ValidarCampoVazio.isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

}
