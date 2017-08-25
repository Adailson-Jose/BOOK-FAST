package com.projeto.bookfast.bookfast.aluguel.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.dominio.EnumNota;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.gui.TelaInicialUsuarioComum;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.recomendacao.negocio.ValidarAvaliacao;

public class AvaliacaoAluguel extends AppCompatActivity {
    Button btAvaliar, btVoltar;
    private Spinner spinner;
    private String[] listaNotas = EnumNota.listaEnumNotas();
    Pessoa pessoa;
    Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_aluguel);
        btAvaliar = (Button) findViewById(R.id.btAvaliar);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        ReadPessoa buscar = new ReadPessoa(getApplicationContext());
        ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = buscar.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            livro = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaNotas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinnerAvalicao);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarAvaliacao valida = new ValidarAvaliacao(getApplicationContext());
                String avaliacaooTexto = (String) spinner.getSelectedItem();
                if (valida.validaAvaliacao(avaliacaooTexto, pessoa, livro)) {
                    Toast.makeText(AvaliacaoAluguel.this, "Avaliacao feitacom sucesso.", Toast.LENGTH_SHORT).show();
                    Intent abreTelaInicialUsuarioComum = new Intent(AvaliacaoAluguel.this, TelaInicialUsuarioComum.class);
                    abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                    startActivity(abreTelaInicialUsuarioComum);
                } else {
                    Toast.makeText(AvaliacaoAluguel.this, "Você já avaliou esse livro antes.", Toast.LENGTH_SHORT).show();
                    Intent abreTelaInicialUsuarioComum = new Intent(AvaliacaoAluguel.this, TelaInicialUsuarioComum.class);
                    abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                    startActivity(abreTelaInicialUsuarioComum);
                }
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaInicialUsuarioComum = new Intent(AvaliacaoAluguel.this, TelaInicialUsuarioComum.class);
                abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaInicialUsuarioComum);
            }
        });
    }
}
