package com.projeto.bookfast.bookfast.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;

import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoLogin;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;

public class TelaLogin extends AppCompatActivity {
    EditText editUsuario, editSenha;
    Button btLogar, btRecuperarSenha, btCadastrarUsuario;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        btLogar = (Button) findViewById(R.id.btLogar);
        btRecuperarSenha = (Button) findViewById(R.id.btRecuperarSenha);
        btCadastrarUsuario = (Button) findViewById(R.id.btCadastrarUsuario);

        CreatBancoDados bancoCriado = new CreatBancoDados(getApplicationContext());

        btLogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ReadBancoDados buscar = new ReadBancoDados(getApplicationContext());
                ValidarCampoLogin validarCampos = new ValidarCampoLogin();

                if (!validarCampos.ValidarCampoLogin(editUsuario, editSenha)) {
                    String senha = editSenha.getText().toString();
                    String loginCpf = editUsuario.getText().toString();
                    //Teste de limpar os campos
                    editSenha.getText().clear();
                    editUsuario.getText().clear();
                    // Teste de buscar pessoa
                    pessoa = buscar.getPessoa(Integer.parseInt(loginCpf));
                    if (pessoa != null && pessoa.getSenha().equals(senha)) {
                        if (pessoa.getCpf() == (1234567890)) {
                            Intent abreTelaInicial = new Intent(TelaLogin.this, TelaInicialAdministrador.class);
                            abreTelaInicial.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                            startActivity(abreTelaInicial);
                            Toast.makeText(TelaLogin.this, R.string.loginAdm, Toast.LENGTH_SHORT).show();

                        } else {
                            //  se entrou aqui é porque existe um usuário baseado na busca
                            Intent abreTelaInicialUsuarioComum = new Intent(TelaLogin.this, TelaInicialUsuarioComum.class);
                            abreTelaInicialUsuarioComum.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                            startActivity(abreTelaInicialUsuarioComum);
                            Toast.makeText(TelaLogin.this, R.string.loginUseComum, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TelaLogin.this, R.string.CamposInvalidos, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(TelaLogin.this, R.string.FaltaPreenchimento, Toast.LENGTH_SHORT).show();

                }
            }
        });

        btCadastrarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastrarUsuario.class);
                startActivity(abreCadastro);
            }

        });
        btRecuperarSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abreAlteracaoDeSenha= new Intent(TelaLogin.this, TelaRecuperarSenha.class);
                startActivity(abreAlteracaoDeSenha);
            }

        });

    }
}
