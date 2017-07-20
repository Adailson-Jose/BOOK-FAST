package com.projeto.bookfast.book_fast.GUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.bookfast.book_fast.R;
import com.projeto.bookfast.book_fast.persistencia.BancoDados;

public class TelaCadastrar extends AppCompatActivity {
    EditText editNovoUsuario, editNovaSenha;
    Button btRegistrar, btCancelarRegistro;
    BancoDados bd= new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar);
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
                ;
            }

        });


    }
}
