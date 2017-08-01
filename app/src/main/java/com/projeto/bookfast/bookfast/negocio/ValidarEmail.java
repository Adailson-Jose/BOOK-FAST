package com.projeto.bookfast.bookfast.negocio;

import android.util.Patterns;

/**
 * Created by oi on 01/08/2017.
 */

public class ValidarEmail {
    ValidarCampoVazio vazio = new ValidarCampoVazio();

    public ValidarEmail() {
    }

    public boolean isEmailValido(String email) {
        boolean resultado = (!vazio.isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

}
