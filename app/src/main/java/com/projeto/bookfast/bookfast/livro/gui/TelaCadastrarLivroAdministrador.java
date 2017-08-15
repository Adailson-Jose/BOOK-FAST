package com.projeto.bookfast.bookfast.livro.gui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.livro.percistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarIsbn;

import java.io.ByteArrayOutputStream;

public class TelaCadastrarLivroAdministrador extends Activity {
    Button btCadastrarLivro, btCancelar, btFoto;
    EditText editIsbn, editNome, editGenero, editAutor, editEdicao, editAno, editQuantidadeTotal, editQuantidadeAlugada, editTextImagem;
    Livro livro;
    ImageView imagem;
    private Bitmap imageBitmap;
    private final int TIRAR_FOTO = 1;
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
        editQuantidadeTotal = (EditText) findViewById(R.id.editQuantidadeTotal);
        editQuantidadeAlugada = (EditText) findViewById(R.id.editQuantidadeAlugada);
        btCadastrarLivro = (Button) findViewById(R.id.btCadastrarLivro);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        btFoto = (Button) findViewById(R.id.btFoto);
        imagem = (ImageView) findViewById(R.id.imagem);

        btCadastrarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLivro inserirLivro = new UpdateLivro(getApplicationContext());
                ViewGroup group = (ViewGroup) findViewById(R.id.raizCadastroLivro);
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
                    long isbn = Long.parseLong(editIsbn.getText().toString());
                    int edicao = Integer.parseInt(editEdicao.getText().toString());
                    int ano = Integer.parseInt(editAno.getText().toString());
                    int quantidadeTotal = Integer.parseInt(editQuantidadeTotal.getText().toString());
                    int quanitdadeAlugada = Integer.parseInt(editQuantidadeAlugada.getText().toString());
                    String nome = editNome.getText().toString();
                    String genero = editGenero.getText().toString();
                    String autor = editAutor.getText().toString();
                    LimparTela.clearForm(group);
                    editIsbn.requestFocus();
                    ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
                    livro = buscarLivro.getLivro(isbn);
                    if (livro != null) {
                        Toast.makeText(TelaCadastrarLivroAdministrador.this, "LIVRO JÁ CADASTRADO.", Toast.LENGTH_LONG).show();
                    } else {
                        byte imagemBytes[] = null;
                        if (imageBitmap != null) {
                            ByteArrayOutputStream saida = new ByteArrayOutputStream();
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, saida);
                            imagemBytes = saida.toByteArray();
                        }
                        livro = new Livro(isbn, nome, quanitdadeAlugada, autor, genero, quantidadeTotal, ano, edicao, imagemBytes);
                        inserirLivro.insertLivro(livro);
                        Toast.makeText(TelaCadastrarLivroAdministrador.this, "LIVRO CADASTRADO COM SUCESSO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TelaCadastrarLivroAdministrador.this, "Campos inválidos.", Toast.LENGTH_SHORT).show();
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
            imagem.setImageBitmap(imageBitmap);
        }
    }
}