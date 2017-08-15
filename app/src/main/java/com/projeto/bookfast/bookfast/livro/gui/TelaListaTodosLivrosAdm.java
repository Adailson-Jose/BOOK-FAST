package com.projeto.bookfast.bookfast.livro.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;

import java.util.ArrayList;

public class TelaListaTodosLivrosAdm extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_livros);
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.listViewLivro);
        final ArrayList<Livro> livro = buscarLivro.getListaLivro();
        ArrayAdapter adapter = new LivroAdapter(getApplicationContext(), R.layout.linha, livro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent abreTelaEditarLivroAdministrador = new Intent(TelaListaTodosLivrosAdm.this, TelaEditarLivroAdministrador.class);
                abreTelaEditarLivroAdministrador.putExtra("livro", String.valueOf(livro.get(position).getIsbn()));
                startActivity(abreTelaEditarLivroAdministrador);
            }
        });
    }
}
