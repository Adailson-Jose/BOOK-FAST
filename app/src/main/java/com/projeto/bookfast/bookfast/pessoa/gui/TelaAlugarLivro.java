package com.projeto.bookfast.bookfast.pessoa.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.aluguel.negocio.ValidaEmprestimo;
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
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ReadPessoa buscarPessoa = new ReadPessoa(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            livroTeste = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
            btAlugarLivro = (Button) findViewById(R.id.btAlugaLivro);
            btAlugarLivro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReadLivro readLivro = new ReadLivro(getApplicationContext());
                    ValidaEmprestimo ValidaEmprestimo = new ValidaEmprestimo();
                    livroTeste = readLivro.getLivro(livroTeste.getIsbn());
                    ValidaEmprestimo.pediEmprestimo(livroTeste, pessoaTeste);
                }
            });

            pessoaTeste = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
        }
    }
}
