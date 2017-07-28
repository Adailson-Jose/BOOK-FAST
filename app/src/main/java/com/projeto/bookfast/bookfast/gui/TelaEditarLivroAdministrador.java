package com.projeto.bookfast.bookfast.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;
import com.projeto.bookfast.bookfast.persistencia.UpdateBancoDados;

public class TelaEditarLivroAdministrador extends AppCompatActivity {

    Button btEdtarLivro, btCancelar;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada;
    Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_livro);
        editIsbn = (EditText) findViewById(R.id.editIsbn);
        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editAutor = (EditText) findViewById(R.id.editAutor);
        editEdicao = (EditText) findViewById(R.id.editEdicao);
        editAno = (EditText) findViewById(R.id.editAno);
        editQuantidadeTotal = (EditText) findViewById(R.id.editQuantidadeTotal);
        editQuantidadeAlugada = (EditText) findViewById(R.id.editQuantidadeAlugada);

        btEdtarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCancelar = (Button) findViewById(R.id.btCancelar);

        btEdtarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBancoDados atualizarLivro = new UpdateBancoDados(getApplicationContext());
                Integer isbn = Integer.parseInt(editIsbn.getText().toString());
                int edicao = Integer.parseInt(editEdicao.getText().toString());
                int ano = Integer.parseInt(editAno.getText().toString());
                int quantidadeTotal = Integer.parseInt(editQuantidadeTotal.getText().toString());
                int quanitdadeAlugada = Integer.parseInt(editQuantidadeAlugada.getText().toString());
                String nome = editNome.getText().toString();
                String genero = editGenero.getText().toString();
                String autor = editAutor.getText().toString();
                //Falta validar os campos!
                ReadBancoDados buscarLivro = new ReadBancoDados(getApplicationContext());
                livro = buscarLivro.getLivro(isbn);
                if (livro != null) {
                    livro.setIsbn(isbn);
                    livro.setNumEdicao(edicao);
                    livro.setAno(ano);
                    livro.setQtdTotal(quantidadeTotal);
                    livro.setQtdAlugado(quanitdadeAlugada);
                    livro.setNome(nome);
                    livro.setGenero(genero);
                    livro.setAutor(autor);
                    atualizarLivro.updateLivro(livro);
                    Toast.makeText(TelaEditarLivroAdministrador.this, "LIVRO ATUALIZADO COM SUCESSO", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(TelaEditarLivroAdministrador.this, "LIVRO NÃO EXISTE.", Toast.LENGTH_LONG).show();

                }

            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}