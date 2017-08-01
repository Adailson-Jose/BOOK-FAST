package com.projeto.bookfast.bookfast.dominio.livro.negocio;

import android.widget.EditText;

import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;

/**
 * Created by oi on 28/07/2017.
 */

public class ValidarCampoEditaLivro {
    ValidarCampoVazio vazio = new ValidarCampoVazio();
    public boolean vefificaEdicaoLivro(EditText isbn, EditText nome, EditText qtdAlugado, EditText autor, EditText genero, EditText qtTotal, EditText ano, EditText numEdicao) {
        String Isbn = isbn.getText().toString();
        String Nome = nome.getText().toString();
        String QtAlugado = qtdAlugado.getText().toString();
        String Autor = autor.getText().toString();
        String Genero = genero.getText().toString();
        String QtTotal = qtTotal.getText().toString();
        String Ano = ano.getText().toString();
        String NumEdicao = numEdicao.getText().toString();
        boolean resultado = false;
        if (vazio.isCampoVazio(Isbn)) {
            resultado = true;
            isbn.setError("Campo ISBN inválido!");
            isbn.requestFocus();
        } else if (vazio.isCampoVazio(Nome)) {
            resultado = true;
            nome.setError("Campo Nome inválido!");
            nome.requestFocus();
        } else if (vazio.isCampoVazio(QtAlugado)) {
            resultado = true;
            qtdAlugado.setError("Campo quantidade de livros alugados inválido!");
            qtdAlugado.requestFocus();
        } else if (vazio.isCampoVazio(Autor)) {
            resultado = true;
            autor.setError("Campo Nome autor inválido!");
            autor.requestFocus();
        } else if (vazio.isCampoVazio(Genero)) {
            resultado = true;
            genero.setError("Campo Gênero inválido!");
            genero.requestFocus();
        } else if (vazio.isCampoVazio(QtTotal)) {
            resultado = true;
            qtTotal.setError("Campo Quantidade total inválido!");
            qtTotal.requestFocus();
        } else if (vazio.isCampoVazio(Ano)) {
            resultado = true;
            ano.setError("Campo Ano inválido!");
            ano.requestFocus();
        } else if (vazio.isCampoVazio(NumEdicao)) {
            resultado = true;
            numEdicao.setError("Campo Número da edição inválido!");
            numEdicao.requestFocus();
        }
        if (resultado) {
            return true;
        } else {
            return false;
        }

    }

}
