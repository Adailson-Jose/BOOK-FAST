package com.projeto.bookfast.book_fast.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.bookfast.book_fast.R;
import com.projeto.bookfast.book_fast.dominio.Pessoa;
import com.projeto.bookfast.book_fast.persistencia.BancoDados;

public class TelaCadastrar extends AppCompatActivity {
    EditText editNovoUsuario, editNovaSenha, editNovoEmail, editNovoNome, editNovoNasc, editNovoId;
    Button btRegistrar, btCancelarRegistro;
    BancoDados bd= new BancoDados(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastrar);
        editNovoNome=(EditText) findViewById(R.id.editNovoNome);
        editNovoEmail=(EditText) findViewById(R.id.editNovoEmail);
        editNovoUsuario = (EditText) findViewById(R.id.editNovoUsuario);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        editNovoNasc = (EditText) findViewById(R.id.editNovoNasc);
        editNovoId = (EditText) findViewById(R.id.editNovoId);

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
                String cpf = editNovoUsuario.getText().toString();
                String nome = editNovoNome.getText().toString();
                String email = editNovoEmail.getText().toString();
                String senha = editNovaSenha.getText().toString();
                String nasc = editNovoNasc.getText().toString();
                String id = editNovoId.getText().toString();


                Pessoa pessoa = new Pessoa(cpf,nome,email,senha);

                bd.addPessoa(pessoa);

            }

        });


    }
}
