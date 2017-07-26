package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.Pessoa;
import com.projeto.bookfast.bookfast.persistencia.CreatBancoDados;
import com.projeto.bookfast.bookfast.persistencia.DeletBancoDados;
import com.projeto.bookfast.bookfast.persistencia.ReadBancoDados;
import com.projeto.bookfast.bookfast.persistencia.UpdateBancoDados;

import java.util.ArrayList;

public class Teste extends Activity {
    Button btnadd, btnEdt, btnRemove, btnRemoveTable, btnVerReg;
    EditText edtNome, editEmail, editCpf, editSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        btnadd = (Button) findViewById(R.id.btnadd);
        btnEdt = (Button) findViewById(R.id.btnEdt);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnRemoveTable = (Button) findViewById(R.id.btnRemoveTable);
        btnVerReg = (Button) findViewById(R.id.btnVerReg);

        edtNome = (EditText) findViewById(R.id.edtNome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editCpf = (EditText) findViewById(R.id.editCpf);
        editSenha = (EditText) findViewById(R.id.editSenha);

        CreatBancoDados db = new CreatBancoDados(getApplicationContext());
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pessoa p = new Pessoa();
                p.setNome(edtNome.getText().toString());
                p.setEmail(editEmail.getText().toString());
                p.setCpf(Integer.parseInt(editCpf.getText().toString()));
                p.setSenha(editSenha.getText().toString());

                UpdateBancoDados u = new UpdateBancoDados(getApplicationContext());
                if (u.insertPessoa(p)) {
                    Toast.makeText(Teste.this, "Pessoa foi inserida com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Teste.this, "Erro ao inserir pessoa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pessoa p = new Pessoa();
                p.setNome(edtNome.getText().toString());
                p.setEmail(editEmail.getText().toString());
                p.setCpf(Integer.parseInt(editCpf.getText().toString()));
                p.setSenha(editSenha.getText().toString());
                UpdateBancoDados u = new UpdateBancoDados(getApplicationContext());
                if (u.updatePessoa(p)) {
                    Toast.makeText(Teste.this, "Pessoa foi atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Teste.this, "Erro ao atualizar pessoa", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pessoa p = new Pessoa();
                p.setNome(edtNome.getText().toString());
                p.setEmail(editEmail.getText().toString());
                p.setCpf(Integer.parseInt(editCpf.getText().toString()));
                p.setSenha(editSenha.getText().toString());
                DeletBancoDados d = new DeletBancoDados(getApplicationContext());
                if (d.deletePessoa(p)) {
                    Toast.makeText(Teste.this, "Pessoa foi removida com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Teste.this, "Erro ao remover pessoa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRemoveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeletBancoDados d = new DeletBancoDados(getApplicationContext());
                if (d.deleteTablePessoa()) {
                    Toast.makeText(Teste.this, "Tabela foi removida com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Teste.this, "Erro ao remover tabela", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnVerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadBancoDados r = new ReadBancoDados(getApplicationContext());

                ArrayList<Pessoa> pArray = r.getListaPessoas();

                for (int i = 0; i < pArray.size(); i++) {
                    Pessoa p = pArray.get(i);
                    System.out.println("NOME: " + p.getNome() + "    CPF: " + p.getCpf() + "  SENHA: " + p.getSenha() + " EMAIL: " + p.getEmail() + " ID: " + p.getId());
                }

            }
        });

    }
}