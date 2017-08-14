package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarCpf;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;

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

        btLogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ReadPessoa buscar = new ReadPessoa(getApplicationContext());
                ViewGroup group = (ViewGroup) findViewById(R.id.raizLogin);
                String cpfString = editUsuario.getText().toString();
                String senhaStr = editSenha.getText().toString();
                boolean resultado = false;
                if (!ValidarCpf.validarCpf(cpfString)) {
                    resultado = true;
                    editUsuario.setError("Campo CPF inválido!");
                    editUsuario.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(senhaStr)) {
                    resultado = true;
                    editSenha.setError("Campo senha inválido!");
                    editSenha.requestFocus();
                }

                if (!resultado) {
                    String senha = editSenha.getText().toString();
                    String loginCpf = editUsuario.getText().toString();
                    LimparTela.clearForm(group);
                    editUsuario.requestFocus();
                    // Teste de buscar pessoa
                    pessoa = buscar.getPessoa(Long.parseLong(loginCpf));
                    if (pessoa != null && pessoa.getSenha().equals(senha)) {
                        if (pessoa.getCpf() == Long.parseLong("19928810303")) {
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
