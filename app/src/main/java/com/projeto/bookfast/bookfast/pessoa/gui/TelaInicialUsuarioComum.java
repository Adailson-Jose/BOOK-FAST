package com.projeto.bookfast.bookfast.pessoa.gui;

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
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.gui.TelaListaLivros;
import com.projeto.bookfast.bookfast.livro.gui.TelaQRcode;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.percistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;

import java.util.ArrayList;

public class TelaInicialUsuarioComum extends Activity {
    TextView textViewBemVindo;
    Button btEmprestimoQRcode, btMinhaInformacao, btListalivros;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial_usuario_comum);
        btEmprestimoQRcode = (Button) findViewById(R.id.btEmprestimoQRcode);
        btMinhaInformacao = (Button) findViewById(R.id.btMinhaInformacao);
        btListalivros = (Button) findViewById(R.id.btListalivros);
        textViewBemVindo = (TextView) findViewById(R.id.textViewBemVindo);
        ReadPessoa busca = new ReadPessoa(getApplicationContext());
        ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ArrayList<Livro> livro = new ArrayList<Livro>();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa = "SEJA BEM-VNDO-> " + pessoa.getNome();
            textViewBemVindo.setText(dadosPessoa);
        } else {
            textViewBemVindo.setText("UM ERRO OCORREU.");
        }

        String[] ids = pessoa.getLivros().trim().split(" ");
        for (String idLivro : ids) {
            if (idLivro.equals("")) {
                //
            } else {
                livro.add(buscarLivro.getLivro(Integer.parseInt(idLivro)));
                Toast.makeText(TelaInicialUsuarioComum.this, buscarLivro.getLivro(Integer.parseInt(idLivro)).getNome(), Toast.LENGTH_SHORT).show();


            }
        }

        ListView listView = (ListView) findViewById(R.id.listViewLivros);
        ArrayAdapter adapter = new LivroAdapter(this, livro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent AbreTelaRemoverLivro = new Intent(TelaInicialUsuarioComum.this, TelaRemoverLivro.class);
                AbreTelaRemoverLivro.putExtra("livro", String.valueOf(livro.get(position).getIsbn()));
                AbreTelaRemoverLivro.putExtra("pessoa", String.valueOf(pessoa.getCpf()));

                startActivity(AbreTelaRemoverLivro);
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
                AbreTelaInformacaoUsuario.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(AbreTelaInformacaoUsuario);
            }
       });

        btListalivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AbreTelaListaLivros = new Intent(TelaInicialUsuarioComum.this, TelaListaLivros.class);
                AbreTelaListaLivros.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                startActivity(AbreTelaListaLivros);
            }
        });
    }
}
