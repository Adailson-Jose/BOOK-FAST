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
import com.projeto.bookfast.bookfast.aluguel.dominio.Aluguel;
import com.projeto.bookfast.bookfast.aluguel.persistecia.AluguelDao;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.gui.TelaQRcode;
import com.projeto.bookfast.bookfast.livro.negocio.LivroAdapterUsuario;
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
        AluguelDao buscarAluguel = new AluguelDao(getApplicationContext());
        final ArrayList<Livro> livros = new ArrayList<Livro>();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            pessoa = busca.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa = "SEJA BEM-VINDO -> " + pessoa.getNome();
            textViewBemVindo.setText(dadosPessoa);
        } else {
            textViewBemVindo.setText("UM ERRO OCORREU.");
        }

        String[] ids = pessoa.getListaAluguel().trim().split(" ");
        for (String idAluguel : ids) {
            if (idAluguel.equals("")) {
                //
            } else {
                Aluguel aluguel = buscarAluguel.getAluguel(Integer.parseInt(idAluguel));
                livros.add(buscarLivro.getLivro(aluguel.getIdLivro()));
            }
        }

        ListView listView = (ListView) findViewById(R.id.listViewLivros);
        ArrayAdapter adapter = new LivroAdapterUsuario(getApplicationContext(), R.layout.linha, livros);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent AbreTelaRemoverLivro = new Intent(TelaInicialUsuarioComum.this, TelaRemoverLivro.class);
                AbreTelaRemoverLivro.putExtra("livro", String.valueOf(livros.get(position).getIsbn()));
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
                Intent abreTelaListarTodosLivrosUusario = new Intent(TelaInicialUsuarioComum.this, TelaListarTodosLivrosUusario.class);
                abreTelaListarTodosLivrosUusario.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaListarTodosLivrosUusario);
            }
        });
    }
}
