package com.projeto.bookfast.bookfast.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.projeto.bookfast.bookfast.R;

/**
 * Created by jadeilson on 27/07/2017.
 */

public class Principal extends Activity implements Runnable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Handler SplashScren = new Handler();
        SplashScren.postDelayed(Principal.this, 2300);
    }

    @Override
    public void run() {
        startActivity(new Intent(Principal.this, TelaLogin.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.layout.activity_tela_principal, menu);
        return true;
    }
}
