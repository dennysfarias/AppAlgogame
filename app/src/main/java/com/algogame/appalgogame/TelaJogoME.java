package com.algogame.appalgogame;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class TelaJogoME extends AppCompatActivity {
    TextView txt_nomeJogador, nivelAtual;
    ArrayList<String> ListaAlg;
    ArrayAdapter<String> ListaAlgAdapt;
    String algoritmoSelecionado;
    String linhaCorreta = "";
    int posicaoObj;
    int opcaoSelecionada = 0;
    String objetivo;
    String op;
    String pontInicial;
    ProgressBar barraProg;
    int contadorTempo = 30;
    DatabaseAlg db = new DatabaseAlg();
    CountDownTimer timer = new CountDownTimer(30 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            contadorTempo = contadorTempo - 1;
            barraProg.setProgress(contadorTempo);
            TextView txtTempoRestante = (TextView) findViewById(R.id.texttemporestante);
            txtTempoRestante.setText("Tempo restante: " + Integer.toString(contadorTempo));
            if (millisUntilFinished / 1000 <= 10) {
                barraProg.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

            } else if (millisUntilFinished / 1000 <= 15) {
                barraProg.getProgressDrawable().setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);

            } else if (millisUntilFinished / 1000 >= 30) {
                barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

            } else if (millisUntilFinished / 1000 == 0) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__jogo__me);

        nivelAtual = (TextView)findViewById(R.id.txt_nivelAtual);


        Intent i = getIntent();
        pontInicial = getIntent().getStringExtra("pontInicial");
        algoritmoSelecionado = i.getStringExtra("algoritmoSelecionado");
        db.inicializarDB(this);
        txt_nomeJogador = (TextView) findViewById(R.id.txt_nomeJogador);

        Cursor jogador = db.consultJogador();
        jogador.moveToFirst();
        jogador.getString(1);
        txt_nomeJogador.setText(jogador.getString(1));
        nivelAtual.setText("Nível: "+algoritmoSelecionado);
        posicaoObj = 0;
        barraProg = (ProgressBar) findViewById(R.id.barraProgresso);
        barraProg.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        ListaAlgAdapt = povoarLVAlg();
        final ListView ListaPrincipal = (ListView) findViewById(R.id.ListaPrincipalME);
        ListaPrincipal.setAdapter(ListaAlgAdapt);

        ListaAlgAdapt.notifyDataSetChanged();

        final Builder builder = new Builder(this);
        builder.setTitle("Objetivo do nível:")
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

                if (ListaPrincipal.getPositionForView(view) == posicaoObj) {


                    mostrarOpcoes(position);
                    ListaAlgAdapt.notifyDataSetChanged();
                }
            }
        });
    }

    //Fim do onCreate
    public void mostrarOpcoes(final int position) {

        final ArrayList<String> listaOp = new ArrayList<>();
        Cursor cursor = db.consultarOpcoes(this, algoritmoSelecionado);
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            listaOp.add(cursor.getString(2));
            if (cursor.getInt(3) == 1) {
                linhaCorreta = cursor.getString(2);
            }
        }

        final ArrayAdapter<String> opcoes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaOp);
        final Builder builder1 = new Builder(this);
        builder1.setTitle("Escolha a opção correta");

        builder1.setSingleChoiceItems(opcoes, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                opcaoSelecionada = which;
                op = opcoes.getItem(which);
                ListaAlgAdapt.remove(ListaAlgAdapt.getItem(position));
                ListaAlgAdapt.insert(position+1 +".  " +"--> " + op, position);
                ListaAlgAdapt.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder1.show();
    }

    public void Perdeu() {

        Builder builder = new Builder(this);
        final int pontIntent = Integer.decode(getIntent().getStringExtra("pontInicial"));
        builder.setTitle("Você perdeu")
                .setMessage("Sua pontuação é:" + pontIntent)
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Context context = getApplicationContext();
                        //Juntar pontuação


                        db.atulizarPontuacao(pontIntent);


                        startActivity(new Intent(context, TelaMenu.class));
                        finish();

                    }
                });
        builder.show();

    }

    public void Ganhou() {

        //Calcular a pontuação baseado no tempo

        final int pontuação = contadorTempo * 10;
        //Mostrar saudação de vitória
        Builder builder = new Builder(this);
        final int pontIntent = Integer.decode(getIntent().getStringExtra("pontInicial"));
        builder.setTitle("Você ganhou")
                .setMessage("Parabéns, sua pontuação atual é: " + pontuação)
                .setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //chamar próxima fase ao clicar no botão

                        Context context = getApplicationContext();


                        //Algoritmoselecionado (Fase) para continuidade
                        int algSelect = Integer.decode(algoritmoSelecionado);
                        algSelect = algSelect +1;
                        String tmpStr = String.valueOf(algSelect);
                        if(algSelect==6){
                            db.atulizarPontuacao(pontIntent);

                            Intent irTelaMenu = new Intent(getApplicationContext(), TelaMenu.class);
                            startActivity(irTelaMenu);
                        }else{
                            Intent i = new Intent(context, TelaJogoME.class);

                            i.putExtra("algoritmoSelecionado", tmpStr);

                            //Juntar pontuação
                            int pontIntent = Integer.decode(pontInicial);
                            pontIntent = pontIntent + pontuação;
                            String strTmp2 = String.valueOf(pontIntent);
                            i.putExtra("pontInicial", strTmp2);

                            startActivity(i);
                            finish();
                        }

                    }
                });
        builder.show();
        timer.cancel();
    }

    public void verificarCodigo(View v) {

        //verifica se a opção selecionada está correta e chama o método para calcular a pontuação e levar para a próxima fase

        if (ListaAlgAdapt.getItem(posicaoObj).contentEquals("...")) {

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

        } else {
            if (ListaAlgAdapt.getItem(posicaoObj).toString().contains(linhaCorreta)) {
                Ganhou();
            } else {
                Perdeu();
            }
        }
    }


    public ArrayAdapter<String> povoarLVAlg() {

        ArrayList lista = new ArrayList();
        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lista);


        //pegando o objetivo do banco
        Cursor cursorObj = db.consultarObjetivo(this, algoritmoSelecionado);
        cursorObj.moveToFirst();
        objetivo = cursorObj.getString(1);


        try {
            //pegando as linhas dos algoritmos do banco
            Cursor cursor = db.consultarAlgoritmo(algoritmoSelecionado);
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                aAdapter.add(cursor.getPosition()+1+ ".  " + cursor.getString(2));
                if (cursor.getString(2).equals("...")) {
                    posicaoObj = cursor.getPosition();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }


        return aAdapter;


    }


}

