package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.gui.TelaAlugarLivro;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.recomendacao.negocio.SlopeOne;
import com.projeto.bookfast.bookfast.recomendacao.persistencia.AvaliacaoDao;

import java.util.ArrayList;
import java.util.List;

public class TelaListarTodosLivrosUusario extends AppCompatActivity {
    Pessoa pessoa;
    Button btTelaPricipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar_todos_livros_usuario);
        btTelaPricipal = (Button) findViewById(R.id.btTelaPricipal);
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ReadPessoa buscarPessoa = new ReadPessoa(getApplicationContext());
        final AvaliacaoDao buscaAvaliacao = new AvaliacaoDao(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.listViewLivro);
        final ArrayList<Livro> livro = buscarLivro.getListaLivro();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
        }
        ArrayAdapter adapter = new LivroAdapter(getApplicationContext(), R.layout.linha, livro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent abreTelaAlugarLivro = new Intent(TelaListarTodosLivrosUusario.this, TelaAlugarLivro.class);
                abreTelaAlugarLivro.putExtra("livro", String.valueOf(livro.get(position).getIsbn()));
                abreTelaAlugarLivro.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaAlugarLivro);
            }
        });

        SlopeOne slopeOne = new SlopeOne(getApplicationContext());
        slopeOne.leituraDados();
        List listaRecomendacao = slopeOne.listaRecomendacao(pessoa);
        ArrayList<Livro> listaLivro = new ArrayList<>();

        for (int i = 0; i < listaRecomendacao.size(); i++) {
            Livro livroTemp = buscarLivro.getLivro((Integer) listaRecomendacao.get(i));
            if (buscaAvaliacao.getAvaliacaoIdPessoIdLivro(pessoa.getId(), livroTemp.getId()) == null) {
                listaLivro.add(livroTemp);
            }
        }

        ListView listViewSugestao = (ListView) findViewById(R.id.listViewLivroSugestao);
        ArrayAdapter LivroAdapter = new LivroAdapter(getApplicationContext(), R.layout.linha, listaLivro);
        listViewSugestao.setAdapter(LivroAdapter);
        listViewSugestao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent abreTelaAlugarLivro = new Intent(TelaListarTodosLivrosUusario.this, TelaAlugarLivro.class);
                abreTelaAlugarLivro.putExtra("livro", String.valueOf(livro.get(position).getIsbn()));
                abreTelaAlugarLivro.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaAlugarLivro);
            }
        });
        btTelaPricipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaInicialUsuarioComum = new Intent(TelaListarTodosLivrosUusario.this, TelaInicialUsuarioComum.class);
                abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaInicialUsuarioComum);
            }
        });
    }
}
