package com.algogame.appalgogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TelaSobre extends AppCompatActivity {


    TextView txtTelaFimJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);


        txtTelaFimJogo = (TextView)findViewById(R.id.textViewFinal);

    }
}
