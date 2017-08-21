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

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.gui.TelaDevolverLivro;
import com.projeto.bookfast.bookfast.aluguel.persistencia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.aluguel.gui.TelaQRcode;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapter;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

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
        AluguelDao buscaAluguel = new AluguelDao(getApplicationContext());
        final ArrayList<Livro> livro = new ArrayList<Livro>();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            String dadosPessoa = "Usuário: " + pessoa.getNome();
            textViewBemVindo.setText(dadosPessoa);
        } else {
            textViewBemVindo.setText("UM ERRO OCORREU.");
        }

        String[] ids = pessoa.getListaAluguel().trim().split(" ");
        for (String idAluguel : ids) {
            if (idAluguel.trim().equals("")) {
                continue;
            } else {

                livro.add(buscarLivro.getLivro(buscaAluguel.getAluguel(Integer.parseInt(idAluguel)).getIdLivro()));
            }
        }
        ListView listView = (ListView) findViewById(R.id.listViewLivros);
        ArrayAdapter adapter = new LivroAdapter(getApplicationContext(), R.layout.linha, livro);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent AbreTelaRemoverLivro = new Intent(TelaInicialUsuarioComum.this, TelaDevolverLivro.class);
                AbreTelaRemoverLivro.putExtra("livro", String.valueOf(livro.get(position).getIsbn()));
                AbreTelaRemoverLivro.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(AbreTelaRemoverLivro);
            }
        });

        btEmprestimoQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaQrCode = new Intent(TelaInicialUsuarioComum.this, TelaQRcode.class);
                startActivity(abreTelaQrCode);
            }
        });

        btMinhaInformacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaInformacaoUsuario = new Intent(TelaInicialUsuarioComum.this, TelaInformacaoUsuario.class);
                abreTelaInformacaoUsuario.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaInformacaoUsuario);
            }
        });

        btListalivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaListarTodosLivrosUusario = new Intent(TelaInicialUsuarioComum.this, TelaListarTodosLivrosUusario.class);
                abreTelaListarTodosLivrosUusario.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaListarTodosLivrosUusario);
            }
        });
    }
}
