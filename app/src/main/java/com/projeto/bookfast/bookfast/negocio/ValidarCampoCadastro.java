package com.projeto.bookfast.bookfast.negocio;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class ValidarCampoCadastro {

    public boolean vefificaCadastroUsuario(EditText cpf, EditText nome, EditText email, EditText senha) {
        String cpfString = cpf.getText().toString();
        String nomeString = nome.getText().toString();
        String emailString = email.getText().toString();
        String senhaString = senha.getText().toString();;
        boolean resultado = false;
        if (!ValidarCpf.validarCpf(cpfString)) {
            resultado = true;
            cpf.setError("CPF inváldo!");
            cpf.requestFocus();
        }else if(isCampoVazio(nomeString)){
            resultado = true;
            nome.setError("Nome inválido!");
            nome.requestFocus();
        }else if(!isEmailValido(emailString)){
            resultado = true;
            email.setError("Email inválido!");
            email.requestFocus();
        }else if(isCampoVazio(senhaString)) {
            resultado = true;
            senha.setError("Senha inválida!");
            senha.requestFocus();
        }
        if (resultado) {
            return true;
        }else{
            return false;
        }
    }

    public boolean vefificaCadastroLivro(EditText isbn, EditText nome, EditText qtdAlugado, EditText autor, EditText genero, EditText qtTotal, EditText ano, EditText numEdicao) {
        String Isbn = isbn.getText().toString();
        String Nome = nome.getText().toString();
        String QtAlugado = qtdAlugado.getText().toString();
        String Autor = autor.getText().toString();
        String Genero = genero.getText().toString();
        String QtTotal = qtTotal.getText().toString();
        String Ano = ano.getText().toString();
        String NumEdicao = numEdicao.getText().toString();
        boolean resultado = false;
        if (isCampoVazio(Isbn)){
            resultado = true;
            isbn.setError("Campo ISBN inválido!");
            isbn.requestFocus();
        }else if(isCampoVazio(Nome)){
            resultado = true;
            nome.setError("Campo Nome inválido!");
            nome.requestFocus();
        }else if(isCampoVazio(QtAlugado)){
            resultado = true;
            qtdAlugado.setError("Campo quantidade de livros alugados inválido!");
            qtdAlugado.requestFocus();
        }else if(isCampoVazio(Autor)) {
            resultado = true;
            autor.setError("Campo Nome autor inválido!");
            autor.requestFocus();
        }else if(isCampoVazio(Genero)) {
            resultado = true;
            genero.setError("Campo Gênero inválido!");
            genero.requestFocus();
        }else if(isCampoVazio(QtTotal)) {
            resultado = true;
            qtTotal.setError("Campo Quantidade total inválido!");
            qtTotal.requestFocus();
        }else if(isCampoVazio(Ano)) {
            resultado = true;
            ano.setError("Campo Ano inválido!");
            ano.requestFocus();
        }else if(isCampoVazio(NumEdicao)) {
            resultado = true;
            numEdicao.setError("Campo Número da edição inválido!");
            numEdicao.requestFocus();
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
