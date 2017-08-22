package com.projeto.bookfast.bookfast.aluguel.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;
import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.aluguel.negocio.ValidaEmprestimo;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.gui.TelaInicialUsuarioComum;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class TelaQRcode extends AppCompatActivity {
    private Button scaner_btn, btTelaInicial;
    private Livro livro;
    private Pessoa pessoa;
    private String resultCode;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_qrcode);
        scaner_btn = (Button) findViewById(R.id.scaner_btn);
        btTelaInicial = (Button) findViewById(R.id.btTelaInicial);
        ReadPessoa buscar = new ReadPessoa(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = buscar.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
        }

        scaner_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode(view);
                Toast.makeText(TelaQRcode.this, "Scanner Ativado com sucesso.", Toast.LENGTH_LONG).show();
            }
        });

        btTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreTelaInicialUsuarioComum = new Intent(TelaQRcode.this, TelaInicialUsuarioComum.class);
                abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                startActivity(abreTelaInicialUsuarioComum);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent abreTelaInicialUsuarioComum = new Intent(TelaQRcode.this, TelaInicialUsuarioComum.class);
        abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
        startActivity(abreTelaInicialUsuarioComum);
    }

    public void scanCode(View view) {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(scannerView);
        scannerView.startCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(Result result) {
            resultCode = result.getText();
            Toast.makeText(TelaQRcode.this, "Texto capturado: " + resultCode, Toast.LENGTH_LONG).show();
            if (resultCode != null && isNumeric(resultCode)) {
                ReadLivro buscaLivro = new ReadLivro(getApplicationContext());
                Long isbn = Long.parseLong(resultCode);
                livro = buscaLivro.getLivro(isbn);
                ValidaEmprestimo validaEmprestimo = new ValidaEmprestimo(getApplication());
                if (livro != null) {
                    if (validaEmprestimo.pediEmprestimo(livro, pessoa)) {
                        Toast.makeText(TelaQRcode.this, "Livro alugado com sucesso.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaQRcode.this, "Erro ao tentar alugar um livro.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TelaQRcode.this, "Livro não existe na base de dados.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(TelaQRcode.this, "Código errado.", Toast.LENGTH_LONG).show();
            }
            setContentView(R.layout.activity_tela_qrcode);
            scannerView.stopCamera();
            Intent abreTelaInicialUsuarioComum = new Intent(TelaQRcode.this, TelaInicialUsuarioComum.class);
            abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
            startActivity(abreTelaInicialUsuarioComum);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}