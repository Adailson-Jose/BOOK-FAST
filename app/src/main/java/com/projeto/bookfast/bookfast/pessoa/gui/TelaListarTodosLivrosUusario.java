package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;

import java.util.ArrayList;

public class TelaListarTodosLivrosUusario extends AppCompatActivity {
    Pessoa pessoaTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listar_todos_livros_usuario);
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ReadPessoa buscarPessoa = new ReadPessoa(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.listViewLivro);
        final ArrayList<Livro> livro = buscarLivro.getListaLivro();
        ArrayAdapter adapter = new LivroAdapter(getApplicationContext(), R.layout.linha, livro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    pessoaTeste = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
                    Intent abreTelaAlugarLivro = new Intent(TelaListarTodosLivrosUusario.this, TelaAlugarLivro.class);
                    abreTelaAlugarLivro.putExtra("livro", String.valueOf(livro.get(position).getIsbn()));
                    abreTelaAlugarLivro.putExtra("pessoa", String.valueOf(String.valueOf(pessoaTeste.getCpf())));
                    startActivity(abreTelaAlugarLivro);
                }
            }
        });
    }
}
