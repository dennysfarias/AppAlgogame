package com.algogame.appalgogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class TelaMenu extends AppCompatActivity {



    TextView nomeJogadorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        //abrir a Intent passada da TelaInicial:
        Intent j = getIntent();
        String nomeJogador = j.getStringExtra("nomeJogador");

        //mostrando um alerta
        /*Toast.makeText(this,
                "Nome digitado: " + nomeJogador,
                Toast.LENGTH_SHORT).show();
                */
        nomeJogadorView = (TextView)findViewById(R.id.nome_jogador);

        nomeJogadorView.setText("Olá, "+nomeJogador);



    }



    public void irTelaNovoJogo(View v){
        startActivity(new Intent(this, TelaNovoJogo.class));
    }

    public void irTelaTutorial(View v){
        startActivity(new Intent(this, TelaTutorial.class));
    }

    public void irTelaCompartilharPt(View v){

        //outra coisa

    }

    public void irTelaSobre(View v){
        startActivity(new Intent(this, TelaSobre.class));
    }




}
