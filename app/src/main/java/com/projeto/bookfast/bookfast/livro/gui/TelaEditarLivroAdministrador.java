package com.projeto.bookfast.bookfast.livro.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.EditarLivro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarIsbn;

import java.io.ByteArrayOutputStream;

public class TelaEditarLivroAdministrador extends AppCompatActivity {
    Button btEdtarLivro, btCancelar, btFoto;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada;
    Livro livro;
    private Bitmap imageBitmap = null;
    private byte imagemBytes[];
    private final int TIRAR_FOTO = 1;
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
        btFoto = (Button) findViewById(R.id.btFoto);
        btEdtarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            livro = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
            editIsbn.setText(String.valueOf(livro.getIsbn()));
            editNome.setText(livro.getNome());
            editGenero.setText(livro.getGenero());
            editAutor.setText(livro.getAutor());
            editEdicao.setText(String.valueOf(livro.getNumEdicao()));
            editAno.setText(String.valueOf((livro.getAno())));
            editQuantidadeTotal.setText(String.valueOf(livro.getQtdTotal()));
            editQuantidadeAlugada.setText(String.valueOf((livro.getQtdAlugado())));
            editIsbn.requestFocus();
        }
        btEdtarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup group = (ViewGroup) findViewById(R.id.raizEditLivro);
                boolean resultado = false;
                String Isbn = editIsbn.getText().toString();
                String Nome = editNome.getText().toString();
                String QtAlugado = editQuantidadeAlugada.getText().toString();
                String Autor = editAutor.getText().toString();
                String Genero = editGenero.getText().toString();
                String QtTotal = editQuantidadeTotal.getText().toString();
                String Ano = editAno.getText().toString();
                String NumEdicao = editEdicao.getText().toString();

                if (ValidarIsbn.validarIsbn(Isbn)) {
                    resultado = true;
                    editIsbn.setError("Campo ISBN inválido!");
                    editIsbn.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Nome)) {
                    resultado = true;
                    editNome.setError("Campo Nome inválido!");
                    editNome.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(QtAlugado)) {
                    resultado = true;
                    editQuantidadeAlugada.setError("Campo quantidade de livros alugados inválido!");
                    editQuantidadeAlugada.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Autor)) {
                    resultado = true;
                    editAutor.setError("Campo Nome autor inválido!");
                    editAutor.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Genero)) {
                    resultado = true;
                    editGenero.setError("Campo Gênero inválido!");
                    editGenero.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(QtTotal)) {
                    resultado = true;
                    editQuantidadeTotal.setError("Campo Quantidade total inválido!");
                    editQuantidadeTotal.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(Ano)) {
                    resultado = true;
                    editAno.setError("Campo Ano inválido!");
                    editAno.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(NumEdicao)) {
                    resultado = true;
                    editEdicao.setError("Campo Número da edição inválido!");
                    editEdicao.requestFocus();
                }
                if (!resultado) {
                    Long isbn = Long.parseLong(editIsbn.getText().toString());
                    int edicao = Integer.parseInt(editEdicao.getText().toString());
                    int ano = Integer.parseInt(editAno.getText().toString());
                    int quantidadeTotal = Integer.parseInt(editQuantidadeTotal.getText().toString());
                    int quanitdadeAlugada = Integer.parseInt(editQuantidadeAlugada.getText().toString());
                    String nome = editNome.getText().toString();
                    String genero = editGenero.getText().toString();
                    String autor = editAutor.getText().toString();
                    LimparTela.clearForm(group);
                    editIsbn.requestFocus();
                    EditarLivro editarLivro = new EditarLivro(getApplicationContext());
                    if (imageBitmap != null) {
                        ByteArrayOutputStream saida = new ByteArrayOutputStream();
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
                        imagemBytes = saida.toByteArray();
                    }
                    if (editarLivro.editarLivro(isbn, nome, quanitdadeAlugada, autor, genero, quantidadeTotal, ano, edicao, imagemBytes)) {
                        Toast.makeText(TelaEditarLivroAdministrador.this, R.string.AtualizcaoLivro, Toast.LENGTH_LONG).show();
                        Intent abreTelaLivroAdministrador = new Intent(TelaEditarLivroAdministrador.this, TelaLivroAdministrador.class);
                        startActivity(abreTelaLivroAdministrador);
                    } else {
                        Toast.makeText(TelaEditarLivroAdministrador.this, R.string.LvroNaoExiste, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btFoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, TIRAR_FOTO);
                }
            }
        });
        btCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
        }
    }
}