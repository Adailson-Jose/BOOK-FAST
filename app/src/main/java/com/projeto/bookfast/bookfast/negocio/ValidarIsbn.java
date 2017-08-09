package com.projeto.bookfast.bookfast.negocio;

/**
 * Created by CIRANDA DA CIÊNCIA on 02/08/2017.
 */

public class ValidarIsbn {

    public ValidarIsbn() {
    }

    public static boolean validarIsbn(String isbn) {
        if (isbn.length() < 13 || isbn.length() > 13 || ValidarCampoVazio.isCampoVazio(isbn)) {
            return true;

        } else {
            int treze = 0;
            int tot = 0;
            for (int i = 0; i < 13; i++) {
                if (i < 13) {
                    int digit = Integer.parseInt(isbn.substring(i, i + 1));
                    tot += (i % 2 == 0) ? digit * 1 : digit * 3;
                } else {
                    treze = i;
                }
            }
            int checksum = 10 - (tot % 10);
            checksum = checksum % 10;
            if (checksum == treze) {
                return false;
            } else {
                return true;
            }

        }
    }
}

