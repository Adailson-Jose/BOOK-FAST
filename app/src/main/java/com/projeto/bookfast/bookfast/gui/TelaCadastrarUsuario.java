package com.projeto.bookfast.bookfast.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoCadastro;
import com.projeto.bookfast.bookfast.persistencia.UpdateBancoDados;

public class TelaCadastrarUsuario extends AppCompatActivity {
    EditText editNovoUsuario, editNovaSenha, editNovoEmail, editNovoNome;
    Button btRegistrar, btCancelarRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar_usuario);
        editNovoNome = (EditText) findViewById(R.id.editNovoNome);
        editNovoEmail = (EditText) findViewById(R.id.editNovoEmail);
        editNovoUsuario = (EditText) findViewById(R.id.editNovoUsuario);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);

        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        btCancelarRegistro = (Button) findViewById(R.id.btCancelarRegistro);
        btCancelarRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();

            }

        });
        btRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ValidarCampoCadastro validarCampos = new ValidarCampoCadastro();
                ViewGroup group = (ViewGroup) findViewById(R.id.raizCadastroUsuario);
                LimparTela limparTela = new LimparTela();

                if (!validarCampos.vefificaCadastroUsuario(editNovoUsuario, editNovoNome, editNovoEmail, editNovaSenha)) {
                    String cpf = editNovoUsuario.getText().toString();
                    String nome = editNovoNome.getText().toString();
                    String email = editNovoEmail.getText().toString();
                    String senha = editNovaSenha.getText().toString();
                    limparTela.clearForm(group);
                    editNovoNome.requestFocus();
                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(nome);
                    pessoa.setEmail(email);
                    pessoa.setCpf(Integer.parseInt(cpf));
                    pessoa.setSenha(senha);
                    UpdateBancoDados inserir = new UpdateBancoDados(getApplicationContext());
                    if (inserir.insertPessoa(pessoa)) {
                        Toast.makeText(TelaCadastrarUsuario.this, "Pessoa foi inserida com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaCadastrarUsuario.this, "Erro ao inserir pessoa", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(TelaCadastrarUsuario.this, "Cadastro  realizado com sucesso.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(TelaCadastrarUsuario.this, "Campos inválidos.", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }
}
