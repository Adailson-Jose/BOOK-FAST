package com.projeto.bookfast.bookfast.pessoa.negocio;

import android.widget.EditText;

import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarCpf;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;

/**
 * Created by Adailson on 26/07/2017.
 */

public class ValidarCampoRecuperarSenhaPessoa {
    ValidarCampoVazio vazio = new ValidarCampoVazio();
    ValidarEmail emailValido = new ValidarEmail();

    public boolean valdarCampoRecuperarSenha(EditText cpf, EditText email, EditText senha){
        String cpfStr = cpf.getText().toString();
        String emailStr = email.getText().toString();
        String senhaStr = senha.getText().toString();
        boolean resultado = false;
        if (!ValidarCpf.validarCpf(cpfStr)) {
            resultado = true;
            cpf.setError("Campo CPF inválido!");
            cpf.requestFocus();
        } else if (!emailValido.isEmailValido(emailStr)) {
            resultado = true;
            email.setError("Campo email inválido!");

            email.requestFocus();
        } else if (vazio.isCampoVazio(senhaStr)) {
            resultado = true;
            senha.setError("Campo senha inválido!");
            senha.requestFocus();
        }
        return resultado;
    }
}
