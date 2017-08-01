package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;

public class TelaInicialUsuarioComum extends Activity {
    Pessoa pessoa;
    TextView textViewDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_usuario_comum);
        ReadPessoa busca = new ReadPessoa(getApplicationContext());
        textViewDados = (TextView) findViewById(R.id.textViewDados);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa = "Nome: " + pessoa.getNome() + ", Cpf: " + pessoa.getCpf() + ", Senha: " + pessoa.getSenha() + " Id: " + pessoa.getId() + ", Email: " + pessoa.getEmail() + ".";
            textViewDados.setText(dadosPessoa);
        } else {
            textViewDados.setText("UM ERRO OCORREU.");
        }
    }
}
