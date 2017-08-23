package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.gui.TelaLivroAdministrador;


public class TelaInicialAdministrador extends AppCompatActivity {
    Button btLivro, btUsuario, btSairTelaAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_admistrador);
        btLivro = (Button) findViewById(R.id.btLivro);
        btUsuario = (Button) findViewById(R.id.btUsuario);
        btSairTelaAdm = (Button) findViewById(R.id.btSairTelaAdm);

        btLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaLivroAdministrador = new Intent(TelaInicialAdministrador.this, TelaLivroAdministrador.class);
                startActivity(abreTelaLivroAdministrador);
            }
        });

        btUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaUsuarioAdministrador = new Intent(TelaInicialAdministrador.this, TelaUsuarioAdministrador.class);
                startActivity(abreTelaUsuarioAdministrador);
            }
        });
        
        btSairTelaAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaLogin = new Intent(TelaInicialAdministrador.this, TelaLogin.class);
                startActivity(abreTelaLogin);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
