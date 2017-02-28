package com.algogame.appalgogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaNovoJogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_novo_jogo);



    }


    public void novoJogoOrden(View v){

        startActivity(new Intent(this, TelaJogoOrden.class));
        this.finish();
    }


    public void novoJogoMult(View v){

        startActivity(new Intent(this, TelaJogoME.class));
        this.finish();

    }

}
