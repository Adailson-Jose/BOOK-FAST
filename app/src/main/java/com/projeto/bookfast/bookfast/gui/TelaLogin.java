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

        //final PessoaDao pessoaDao = new PessoaDao(this);

        btLogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView editUsuario = (TextView) findViewById(R.id.editUsuario);
                TextView editSenha = (TextView) findViewById(R.id.editSenha);
                String login = editUsuario.getText().toString();
                String senha = editSenha.getText().toString();

                if (login.equals("admin") && senha.equals("admin")) {
                    Pessoa administrador = new Pessoa(1, "admin", "admin@hotmail.com", "admin");
                    /*
                    Intent abreTelaInicail = new Intent(TelaLogin.this, TelaInicial.class);
                    abreTelaInicail.putExtra("KEY", administrador.getCpf());
                    startActivity(abreTelaInicail); */
                    Toast.makeText(TelaLogin.this, "Login do ADMINISTRADOR realizado com sucesso.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TelaLogin.this, "Login de user comum realizado com sucesso.", Toast.LENGTH_LONG).show();

                    /*if(senha!=null && bd.selecioanarPessoa(login).getCpf() !=""){
                        // se entrou aqui é porque existe um usuário baseado na busca
                        Intent abreTelaInicail= new Intent(TelaLogin.this, TelaInicial.class);
                        startActivity(abreTelaInicail);

                    } else {
                        // se entrou aqui é porque NÃO existe um usuário baseado na busca
                    Context contexto = getApplicationContext();
                    String texto = "Senha ou login incorretos.";
                    int duracao = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(contexto, texto, duracao);
                    toast.show(); */
                }



            }
        });
        btCadastrarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TelaLogin.this, Teste.class);
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
    private void alert (String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
