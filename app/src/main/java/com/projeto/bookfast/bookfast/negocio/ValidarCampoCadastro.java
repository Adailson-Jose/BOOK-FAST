package com.projeto.bookfast.bookfast.negocio;


import android.widget.EditText;

public class ValidarCampoCadastro {

    public boolean vefificaCadastroUsuario(EditText cpf, EditText nome, EditText email, EditText senha) {
        String cpfString = String.valueOf(cpf);
        String nomeString = String.valueOf(nome);
        String emailString = String.valueOf(email);
        String senhaString = String.valueOf(senha);

        if (cpfString.trim().isEmpty()|| nomeString.trim().isEmpty() || emailString.trim().isEmpty()
                || senhaString.trim().isEmpty()){
                    return false;
        }else
            return true;
    }

    public boolean isEmailValid(EditText email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher((CharSequence)email).matches();
    }

    public boolean vefificaCadastroLivro(EditText isbn, EditText nome, EditText qtdAlugado, EditText autor, EditText genero, EditText qtTotal, EditText ano, EditText numEdicao) {
        String Isbn = String.valueOf(isbn);
        String Nome = String.valueOf(nome);
        String QtAlugado = String.valueOf(qtdAlugado);
        String Autor = String.valueOf(autor);
        String Genero = String.valueOf(genero);
        String QtTotal = String.valueOf(qtTotal);
        String Ano = String.valueOf(ano);
        String NumEdicao = String.valueOf(numEdicao);

        if (Isbn.trim().isEmpty() || Nome.trim().isEmpty() || QtAlugado.trim().isEmpty()
                || Autor.trim().isEmpty() || Genero.trim().isEmpty() || QtTotal.trim().isEmpty() || Ano.trim().isEmpty() || NumEdicao.trim().isEmpty()) {
            return false;
        } else
            return true;
    }

    public boolean vefificaEdicaoLivro(EditText isbn, EditText nome, EditText qtdAlugado, EditText autor, EditText genero, EditText qtTotal, EditText ano, EditText numEdicao) {
        String Isbn = String.valueOf(isbn);
        String Nome = String.valueOf(nome);
        String QtAlugado = String.valueOf(qtdAlugado);
        String Autor = String.valueOf(autor);
        String Genero = String.valueOf(genero);
        String QtTotal = String.valueOf(qtTotal);
        String Ano = String.valueOf(ano);
        String NumEdicao = String.valueOf(numEdicao);

        if (Isbn.trim().isEmpty() || Nome.trim().isEmpty() || QtAlugado.trim().isEmpty()
                || Autor.trim().isEmpty() || Genero.trim().isEmpty() || QtTotal.trim().isEmpty() || Ano.trim().isEmpty() || NumEdicao.trim().isEmpty()) {
            return false;
        } else
            return true;
    }


}
