package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;

public class TelaInicialUsuarioComum extends Activity {
    Pessoa pessoa;
    TextView editTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_usuario_comum);
        ReadBancoDados busca = new ReadBancoDados(getApplicationContext());
        editTextView = (TextView) findViewById(R.id.editTextView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = busca.getPessoa(Integer.parseInt(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa = "Nome: " + pessoa.getNome() + ", Cpf: " + pessoa.getCpf() + ", Senha: " + pessoa.getSenha() + " Id: " + pessoa.getId() + ", Email: " + pessoa.getEmail() + ".";
            editTextView.setText(dadosPessoa);
        } else {
            editTextView.setText("UM ERRO OCORREU.");
        }
    }
}
