package com.projeto.bookfast.bookfast.negocio;

/**
 * Created by CIRANDA DA CIÊNCIA on 02/08/2017.
 */

public class ValidarIsbn {
    ValidarCampoVazio vazio = new ValidarCampoVazio();

    public ValidarIsbn() {
    }

    public boolean validarIsbn(String isbn) {
        if (isbn.length() != 13 || vazio.isCampoVazio(isbn)) {
            return false;
        }
        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }
}

