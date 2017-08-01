package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.negocio.ValidarCampoCadastroPessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.UpdatePessoa;
import com.projeto.bookfast.bookfast.negocio.LimparTela;

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
                ValidarCampoCadastroPessoa validarCampos = new ValidarCampoCadastroPessoa();
                ViewGroup group = (ViewGroup) findViewById(R.id.raizCadastroUsuario);
                LimparTela limparTela = new LimparTela();
                Pessoa pessoa = new Pessoa();
                ReadPessoa buscar = new ReadPessoa(getApplicationContext());
                String cpf = editNovoUsuario.getText().toString();
                if (validarCampos.vefificaCadastroUsuario(editNovoUsuario, editNovoNome, editNovoEmail, editNovaSenha) == false && buscar.getPessoa(Long.parseLong(cpf)) == null) {
                    String nome = editNovoNome.getText().toString();
                    String email = editNovoEmail.getText().toString();
                    String senha = editNovaSenha.getText().toString();
                    limparTela.clearForm(group);
                    editNovoNome.requestFocus();
                    pessoa.setNome(nome);
                    pessoa.setEmail(email);
                    pessoa.setCpf(Long.parseLong(cpf));
                    pessoa.setSenha(senha);
                    UpdatePessoa inserir = new UpdatePessoa(getApplicationContext());
                    if (inserir.insertPessoa(pessoa)) {
                        Toast.makeText(TelaCadastrarUsuario.this, "Pessoa foi inserida com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaCadastrarUsuario.this, R.string.ErroInserirPessoa, Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(TelaCadastrarUsuario.this, R.string.CadastroSucesso, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(TelaCadastrarUsuario.this, R.string.CPFJaCadastrado, Toast.LENGTH_SHORT).show();

                }
            }

        });

    }
}
