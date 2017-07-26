package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;

import java.util.ArrayList;

public class TelaUsuarioComum extends Activity {
    private ListView objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario_comum);
        ReadBancoDados busca = new ReadBancoDados(getApplicationContext());
        ArrayList arrayList = busca.getListaPessoas();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        objeto = (ListView) findViewById(R.id.listaUsuarios);
        objeto.setAdapter(arrayAdapter);
    }
}
