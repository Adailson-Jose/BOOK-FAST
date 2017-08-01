package com.projeto.bookfast.bookfast.dominio.pessoa.negocio;

import android.widget.EditText;

import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarCpf;

/**
 * Created by oi on 27/07/2017.
 */

public class ValidarCampoLogin {
    ValidarCampoVazio vazio = new ValidarCampoVazio();
    public boolean ValidarCampoLogin(EditText  cpf, EditText senha){
        String cpfString = cpf.getText().toString();
        String senhaStr = senha.getText().toString();
        boolean resultado = false;
        if (!ValidarCpf.validarCpf(cpfString)) {
            resultado = true;
            cpf.setError("Campo CPF inválido!");
            cpf.requestFocus();
        } else if (vazio.isCampoVazio(senhaStr)) {
            resultado = true;
            senha.setError("Campo senha inválido!");
            senha.requestFocus();
        }
        if (resultado) {

            return true;
        }else{
            return false;
        }
    }
}


