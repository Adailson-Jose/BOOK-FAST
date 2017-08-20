package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.aluguel.negocio.ValidaEmprestimo;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;

public class TelaAlugarLivro extends AppCompatActivity {
    Livro livroTeste;
    Pessoa pessoaTeste;
    Button btAlugarLivro, btVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alugar_livro);
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ReadPessoa buscarPessoa = new ReadPessoa(getApplicationContext());
        btAlugarLivro = (Button) findViewById(R.id.btAlugaLivro);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            livroTeste = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
            pessoaTeste = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
        }

        btAlugarLivro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReadLivro readLivro = new ReadLivro(getApplicationContext());
                    ValidaEmprestimo validaEmprestimo = new ValidaEmprestimo(getApplication());
                    livroTeste = readLivro.getLivro(livroTeste.getIsbn());
                    if (validaEmprestimo.pediEmprestimo(livroTeste, pessoaTeste)) {
                        Toast.makeText(TelaAlugarLivro.this, "Livro alugado com sucesso.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaAlugarLivro.this, "Erro ao tentar alugar um livro.", Toast.LENGTH_LONG).show();
                    }
                }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreTelaListarTodosLivrosUusario = new Intent(TelaAlugarLivro.this, TelaListarTodosLivrosUusario.class);
                abreTelaListarTodosLivrosUusario.putExtra("pessoa", String.valueOf(pessoaTeste.getCpf()));
                startActivity(abreTelaListarTodosLivrosUusario);
            }
        });
    }
}
