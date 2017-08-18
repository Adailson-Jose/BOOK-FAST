package com.projeto.bookfast.bookfast.pessoa.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.projeto.bookfast.bookfast.R;

public class TelaUsuarioAdministrador extends Activity {
    Button btRemoverUsuario, btAtualizarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario_administrador);
        btRemoverUsuario = (Button) findViewById(R.id.btRemoverUsuario);
        btAtualizarUsuario = (Button) findViewById(R.id.btAtualizarUsuario);
    }
}
