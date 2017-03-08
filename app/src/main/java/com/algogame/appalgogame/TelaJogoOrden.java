package com.algogame.appalgogame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.ListIterator;

public class TelaJogoOrden extends AppCompatActivity {


    ArrayList<String> ListaAlg;


    StableArrayAdapter ListaAlgAdapt;


    int posicaoObj;
    ProgressBar barraProg;
    int contadorTempo = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo_orden);


        barraProg = (ProgressBar) findViewById(R.id.barraProgresso);
        barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);


        ListaAlg = new ArrayList<>();
        ListaAlgAdapt = new StableArrayAdapter(this, R.layout.text_view, ListaAlg);


        DynamicListView ListaPrincipal = (DynamicListView) findViewById(R.id.ListaPrincipalOrden);



        String objetivo = "Seu algoritmo deverá ler dois valores e calcular a média aritmética " +
                "destes e informar se o aluno foi aprovado ou não, sendo" +
                " a média mínima de aprovação, 7.";


        ListaAlg.add("Início");
        ListaAlg.add("real nota1, nota2;");
        ListaAlg.add("real media;");
        ListaAlg.add("ler nota1, nota2;");
        ListaAlg.add("media = (nota1 + nota2) / 2");
        ListaAlg.add("se(media >= 7) então");
        ListaAlg.add("Imprimir 'Aprovado!';");
        ListaAlg.add("se não;");
        ListaAlg.add("Imprimir 'Reprovado!';");
        ListaAlg.add("fim se");
        ListaAlg.add("imprimir 'Média =' +media;");
        ListaAlg.add("fim");


        ListaAlgAdapt.notifyDataSetChanged();



        ListaPrincipal.setCheeseList(ListaAlg);
        ListaPrincipal.setAdapter(ListaAlgAdapt);
        ListaPrincipal.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



    }
}