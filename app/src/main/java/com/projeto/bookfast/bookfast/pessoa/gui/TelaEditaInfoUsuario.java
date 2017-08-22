package com.projeto.bookfast.bookfast.pessoa.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.UpdatePessoa;

import static com.projeto.bookfast.bookfast.pessoa.dominio.EnumCurso.listaEnumCurso;

public class TelaEditaInfoUsuario extends AppCompatActivity {
    Button btSalvar, btCancelarMudanças2;
    EditText editTextNome, editTextEmail;
    private Pessoa pessoa;
    private Spinner spinnerCurso;
    private String[] listaCurso = listaEnumCurso();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edita_info_usuario);
        btSalvar = (Button) findViewById(R.id.btSalvar);
        btCancelarMudanças2 = (Button) findViewById(R.id.btCancelarMudanças2);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaCurso);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurso = (Spinner) findViewById(R.id.spinnerCurso);
        spinnerCurso.setAdapter(adapter);
        spinnerCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ReadPessoa buscar = new ReadPessoa(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pessoa = buscar.getPessoa(Long.parseLong(String.valueOf(bundle.get("pessoa"))));
            editTextNome.setText(pessoa.getNome());
            editTextEmail.setText(pessoa.getEmail());
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup group = (ViewGroup) findViewById(R.id.raizEditarinfomacaoUsuario);
                UpdatePessoa atualizar = new UpdatePessoa(getApplicationContext());
                boolean resultado = false;
                String cursoTexto = (String) spinnerCurso.getSelectedItem();
                String nomeString = editTextNome.getText().toString();
                String emailString = editTextEmail.getText().toString();
                if (ValidarCampoVazio.isCampoVazio(nomeString)) {
                    resultado = true;
                    editTextNome.setError("Nome inválido!");
                    editTextNome.requestFocus();
                } else if (!ValidarEmail.isEmailValido(emailString)) {
                    resultado = true;
                    editTextEmail.setError("Email inválido!");
                    editTextEmail.requestFocus();
                }
                if (!resultado) {
                    pessoa.setNome(nomeString);
                    pessoa.setEmail(emailString);
                    pessoa.setCurso(cursoTexto);
                    atualizar.updatePessoa(pessoa);
                    LimparTela.clearForm(group);
                    editTextNome.requestFocus();
                    Toast.makeText(TelaEditaInfoUsuario.this, "INFORMAÇÕES ALTERADAS COM SUCESSO", Toast.LENGTH_SHORT).show();
                    Intent abreTelaInicialUsuarioComum = new Intent(TelaEditaInfoUsuario.this, TelaInicialUsuarioComum.class);
                    abreTelaInicialUsuarioComum.putExtra("pessoa", String.valueOf(pessoa.getCpf()));
                    startActivity(abreTelaInicialUsuarioComum);
                } else {
                    Toast.makeText(TelaEditaInfoUsuario.this, "CAMPO INVÁLIDO", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btCancelarMudanças2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
