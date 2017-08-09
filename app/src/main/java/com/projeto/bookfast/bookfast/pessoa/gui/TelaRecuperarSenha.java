package com.projeto.bookfast.bookfast.pessoa.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.negocio.LimparTela;
import com.projeto.bookfast.bookfast.negocio.ValidarCampoVazio;
import com.projeto.bookfast.bookfast.negocio.ValidarCpf;
import com.projeto.bookfast.bookfast.negocio.ValidarEmail;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.pessoa.percistencia.UpdatePessoa;

public class TelaRecuperarSenha extends Activity {
    EditText editCPF, editNovaSenha, editEmail;
    Button btAlterarSenha, btCancelarAlteracao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__recuperar__senha);
        editCPF = (EditText) findViewById(R.id.editCPF);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        editEmail = (EditText) findViewById(R.id.editEmail);

        btAlterarSenha = (Button) findViewById(R.id.btAlterarSenha);
        btCancelarAlteracao = (Button) findViewById(R.id.btCancelarAlteracao);
        btAlterarSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ReadPessoa buscar = new ReadPessoa(getApplicationContext());
                UpdatePessoa atualizar = new UpdatePessoa(getApplicationContext());
                ViewGroup group = (ViewGroup) findViewById(R.id.raizRecuperarSenha);
                LimparTela limparTela = new LimparTela();
                String cpfStr = editCPF.getText().toString();
                String emailStr = editEmail.getText().toString();
                String senhaStr = editNovaSenha.getText().toString();
                boolean resultado = false;
                if (!ValidarCpf.validarCpf(cpfStr)) {
                    resultado = true;
                    editCPF.setError("Campo CPF inválido!");
                    editCPF.requestFocus();
                } else if (!ValidarEmail.isEmailValido(emailStr)) {
                    resultado = true;
                    editEmail.setError("Campo email inválido!");
                    editEmail.requestFocus();
                } else if (ValidarCampoVazio.isCampoVazio(senhaStr)) {
                    resultado = true;
                    editNovaSenha.setError("Campo senha inválido!");
                    editNovaSenha.requestFocus();
                }
                if (!resultado) {
                    Pessoa pessoa;
                    String loginCpf = editCPF.getText().toString();
                    String novaSenha = editNovaSenha.getText().toString();
                    String email = editEmail.getText().toString();
                    limparTela.clearForm(group);
                    editCPF.requestFocus();
                    pessoa = buscar.getPessoa(Long.parseLong(loginCpf));
                    if (pessoa != null && pessoa.getSenha().equals(novaSenha)) {
                        pessoa.setSenha(novaSenha);
                        atualizar.updatePessoa(pessoa);
                        Toast.makeText(TelaRecuperarSenha.this, R.string.SenhaAtualizada, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(TelaRecuperarSenha.this, R.string.SenhaNaoAtualizada, Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(TelaRecuperarSenha.this, R.string.FaltaPreenchimento, Toast.LENGTH_SHORT).show();

                }
            }

        });

        btCancelarAlteracao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
    }

}