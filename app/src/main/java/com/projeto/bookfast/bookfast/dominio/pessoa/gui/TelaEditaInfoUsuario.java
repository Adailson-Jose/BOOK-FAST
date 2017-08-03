package com.projeto.bookfast.bookfast.dominio.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.dominio.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.negocio.ValidarCampoEditaInformacaoUsuario;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.dominio.pessoa.percistencia.UpdatePessoa;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;

public class TelaEditaInfoUsuario extends AppCompatActivity {
    Button btSalvar;
    EditText editTextNome, editTextEmail;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edita_info_usuario);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        ReadPessoa buscar = new ReadPessoa(getApplicationContext());
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            pessoa = buscar.getPessoa(Long.parseLong(String.valueOf(bundle.get("KEY"))));
        }
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LimparTela limparTela = new LimparTela();
                ViewGroup group = (ViewGroup) findViewById(R.id.raizEditarinfomacaoUsuario);
                ValidarCampoEditaInformacaoUsuario validarCampoEditaInformacaoUsuario = new ValidarCampoEditaInformacaoUsuario();
                UpdatePessoa atualizar = new UpdatePessoa(getApplicationContext());

                if (!validarCampoEditaInformacaoUsuario.ValidarCampoEditaInformacaoUsuario(editTextNome, editTextEmail)){
                    String nome = editTextNome.getText().toString();
                    String email = editTextEmail.getText().toString();
                    pessoa.setNome(nome);
                    pessoa.setEmail(email);
                    atualizar.updatePessoa(pessoa);
                    limparTela.clearForm(group);
                    editTextNome.requestFocus();
                    Toast.makeText(TelaEditaInfoUsuario.this, "INFORMAÇÕES ALTERADAS COM SUCESSO", Toast.LENGTH_SHORT).show();

                    Intent abreTelaInicialUsuarioComum = new Intent(TelaEditaInfoUsuario.this, TelaInicialUsuarioComum.class);
                    abreTelaInicialUsuarioComum.putExtra("KEY", String.valueOf(pessoa.getCpf()));
                    startActivity(abreTelaInicialUsuarioComum);
                }else{
                    Toast.makeText(TelaEditaInfoUsuario.this, "CAMPO INVÁLIDO", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
