package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.livro.gui.TelaQRcode;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;

import java.util.Stack;

public class TelaInicialUsuarioComum extends Activity {
    Pessoa pessoa;
    TextView textViewDados;
    Button btEmprestimoQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_usuario_comum);
        btEmprestimoQRcode = (Button) findViewById(R.id.btEmprestimoQRcode);
        ReadPessoa busca = new ReadPessoa(getApplicationContext());
        //textViewDados = (TextView) findViewById(R.id.textViewDados);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa = "Nome: " + pessoa.getNome() + ", Cpf: " + pessoa.getCpf() + ", Senha: " + pessoa.getSenha() + " Id: " + pessoa.getId() + ", Email: " + pessoa.getEmail() + ".";
            //textViewDados.setText(dadosPessoa);
        } else {
            textViewDados.setText("UM ERRO OCORREU.");
        }

        //Leandro fez essa parte falta fazer o teste
        btEmprestimoQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AbreTelaQrCode = new Intent(TelaInicialUsuarioComum.this, TelaQRcode.class);
                startActivity(AbreTelaQrCode);
            }
        });

    }


}
