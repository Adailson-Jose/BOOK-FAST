package com.projeto.bookfast.bookfast.negocio;

import android.widget.EditText;

/**
 * Created by oi on 27/07/2017.
 */

public class ValidarCampoLogin {

    public boolean validarLogin (EditText usuarioCPF, EditText editSenha) {
        String senhaString = String.valueOf(editSenha);
        String cpfString = String.valueOf(usuarioCPF);

        if (cpfString.trim().isEmpty() || senhaString.trim().isEmpty()) {
            return false;
        } else
            return true;
    }
}


