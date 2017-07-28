package com.projeto.bookfast.bookfast.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;

import java.util.ArrayList;

public class TelaListaLivros extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_livros);
        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> livros = preencherDados();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, livros);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), livros.get(position).toString(), Toast.LENGTH_LONG).show();


            }
        });

    }

    private ArrayList<String> preencherDados() {
        ReadBancoDados buscarLivro = new ReadBancoDados(getApplicationContext());
        ArrayList<String> stringDados = new ArrayList<>();
        ArrayList<Livro> lvros;
        lvros = buscarLivro.getListaLivro();

        for (Livro livro : lvros) {
            stringDados.add("Isbn: " + livro.getIsbn() + ", Nome: " + livro.getNome() + ", Gênero: " + livro.getGenero() + ".");
        }
        return stringDados;
    }
}
