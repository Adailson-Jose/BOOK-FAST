package com.projeto.bookfast.bookfast.negocio;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by oi on 27/07/2017.
 */

public class ValidarCampoLogin {

    public boolean ValidarCampoLogin(EditText  cpf, EditText senha){
        String cpfStr = cpf.getText().toString();
        String senhaStr = senha.getText().toString();
        boolean resultado = false;
        if (isCampoVazio(cpfStr)){
            resultado = true;
            cpf.requestFocus();
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


    public boolean isCampoVazio (String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }
}


