package com.projeto.bookfast.bookfast.negocio;

import android.widget.EditText;

/**
 * Created by oi on 27/07/2017.
 */

public class ValidarLogin {
    String senha = "2012";
    String login =" 1234567890";

    public boolean validarLogin (EditText usuarioCPF, EditText editSenha) {
        if (usuarioCPF.equals(login) && editSenha.equals(senha)) {
            return true;
        }
        else {
            return false;
        }
    }

}
