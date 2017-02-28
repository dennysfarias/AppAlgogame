package com.algogame.appalgogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TelaSobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);


        Toast.makeText(this, "Que voa, bruxao", Toast.LENGTH_SHORT).show();

    }
}
