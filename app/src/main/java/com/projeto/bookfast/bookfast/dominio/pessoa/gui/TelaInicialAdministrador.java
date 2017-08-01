package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.livro.gui.TelaLivroAdministrador;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;


public class TelaInicialAdministrador extends AppCompatActivity {
    Button btLivro, btUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ReadPessoa busca = new ReadPessoa(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_admistrador);
        btLivro = (Button) findViewById(R.id.btLivro);
        btUsuario = (Button) findViewById(R.id.btUsuario);

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

    }
}
