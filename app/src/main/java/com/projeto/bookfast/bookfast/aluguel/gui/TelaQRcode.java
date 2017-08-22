package com.projeto.bookfast.bookfast.aluguel.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.negocio.ValidaEmprestimo;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

public class TelaQRcode extends AppCompatActivity {
    private Button scaner_btn;
    private Livro livro;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_qrcode);
        scaner_btn = (Button) findViewById(R.id.scaner_btn);
        final Activity activity = this;
        final ReadLivro buscarLivro = new ReadLivro(getApplicationContext());
        final ReadPessoa buscarPessoa = new ReadPessoa(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            livro = buscarLivro.getLivro(Long.parseLong(String.valueOf(bundle.get("livro"))));
            pessoa = buscarPessoa.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
        }


        scaner_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Você Cancelou o Scaneamento", Toast.LENGTH_LONG).show();
            } else {
               int resultado = resultCode;
                if ((livro.getIsbn()).equals(String.valueOf(resultado))) {
                    ReadLivro readLivro = new ReadLivro(getApplicationContext());
                    ValidaEmprestimo validaEmprestimo = new ValidaEmprestimo(getApplication());
                    livro = readLivro.getLivro(livro.getIsbn());
                    if (validaEmprestimo.pediEmprestimo(livro, pessoa)) {
                        Toast.makeText(TelaQRcode.this, "Livro alugado com sucesso.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaQRcode.this, "Erro ao tentar alugar um livro.", Toast.LENGTH_LONG).show();

                    }
                 Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                } else {

                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }
}