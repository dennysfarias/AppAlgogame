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

import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.ListIterator;

public class TelaJogoOrden extends AppCompatActivity {
    ArrayList<String> ListaAlg;


    ArrayAdapter ListaAlgAdapt;



    int posicaoObj;
    ProgressBar barraProg;
    int contadorTempo = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo_orden);


        barraProg = (ProgressBar)findViewById(R.id.barraProgresso);
        barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);



        ListaAlg = new ArrayList<>();
        ListaAlgAdapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListaAlg);


        DragSortListView ListaPrincipal = (DragSortListView) findViewById(R.id.ListaPrincipalME);
        ListaPrincipal.setAdapter(ListaAlgAdapt);




        ListaPrincipal.setDropListener(new DragSortListView.DropListener() {
             @Override
            public void drop(int from, int to) {
                String movedItem = ListaAlg.get(from);
                 ListaAlg.remove(from);
                 if(from>to)--from;
                 ListaAlg.add(to, movedItem);
                 ListaAlgAdapt.notifyDataSetChanged();
            }


        });





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



        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Objetivo do Nível")
                .setMessage(objetivo)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        timer.start();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();



    }












    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            contadorTempo = contadorTempo - 1;
            barraProg.setProgress(contadorTempo);

            if(millisUntilFinished/1000 <=15){
                barraProg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

            }else if(millisUntilFinished/1000<=29){
                barraProg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);

            }else if(millisUntilFinished/1000>=30){
                barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

            }else if(millisUntilFinished/1000==0){
                timer.onFinish();
            }
        }


        @Override
        public void onFinish() {
            Perdeu();

        }
    };





    public void Perdeu( /*int pontuação */   ){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Você perdeu")
                .setMessage("Sua pontuação é:" + "N/A")
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Context context = getApplicationContext();

                        startActivity(new Intent(context, TelaMenu.class));

                    }
                });
        builder.show();
        this.finish();


    }






    public void Ganhou(){

        //Calcular a pontuação baseado no tempo

        int pontuação = contadorTempo * 10;



        //Mostrar saudação de vitória
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Você ganhou")
                .setMessage("Parabéns, sua pontuação atual é: " + pontuação)
                .setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //chamar próxima fase ao clicar no botão

                        Context context = getApplicationContext();

                        startActivity(new Intent(context, TelaMenu.class));



                    }
                });
        builder.show();
        timer.cancel();
        this.finish();
    }






    public void verificarCodigo(View v){

        //verifica se a opção selecionada está correta e chama o método para calcular a pontuação e levar para a próxima fase

        if(ListaAlgAdapt.getItem(posicaoObj).equals("...")){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Cuidado!")
                    .setMessage("Você não escolheu nenhuma opção, escolha uma opção para continuar")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Context context = getApplicationContext();
                            dialog.dismiss();



                        }
                    });

            builder.show();




        }else{

            /*
            if(ListaAlgAdapt.getItem(posicaoObj).toString().contains(linhaCorreta)){
                Ganhou();
            }else{
                Perdeu();
            }
            */

        }
    }





}
