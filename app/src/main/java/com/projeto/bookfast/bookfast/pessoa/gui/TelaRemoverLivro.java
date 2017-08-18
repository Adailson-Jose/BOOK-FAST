package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;

import java.util.ArrayList;

public class TelaRemoverLivro extends AppCompatActivity {
    Button btDevolver, btRenovar, btVoltar;
    TextView textViewDataDevolucao;
    ListView listViewLivroSelecionado;
    ReadLivro buscarLivro;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_livro);
        btDevolver = (Button) findViewById(R.id.btDevolver);
        btRenovar = (Button) findViewById(R.id.btRenovar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        textViewDataDevolucao = (TextView) findViewById(R.id.textViewDataDevolucao);
        textViewDataDevolucao.setText("Dados da devoluão do livro.");
        ReadPessoa buscar = new ReadPessoa(getApplicationContext());
        ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        ArrayList<Livro> livro = new ArrayList<Livro>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = buscar.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            livro.add(buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro")))));
        }

        ListView listViewLivroSelecionado = (ListView) findViewById(R.id.listViewLivroSelecionado);
        ArrayAdapter adapter = new LivroAdapter(getApplicationContext(), R.layout.linha, livro);
        listViewLivroSelecionado.setAdapter(adapter);

        btDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btRenovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaInicialUsuarioComum = new Intent(TelaRemoverLivro.this, TelaInicialUsuarioComum.class);
                abreTelaInicialUsuarioComum.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaInicialUsuarioComum);
            }
        });


    }
}
