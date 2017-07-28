package com.projeto.bookfast.bookfast.negocio;


import android.widget.EditText;

public class ValidarCadastro {
    public boolean vefificacadastrovazio (EditText cpf, EditText nome, EditText email, EditText senha ){

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
}
