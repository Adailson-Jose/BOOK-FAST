package com.projeto.bookfast.bookfast.dominio.pessoa.negocio;

import android.widget.EditText;

import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;

/**
 * Created by oi on 03/08/2017.
 */

public class ValidarCampoEditaInformacaoUsuario {
    ValidarCampoVazio vazio = new ValidarCampoVazio();
    ValidarEmail emailValido = new ValidarEmail();

    public boolean ValidarCampoEditaInformacaoUsuario(EditText nome, EditText email) {
        String nomeString = nome.getText().toString();
        String emailString = email.getText().toString();
        boolean resultado = false;
        if (vazio.isCampoVazio(nomeString)) {
            resultado = true;
            nome.setError("Nome inválido!");
            nome.requestFocus();
        } else if (!emailValido.isEmailValido(emailString)) {
            resultado = true;
            email.setError("Email inválido!");
            email.requestFocus();
        }
        return resultado;
    }
}
