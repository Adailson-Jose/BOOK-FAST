package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;
import com.projeto.bookfast.bookfast.persistencia.UpdateBancoDados;

public class TelaRecuperarSenha extends Activity {
    EditText editCPF, editNovaSenha, editEmail;
    Button btAlterarSenha, btCancelarAlteracao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__recuperar__senha);

        editCPF = (EditText) findViewById(R.id.editCPF);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        editEmail = (EditText) findViewById(R.id.editEmail);
        btAlterarSenha = (Button) findViewById(R.id.btAlterarSenha);
        btCancelarAlteracao = (Button) findViewById(R.id.btCancelarAlteracao);

        btAlterarSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ReadBancoDados buscar = new ReadBancoDados(getApplicationContext());
                UpdateBancoDados atualizar = new UpdateBancoDados(getApplicationContext());
                Pessoa pessoa;
                //validar campos e se existe essa pessoa no banco
                String loginCpf = editCPF.getText().toString();
                String novaSenha = editNovaSenha.getText().toString();
                String email = editEmail.getText().toString();
                pessoa = buscar.getPessoa(Integer.parseInt(loginCpf));

                // se campos estiverem corretos e a pessoa existe no banco
                pessoa.setSenha(novaSenha);
                atualizar.updatePessoa(pessoa);
                Toast.makeText(TelaRecuperarSenha.this, "Atualização da SENHA realizado com sucesso.", Toast.LENGTH_LONG).show();

            }

        });

        btCancelarAlteracao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
        }

    }

