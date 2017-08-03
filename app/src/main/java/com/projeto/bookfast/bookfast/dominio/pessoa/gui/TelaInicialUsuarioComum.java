package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.dominio.livro.gui.TelaQRcode;
import com.projeto.bookfast.bookfast.dominio.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;

import java.util.ArrayList;

public class TelaInicialUsuarioComum extends Activity {
    Livro livro;
    TextView textViewBemVindo;
    Button btEmprestimoQRcode, btMinhaInformacao, btListalivros;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_usuario_comum);
        ReadPessoa busca = new ReadPessoa(getApplicationContext());

        btEmprestimoQRcode = (Button) findViewById(R.id.btEmprestimoQRcode);
        textViewBemVindo = (TextView) findViewById(R.id.textViewBemVindo);
        ListView listViewLivros = (ListView) findViewById(R.id.listViewLivros);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa = "SEJA BEM-VNDO-> " + pessoa.getNome();
            textViewBemVindo.setText(dadosPessoa);
        } else {
            textViewBemVindo.setText("UM ERRO OCORREU.");
        }

        final ArrayList<String> livros = preencherDados(pessoa);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, livros);
        listViewLivros.setAdapter(arrayAdapter);

        listViewLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), livros.get(position).toString(), Toast.LENGTH_SHORT).show();


            }
        });


        btEmprestimoQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AbreTelaQrCode = new Intent(TelaInicialUsuarioComum.this, TelaQRcode.class);
                startActivity(AbreTelaQrCode);
            }
        });

        btMinhaInformacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AbreTelaInformacaoUsuario = new Intent(TelaInicialUsuarioComum.this, TelaInformacaoUsuario.class);
                AbreTelaInformacaoUsuario.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                startActivity(AbreTelaInformacaoUsuario);
            }
        });

    }

    private ArrayList<String> preencherDados(Pessoa pessoa) {
        ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        ArrayList<String> stringDados = new ArrayList<>();
        String[] ids = pessoa.getLivros().trim().split(" ");
        ;
        Livro livor2;
        for (String idLivro : ids) {
            if (idLivro == "") {
                stringDados.add("VOCÊ NÃO TEM LIVRO ALUGADO!");
            } else {
                livor2 = buscarLivro.getLivro(Integer.parseInt(idLivro));
                stringDados.add("Isbn: " + livor2.getIsbn() + ", Nome: " + livor2.getNome() + ", Gênero: " + livor2.getGenero() + ", Autor: " + livor2.getAutor() + ".");
            }
        }
        return stringDados;
    }
}
