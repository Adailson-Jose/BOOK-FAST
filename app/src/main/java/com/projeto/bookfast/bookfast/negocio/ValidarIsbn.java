package com.projeto.bookfast.bookfast.negocio;

/**
 * Created by CIRANDA DA CIÊNCIA on 02/08/2017.
 */

public class ValidarIsbn {
    ValidarCampoVazio vazio = new ValidarCampoVazio();

    public ValidarIsbn() {
    }

    public boolean validarIsbn(String isbn) {
        if (isbn.length() < 13 || isbn.length() > 13 || vazio.isCampoVazio(isbn)) {
            return true;

        } else {
            return false;
        }
    }
}

