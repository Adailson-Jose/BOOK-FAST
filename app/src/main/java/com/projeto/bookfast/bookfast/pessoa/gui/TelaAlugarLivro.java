package com.projeto.bookfast.bookfast.pessoa.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.ItemLivro;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.ValidaEmprestimo;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.livro.percistencia.UpdateLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Aluguel;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.UpdatePessoa;

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
                    ValidaEmprestimo validaEmprestimo = new ValidaEmprestimo();
                    validaEmprestimo.pediemprestimo(livroTeste);
                    Aluguel aluguel = new Aluguel();
                    aluguel.setId(pessoaTeste.getId());
                    ItemLivro itemLivro = new ItemLivro();
                    itemLivro.setIdTtemAluguel(aluguel.getId());
                    itemLivro.setIdLivro(livroTeste.getId());
                    UpdatePessoa updatePessoa = new UpdatePessoa(getApplicationContext());
                    UpdateLivro updateLivro = new UpdateLivro(getApplicationContext());
                    updatePessoa.insertAluguel(aluguel);
                    updateLivro.insertItemLivro(itemLivro);
                    Toast.makeText(TelaAlugarLivro.this, "Cheguei e Fiz", Toast.LENGTH_LONG).show();
                    Toast.makeText(TelaAlugarLivro.this, livroTeste.getQtdTotal(), Toast.LENGTH_LONG).show();
                    //  Toast.makeText(TelaAlugarLivro.this, livroTeste.getQtdTotal(), Toast.LENGTH_LONG).show();
                }
            });

            pessoaTeste = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
        }
    }
}




