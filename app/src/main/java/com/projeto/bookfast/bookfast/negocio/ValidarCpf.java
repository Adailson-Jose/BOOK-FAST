package com.projeto.bookfast.bookfast.negocio;

/**
 * Created by oi on 31/07/2017.
 */

public class ValidarCpf {
    public ValidarCpf() {
    }

    public static boolean validarCpf(String CPF) {
        if (CPF.length() > 11 || CPF.length() < 11 || CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }
        char digito10, digito11;
        int soma, i, resto, num, peso;
        try {
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }
            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11))
                digito10 = '0';
            else
                digito10 = (char) (resto + 48);
            soma = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }
            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11))
                digito11 = '0';
            else
                digito11 = (char) (resto + 48);
            if ((digito10 == CPF.charAt(9)) && (digito11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (Exception erro) {
            return (false);
        }
    }
}

