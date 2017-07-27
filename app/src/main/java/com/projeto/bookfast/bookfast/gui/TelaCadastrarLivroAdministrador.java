package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;
import com.projeto.bookfast.bookfast.persistencia.UpdateBancoDados;

public class TelaCadastrarLivroAdministrador extends Activity {
    Button btCadastrarLivro, btCancelar;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantdadeTotal, editQuantdadeAugada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar_livro_administrador);
        editIsbn = (EditText) findViewById(R.id.editIsbn);
        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editAutor = (EditText) findViewById(R.id.editAutor);
        editEdicao = (EditText) findViewById(R.id.editEdicao);
        editAno = (EditText) findViewById(R.id.editAno);
        editQuantdadeTotal = (EditText) findViewById(R.id.editQuantdadeTotal);
        editQuantdadeAugada = (EditText) findViewById(R.id.editQuantdadeAugada);

        btCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBancoDados inserirLivro = new UpdateBancoDados(getApplicationContext());
                Integer isbn = Integer.parseInt(editIsbn.getText().toString());
                int edicao = Integer.parseInt(editEdicao.getText().toString());
                int ano = Integer.parseInt(editAno.getText().toString());
                int quantdadeTotal = Integer.parseInt(editQuantdadeTotal.getText().toString());
                int quantdadeAugada = Integer.parseInt(editQuantdadeAugada.getText().toString());
                String nome = editNome.getText().toString();
                String genero = editGenero.getText().toString();
                String autor = editAutor.getText().toString();
                //Falta validar os campos!
                Livro livro = new Livro(isbn, nome, quantdadeAugada, autor, genero, quantdadeTotal, ano, edicao);
                inserirLivro.insertLivro(livro);
                Toast.makeText(TelaCadastrarLivroAdministrador.this, "LVRO INSERIDO COM SUCESSO", Toast.LENGTH_LONG).show();


            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
