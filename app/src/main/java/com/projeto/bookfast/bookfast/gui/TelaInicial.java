package com.projeto.bookfast.bookfast.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;


import java.util.ArrayList;


public class TelaInicial extends AppCompatActivity {
    ListView listViewPessoa;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    TextView mensagem;

    // Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tala_inicial);
        this.mensagem = (TextView) this.findViewById(R.id.mensagem);
        //final PessoaDao pessoaDao= new PessoaDao(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String mensagemRecebida = bundle.get("KEY").toString();
            //pessoa = pessoaDao.getPessoa(Long.parseLong(mensagemRecebida));
            //String dadosPessoa= pessoa.getId()+" - "+ pessoa.getNome()+" - "+ pessoa.getCpf()+" - "+pessoa.getEmail()+" - " +pessoa.getSenha();
            this.mensagem.setText(mensagemRecebida);
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
