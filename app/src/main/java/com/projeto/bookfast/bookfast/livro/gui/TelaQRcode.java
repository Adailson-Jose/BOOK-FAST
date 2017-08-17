package com.projeto.bookfast.bookfast.livro.gui;

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
import com.projeto.bookfast.bookfast.livro.dominio.Livro;

public class TelaQRcode extends AppCompatActivity {
    private Button scaner_btn;
    private Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_qrcode);
        scaner_btn = (Button) findViewById(R.id.scaner_btn);
        final Activity activity = this;
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
        if(result!=null){
            if (result.getContents()==null){
                Toast.makeText(this, "Você Cancelou o Scaneamento", Toast.LENGTH_LONG).show();
            }
            else{
//                ReadLivro readLivro = new ReadLivro(getApplicationContext());
//                ValidaEmprestimo validaEmprestimo = new ValidaEmprestimo();
//                livroaux = readLivro.getLivro(Long.parseLong(result.getContents()));
//                validaEmprestimo.pediemprestimo(livroaux);
//                Intent abreTelaAlugarLivro = new Intent(TelaQRcode.this, TelaAlugarLivro.class);
 //               abreTelaAlugarLivro.putExtra("livro", String.valueOf(livroaux.getIsbn()));
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Cheguei aqui", Toast.LENGTH_LONG).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}