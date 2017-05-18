package com.algogame.appalgogame;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;


public class TelaMenu extends AppCompatActivity {


    TextView nomeJogadorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseAlg db = new DatabaseAlg();
        db.inicializarDB(this);

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






        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(TelaMenu.this, TelaInicial.class));
        }else{
            //Pegar o nome do jogador do banco de dados para exibição na tela inicial
            Cursor jogador = db.consultJogador();
            jogador.moveToFirst();
            jogador.getString(1);
            nomeJogadorView.setText("Olá, "+jogador.getString(1));

        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();




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
