package com.projeto.bookfast.bookfast.aluguel.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.negocio.DevolverLivro;
import com.projeto.bookfast.bookfast.aluguel.negocio.RenovarLivro;
import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.gui.TelaInicialUsuarioComum;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

import java.util.ArrayList;

public class TelaDevolverLivro extends AppCompatActivity {
    Button btDevolver, btRenovar, btVoltar;
    TextView textViewDataDevolucao;
    Aluguel aluguel;
    Pessoa pessoa;
    Livro livro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devolver_livro);
        btDevolver = (Button) findViewById(R.id.btDevolver);
        btRenovar = (Button) findViewById(R.id.btRenovar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        textViewDataDevolucao = (TextView) findViewById(R.id.textViewDataDevolucao);
        ReadPessoa buscar = new ReadPessoa(getApplicationContext());
        ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        AluguelDao buscaAluguel = new AluguelDao(getApplicationContext());
        ArrayList<Livro> livros = new ArrayList<Livro>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = buscar.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            livro = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
            aluguel = buscaAluguel.getAluguel(pessoa.getId(), livro.getId());
            textViewDataDevolucao.setText("Data da devolução: " + aluguel.getDataEntrega());
            livros.add(livro);
        }

        ListView listViewLivroSelecionado = (ListView) findViewById(R.id.listViewLivroSelecionado);
        ArrayAdapter adapter = new LivroAdapter(getApplicationContext(), R.layout.linha, livros);
        listViewLivroSelecionado.setAdapter(adapter);

        btDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevolverLivro devolverLivro = new DevolverLivro(getApplicationContext());
                if (devolverLivro.devolverLivro(livro,pessoa)){
                    Toast.makeText(TelaDevolverLivro.this, "Devolução feita com sucesso.", Toast.LENGTH_SHORT).show();
                    Intent abreAvaliacaoAluguel = new Intent(TelaDevolverLivro.this, AvaliacaoAluguel.class);
                    abreAvaliacaoAluguel.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                    abreAvaliacaoAluguel.putExtra("livro", String.valueOf(livro.getIsbn()));
                    startActivity(abreAvaliacaoAluguel);
                } else {
                    Toast.makeText(TelaDevolverLivro.this, "Um erro aconteceu durante a renovacão do aluguel.", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btRenovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RenovarLivro renovarLivro = new RenovarLivro(getApplicationContext());
                if (renovarLivro.renovarLivro(livro, pessoa)) {
                    Toast.makeText(TelaDevolverLivro.this, "Renovação feita com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TelaDevolverLivro.this, "Um erro aconteceu durante a devolução.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaInicialUsuarioComum = new Intent(TelaDevolverLivro.this, TelaInicialUsuarioComum.class);
                abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaInicialUsuarioComum);
            }
        });


    }
}
