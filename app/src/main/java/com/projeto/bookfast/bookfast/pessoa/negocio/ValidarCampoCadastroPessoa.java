package com.projeto.bookfast.bookfast.pessoa.negocio;

import android.widget.EditText;

import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarCpf;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;

public class ValidarCampoCadastroPessoa {
    ValidarCampoVazio vazio = new ValidarCampoVazio();
    ValidarEmail emailValido = new ValidarEmail();

    public boolean vefificaCadastroUsuario(EditText cpf, EditText nome, EditText email, EditText senha) {
        String cpfString = cpf.getText().toString();
        String nomeString = nome.getText().toString();
        String emailString = email.getText().toString();
        String senhaString = senha.getText().toString();
        boolean resultado = false;
        if (!ValidarCpf.validarCpf(cpfString)) {
            resultado = true;
            cpf.setError("CPF inváldo!");
            cpf.requestFocus();
        } else if (vazio.isCampoVazio(nomeString)) {
            resultado = true;
            nome.setError("Nome inválido!");
            nome.requestFocus();
        } else if (!emailValido.isEmailValido(emailString)) {
            resultado = true;
            email.setError("Email inválido!");
            email.requestFocus();
        } else if (vazio.isCampoVazio(senhaString)) {
            resultado = true;
            senha.setError("Senha inválida!");
            senha.requestFocus();
        }
        return resultado;
    }
}
