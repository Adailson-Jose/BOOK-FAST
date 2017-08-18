package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.negocio.ValidaEmprestimo;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;

public class TelaAlugarLivro extends AppCompatActivity {
    Livro livroTeste;
    Pessoa pessoaTeste;
    Button btAlugarLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alugar_livro);
        btAlugarLivro = (Button) findViewById(R.id.btAlugaLivro);
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ReadPessoa buscarPessoa = new ReadPessoa(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            livroTeste = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
            pessoaTeste = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            Toast.makeText(TelaAlugarLivro.this, pessoaTeste.getNome() + livroTeste.getNome(), Toast.LENGTH_LONG).show();
            btAlugarLivro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ValidaEmprestimo validaEmprestimo = new ValidaEmprestimo(getApplicationContext());
                    if (validaEmprestimo.pediEmprestimo(livroTeste, pessoaTeste)) {
                        Toast.makeText(TelaAlugarLivro.this, "LIVRO ALUGADO COM SUCESSO.", Toast.LENGTH_LONG).show();
                        Intent abreTelaInicialUsuarioComum = new Intent(TelaAlugarLivro.this, TelaInicialUsuarioComum.class);
                        abreTelaInicialUsuarioComum.putExtra("livro", String.valueOf(livroTeste.getIsbn()));
                        abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoaTeste.getCpf()));
                        startActivity(abreTelaInicialUsuarioComum);
                    } else {
                        Toast.makeText(TelaAlugarLivro.this, "EXCEDEU O LIMITE.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}




