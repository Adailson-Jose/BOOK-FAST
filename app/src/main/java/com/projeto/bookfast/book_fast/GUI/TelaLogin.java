package com.projeto.bookfast.book_fast.GUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.bookfast.book_fast.R;
import com.projeto.bookfast.book_fast.persistencia.BancoDados;

public class TelaLogin extends AppCompatActivity {
    EditText editUsuario, editSenha;
    Button btLogar, btRecuperarSenha, btCadastrarUsuario;
    BancoDados bd = new BancoDados(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editSenha= (EditText) findViewById(R.id.editSenha);
        editUsuario= (EditText) findViewById(R.id.editUsuario);
        btLogar = (Button) findViewById(R.id.btLogar);
        btRecuperarSenha = (Button) findViewById(R.id.btRecuperarSenha);
        btCadastrarUsuario = (Button) findViewById(R.id.btCadastrarUsuario);

        btLogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView editUsuario = (TextView) findViewById(R.id.editUsuario);
                TextView editSenha = (TextView) findViewById(R.id.editSenha);
                String login = editUsuario.getText().toString();
                String senha = editSenha.getText().toString();
                if (login.equals("admin")&&senha.equals("admin")){
                    alert("Login realizado com sucesso.");
                            Intent abreTelaInicail= new Intent(TelaLogin.this, TelaInicial.class);
                            startActivity(abreTelaInicail);


                }else{
                    alert("Login ou senha incorretos.");
                }
            }
        });
        btCadastrarUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abreCadastro= new Intent(TelaLogin.this, TelaCadastrar.class);
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
