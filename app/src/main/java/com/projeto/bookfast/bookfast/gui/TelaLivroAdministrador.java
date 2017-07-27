package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Livro;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;

import java.util.ArrayList;

public class TelaLivroAdministrador extends Activity {
    Button btEditarLivro, btCadastrarLivro;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btEditarLivro = (Button) findViewById(R.id.btEditarLivro);
        btCadastrarLivro = (Button) findViewById(R.id.btCadastrarLivro);

        setContentView(R.layout.activity_tela_livro_admnistrador);
        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> livros = preencherDados();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, livros);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "posição: " + livros.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });

        /*btCadastrarLivro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastrarUsuario.class);
                startActivity(abreCadastro);

        btEditarLivro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(TelaLivroAdministrador.this, "FALTA IMPLEMENTAR AINDA", Toast.LENGTH_LONG).show();

            }

        });*/

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
