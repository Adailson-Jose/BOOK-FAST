package com.projeto.bookfast.bookfast.pessoa.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projeto.bookfast.bookfast.R;

public class TelaUsuarioAdministrador extends Activity {
    Button btRemoverUsuario, btAtualizarUsuario, btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario_administrador);
        btRemoverUsuario = (Button) findViewById(R.id.btRemoverUsuario);
        btAtualizarUsuario = (Button) findViewById(R.id.btAtualizarUsuario);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaUsuarioAdministrador = new Intent(TelaUsuarioAdministrador.this, TelaInicialAdministrador.class);
                startActivity(abreTelaUsuarioAdministrador);
            }
        });
    }

}
