package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

public class TelaInformacaoUsuario extends AppCompatActivity {
    Button btEditaMinhasInformacoes, btCancelarMudanças2;
    TextView editTextNome;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_informacao_usuario);
        ReadPessoa busca = new ReadPessoa(getApplicationContext());
        editTextNome = (TextView) findViewById(R.id.editTextNome);
        btEditaMinhasInformacoes = (Button) findViewById(R.id.btEditaMinhasInformacoes);
        btCancelarMudanças2 = (Button) findViewById(R.id.btCancelarMudanças);

        Bundle bundle = getIntent().getExtras();
        int quantidadeLivrosAludado = 0;
        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            String[] ids = pessoa.getListaAluguel().trim().split(" ");
            for (String idLivro : ids) {
                if (idLivro.equals("")) {
                    continue;
                } else {
                    quantidadeLivrosAludado += 1;
                }
            }
            editTextNome.setText("Nome: " + pessoa.getNome() + "\n Cpf: " + pessoa.getCpf() + "\n E-mail: " + pessoa.getEmail() + "\n Qtd Livros Alugados: " + String.valueOf(quantidadeLivrosAludado));
        }
        btEditaMinhasInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AbreTelaEditaInfoUsuario = new Intent(TelaInformacaoUsuario.this, TelaEditaInfoUsuario.class);
                AbreTelaEditaInfoUsuario.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                startActivity(AbreTelaEditaInfoUsuario);
            }
        });
        btCancelarMudanças2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
