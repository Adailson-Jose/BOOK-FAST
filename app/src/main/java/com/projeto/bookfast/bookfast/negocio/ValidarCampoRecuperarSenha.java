package com.projeto.bookfast.bookfast.negocio;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by Adailson on 26/07/2017.
 */

public class ValidarCampoRecuperarSenha {
    public boolean valdarCampoRecuperarSenha(EditText cpf, EditText email, EditText senha){
        String cpfStr = cpf.getText().toString();
        String emailStr = email.getText().toString();
        String senhaStr = senha.getText().toString();
        boolean resultado = false;
        if (isCampoVazio(cpfStr)){
            resultado = true;
            cpf.requestFocus();
        }else if(!isEmailValido(emailStr)){
            resultado = true;
            email.requestFocus();
        }else if(isCampoVazio(senhaStr)){
            resultado = true;
            senha.requestFocus();
        }
        if (resultado) {
            return true;
        }else{
            return false;
        }
    }

    public boolean isEmailValido(String email) {
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    public boolean isCampoVazio (String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

}
