package com.projeto.bookfast.bookfast.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;


import java.util.ArrayList;
import java.util.List;


public class TelaInicial extends AppCompatActivity {
    ListView listViewPessoa;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    TextView mensagem;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ReadBancoDados busca = new  ReadBancoDados(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tala_inicial);
        this.mensagem = (TextView) this.findViewById(R.id.mensagem);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = busca.getPessoa(Integer.parseInt(String.valueOf(bundle.get("KEY"))));
            String dadosPessoa="Nome: "+pessoa.getNome()+", Cpf: "+pessoa.getCpf()+", Senha: "+pessoa.getSenha()+" Id: "+pessoa.getId()+", Email: "+pessoa.getEmail()+".";
            this.mensagem.setText(dadosPessoa);

        }else {
            this.mensagem.setText("deu errado");

        }

    }
}
