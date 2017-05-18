package com.algogame.appalgogame;

import android.database.Cursor;
import android.view.View;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TelaJogoME extends AppCompatActivity {



    TextView txt_nomeJogador;

    ArrayList<String> ListaAlg;
    ArrayAdapter<String> ListaAlgAdapt;

    String linhaCorreta = "media = (nota1 + nota2) / 2";
    int posicaoObj;
    int opcaoSelecionada = 0;
    String op;
    ProgressBar barraProg;
    int contadorTempo = 30;

    DatabaseAlg db = new DatabaseAlg();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__jogo__me);


        db.inicializarDB(this);

        txt_nomeJogador = (TextView)findViewById(R.id.txt_nomeJogador);

        Cursor jogador = db.consultJogador();
        jogador.moveToFirst();
        jogador.getString(1);
        txt_nomeJogador.setText(jogador.getString(1));

        posicaoObj = 0;
        barraProg = (ProgressBar)findViewById(R.id.barraProgresso);
        barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        ListaAlg = new ArrayList<>();
        ListaAlgAdapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListaAlg);

        final ListView ListaPrincipal = (ListView) findViewById(R.id.ListaPrincipalME);

        ListaPrincipal.setAdapter(ListaAlgAdapt);

        String objetivo = "Seu algoritmo deverá ler dois valores e calcular a média aritmética " +
                "destes e informar se o aluno foi aprovado ou não, sendo" +
                " a média mínima de aprovação, 7.";


        ListaAlg.add("Início");
        ListaAlg.add("real nota1, nota2;");
        ListaAlg.add("real media;");
        ListaAlg.add("ler nota1, nota2;");
        ListaAlg.add("...");
        ListaAlg.add("se(media >= 7) então");
        ListaAlg.add("Imprimir 'Aprovado!';");
        ListaAlg.add("se não;");
        ListaAlg.add("Imprimir 'Reprovado!';");
        ListaAlg.add("fim se");
        ListaAlg.add("imprimir 'Média =' +media;");
        ListaAlg.add("fim");



        ListaAlgAdapt.notifyDataSetChanged();



        final Builder builder = new Builder(this);
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

        ListaPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ListaPrincipal.getItemAtPosition(position).toString().contains("...")) {
                    mostrarOpcoes(position);
                    posicaoObj  = position;
                    ListaAlgAdapt.notifyDataSetChanged();
                }
            }
        });
    }

    //Fim do onCreate
    public void mostrarOpcoes(final int position) {

        final ArrayList<String> listaOp = new ArrayList<>();
        listaOp.add("...");
        listaOp.add("media = (nota1 + nota2) / 2");
        listaOp.add("media == nota1 + nota2 / 2");
        listaOp.add("nota = media - nota1 + nota 2");

        final ArrayAdapter<String> opcoes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaOp);


        final Builder builder1 = new Builder(this);
        builder1.setTitle("Escolha a opção correta");

        builder1.setSingleChoiceItems(opcoes, -1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                opcaoSelecionada = which;
                op = opcoes.getItem(which);

                ListaAlg.set(position, "... <- " + op);
                ListaAlgAdapt.notifyDataSetChanged();

                dialog.dismiss();

            }
        });
        builder1.show();
    }

    CountDownTimer timer = new CountDownTimer(30*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            contadorTempo = contadorTempo - 1;
            barraProg.setProgress(contadorTempo);
            TextView txtTempoRestante = (TextView)findViewById(R.id.texttemporestante);
            txtTempoRestante.setText("Tempo restante: "+ Integer.toString(contadorTempo));
            if(millisUntilFinished/1000 <=10){
                barraProg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

            }else if(millisUntilFinished/1000<=15){
                barraProg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);

            }else if(millisUntilFinished/1000>=30){
                barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

            }else if(millisUntilFinished/1000==0){
                barraProg.setProgress(0);
                timer.onFinish();


            }
        }




        @Override
        public void onFinish() {
            barraProg.setProgress(0);
            Perdeu();
        }
    };

    public void Perdeu( /*int pontuação */   ){

        Builder builder = new Builder(this);

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

    }
    public void Ganhou(){

        //Calcular a pontuação baseado no tempo

        int pontuação = contadorTempo * 10;
        //Mostrar saudação de vitória
        Builder builder = new Builder(this);

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
    }
    public void verificarCodigo(View v){

        //verifica se a opção selecionada está correta e chama o método para calcular a pontuação e levar para a próxima fase

        if(ListaAlgAdapt.getItem(posicaoObj).contentEquals("...")){

            Builder builder = new Builder(this);

            builder.setTitle("Cuidado!")
                    .setMessage("Nenhuma alternativa selecionada, escolha uma para continuar")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Context context = getApplicationContext();
                            dialog.dismiss();

                        }
                    });

            builder.show();

        }else{
            if(ListaAlgAdapt.getItem(posicaoObj).toString().contains(linhaCorreta)){
                Ganhou();
            }else{
                Perdeu();
            }
        }
    }






}

