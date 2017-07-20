package com.projeto.bookfast.book_fast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class TelaInicial extends AppCompatActivity {
    ListView listViewPessoa;
    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    BancoDados bd= new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tala_inicial);
    }

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

    }
}
