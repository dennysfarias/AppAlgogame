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

    DatabaseAlg db = new DatabaseAlg();
    TextView nomeJogadorView, pontuacaoMaxima;
    int ptGeral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DatabaseAlg db = new DatabaseAlg();
        db.inicializarDB(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        nomeJogadorView = (TextView)findViewById(R.id.nome_jogador);
        pontuacaoMaxima = (TextView)findViewById(R.id.pontuacaoTelaMenu);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(TelaMenu.this, TelaInicial.class));
            this.finish();
        }else{

            try{

                //Pegar o nome do jogador do banco de dados para exibição na tela inicial
                Cursor jogador = db.consultJogador();
                jogador.moveToFirst();
                jogador.getString(1);
                nomeJogadorView.setText("Olá, "+jogador.getString(1));
                pontuacaoMaxima.setText("Pontuação máxima: "+jogador.getInt(3));
                ptGeral = jogador.getInt(3);





            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
            }


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



        Intent j = new Intent(Intent.ACTION_SEND);
        j.setType("text/plain");
        j.putExtra(Intent.EXTRA_SUBJECT, "Vamos jogador AlgoGame?");
        j.putExtra(Intent.EXTRA_TEXT, "Minha pontuação no AlgoGame é " +ptGeral+" pontos. " +
                "Quero ver quem irá me alcançar! " +
                "O desafio está lançado! Conheça o app AlgoGame em http://goo.gl/algogame "

        );
        startActivity(Intent.createChooser(j,
                "Compartilhar..."));





    }

    public void irTelaSobre(View v){
        startActivity(new Intent(this, TelaSobre.class));
    }




}
