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
    /*Cria uma lista de pessos e mostra na tela principal
    public  void listarPessoas(){
        List<Pessoa> pessoas = db.getListaPessoas();

        arrayList = new ArrayList<String>();
        //Craindo uma lista simples
        adapter = new ArrayAdapter<String>(TelaInicial.this, android.R.layout.simple_list_item_1, arrayList);

        listViewPessoa.setAdapter(adapter);

        int cont = 1;
        for(Pessoa pessoa: pessoas){
            String pesspaString= cont+"º Pessoa: NOME: " + pessoa.getNome()+ ", CPF: " + pessoa.getCpf()
                    + ", EMAIL: " + pessoa.getEmail() + ", SENHA: " + pessoa.getSenha();
            arrayList.add(pesspaString);
            adapter.notifyDataSetChanged();
        }
        cont=0;

    }*/

}
