
package com.projeto.bookfast.bookfast.negocio;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.projeto.bookfast.bookfast.gui.*;
//import com.projeto.bookfast.book_fast.persistencia.UsuarioDAO.UsuarioDAO;
// ajustar o dominio e a classe?

public class ValidarLogin {
    private final EditText editNovoUsuario;
    private final EditText editNovoId;
    private final EditText editNovoEmail;
    private final EditText editNovoNasc;
    private final EditText editNovaSenha;
    private final EditText editNovoNome;
    private final TelaCadastrar activity;
    //private UsuarioDAO usuarioDAO;


    public ValidarLogin(EditText editNovoUsuario, EditText editNovoNome, EditText editNovoId, EditText editNovoEmail, EditText editNovoNasc, EditText editNovaSenha, TelaCadastrar activity) {
        this.editNovoUsuario = editNovoUsuario;
        this.editNovoId = editNovoId;
        this.editNovoEmail = editNovoEmail;
        this.editNovoNasc = editNovoNasc;
        this.editNovaSenha = editNovaSenha;
        this.editNovoNome = editNovoNome;
        this.activity = activity;
    }

    public boolean validarCampos() {
        boolean res = false;

        if (res = isCampoVazio(editNovoNome.getText().toString())) {
            editNovoNome.requestFocus();
        } else if (res = !isNickValido(editNovoId.getText().toString())) {
            editNovoId.requestFocus();
        } else if (res = !isEmailValido(editNovoEmail.getText().toString())) {
            editNovoEmail.requestFocus();
        } else if (res = !isNascValido(editNovoNasc.getText().toString())) {
            editNovoNasc.requestFocus();
        } else if (res = !isSenhaValida(editNovaSenha.getText().toString())) {
            editNovaSenha.requestFocus();
        } else if (res = !isUsuarioValido(editNovoUsuario.getText().toString())) {
            editNovoUsuario.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos inválidos ou em branco.");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
        return !res;
    }


    private boolean isCampoVazio(String campo) {
        boolean resultado = (TextUtils.isEmpty(campo) || campo.trim().isEmpty());
        return resultado;
    }

    private boolean isNickValido(String nick) {
        if (usuarioDAO.getUsuario(nick) != null) {
            return true;
        }
        boolean resultado = (!isCampoVazio(nick) && !Patterns.EMAIL_ADDRESS.matcher(nick).matches());
        return resultado;
    }

    private boolean isEmailValido(String email) {
        if (usuarioDAO.getUsuarioEmail(email) != null) {
            return true;
        }
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    private boolean isUsuarioValido(String email) {
        if (usuarioDAO.getUsuarioEmail(email) != null) {
            return true;
        }
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    private boolean isNascValido(String nasc) {
        if (isCampoVazio(nasc)) {
            return false;
        } else {
            String rex = "([0]{1}[1-9]{1}|[1-2]{1}[0-9]{1}|[3]{1}[0-1]{1})+" +
                    "\\/([0]{1}[1-9]{1}|[1]{1}[0-2]{2})+" +
                    "\\/([1]{1}[9]{1}[5-9]{1}[0-9]{1}|[2]{1}[0]{1}([0-4]{1}+ " +
                    "[0-9]{1}|[5]{1}[0]{1}))";
            return (nasc.matches(rex));
        }

    }

    private boolean isSenhaValida(String senha) {
        if (isCampoVazio(senha)) {
            return false;
        } else {
            String rex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,10})";
            return (senha.matches(rex));
        }

    }
}
