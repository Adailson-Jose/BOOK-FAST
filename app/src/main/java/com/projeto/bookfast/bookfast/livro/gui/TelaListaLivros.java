package com.projeto.bookfast.bookfast.livro.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;

import java.util.ArrayList;

public class TelaListaLivros extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        setContentView(R.layout.activity_tela_lista_livros);
        ListView listView = (ListView) findViewById(R.id.listViewLivro);
        final ArrayList<Livro> livro = buscarLivro.getListaLivro();
        ArrayAdapter adapter = new LivroAdapter(this, livro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), livro.get(position).getNome().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
