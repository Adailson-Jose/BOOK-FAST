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
        if (isCampoVazio(cpfString)){
            resultado = true;
            cpf.requestFocus();
        }else if(isCampoVazio(nomeString)){
            resultado = true;
            nome.requestFocus();
        }else if(!isEmailValido(emailString)){
            resultado = true;
            email.requestFocus();
        }else if(isCampoVazio(senhaString)) {
            resultado = true;
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
            isbn.requestFocus();
        }else if(isCampoVazio(Nome)){
            resultado = true;
            nome.requestFocus();
        }else if(isCampoVazio(QtAlugado)){
            resultado = true;
            qtdAlugado.requestFocus();
        }else if(isCampoVazio(Autor)) {
            resultado = true;
            autor.requestFocus();
        }else if(isCampoVazio(Genero)) {
            resultado = true;
            genero.requestFocus();
        }else if(isCampoVazio(QtTotal)) {
            resultado = true;
            qtTotal.requestFocus();
        }else if(isCampoVazio(Ano)) {
            resultado = true;
            ano.requestFocus();
        }else if(isCampoVazio(NumEdicao)) {
            resultado = true;
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