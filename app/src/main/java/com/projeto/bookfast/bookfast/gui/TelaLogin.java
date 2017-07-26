package com.projeto.bookfast.bookfast.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;

import com.projeto.bookfast.bookfast.dominio.Pessoa;
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

                TextView editUsuario = (TextView) findViewById(R.id.editUsuario);
                TextView editSenha = (TextView) findViewById(R.id.editSenha);
                String loginCpf = editUsuario.getText().toString();
                pessoa = buscar.getPessoa(Integer.parseInt(loginCpf));
                // Teste de busca
                if (pessoa != null) {

                    if (pessoa.getCpf()==(1234567890)) {
                        Intent abreTelaInicail = new Intent(TelaLogin.this, TelaInicial.class);
                        abreTelaInicail.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                        startActivity(abreTelaInicail);
                        Toast.makeText(TelaLogin.this, "Login do ADMINISTRADOR realizado com sucesso.", Toast.LENGTH_LONG).show();

                    } else {
                        // se entrou aqui é porque existe um usuário baseado na busca
                        Intent abreTelaInicail = new Intent(TelaLogin.this, TelaInicial.class);
                        abreTelaInicail.putExtra("KEY", String.valueOf(pessoa.getCpf()));startActivity(abreTelaInicail);
                        Toast.makeText(TelaLogin.this, "Login de user comum realizado com sucesso.", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(TelaLogin.this, "CAMPO LOGIN INVÁLDO.", Toast.LENGTH_LONG).show();

                }

            }
        });

        btCadastrarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastrar.class);
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
