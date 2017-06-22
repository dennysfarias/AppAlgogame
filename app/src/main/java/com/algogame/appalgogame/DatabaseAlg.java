package com.algogame.appalgogame;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


public class DatabaseAlg extends AppCompatActivity {
    SQLiteDatabase db;


    public void inicializarDB(Context context){


        try{
            db = context.openOrCreateDatabase("algoGame", Context.MODE_PRIVATE, null);

            db.execSQL("CREATE TABLE IF NOT EXISTS" +
                    " tab_jogador (_idJogador integer primary key autoincrement ,"+
                    " nome text, fase_id integer, pontuacao integer, tempo_total integer)");

            db.execSQL("CREATE TABLE IF NOT EXISTS tab_algoritmos (" +
                    "_idAlgoritmo integer primary key autoincrement," +
                    "objetivo text)");


            db.execSQL("CREATE TABLE IF NOT EXISTS tab_linhasAlg" +
                    "(_idLinhasAlgoritmo integer primary key autoincrement, " +
                    "idAlgoritmo integer, " +
                    "linhas text," +
                    "oculta int," +
                    "FOREIGN KEY (idAlgoritmo) REFERENCES tab_algoritmos(_idAlgoritmo)" +
                    ")");


            db.execSQL("CREATE TABLE IF NOT EXISTS tab_opcoesCorretas" +
                    "(_id integer primary key autoincrement, idAlgoritmo integer, opcoes text, correta int, " +
                    "FOREIGN KEY (idAlgoritmo) REFERENCES tab_algoritmos(_idAlgoritmo))");



        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void addJogador(Context context, ContentValues dados){
        try{
            db.insert("tab_jogador", null, dados);
            Toast.makeText(context, "Jogador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }


        addAlgoritmo();





    }

    public Cursor consultJogador(){
       Cursor jogador = db.query("tab_jogador", null, null, null, null, null, null);
        return jogador;
    }


    public void addAlgoritmo(){

        //Algoritmo 1
        try {
            //Objetivo
            db.execSQL("INSERT INTO tab_algoritmos values (?, 'Calcular a média aritmética de dois valores " +
                    "e retonar o status de aprovação do aluno, sendo a média minima de aprovação, 7.');");


            //Linhas
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'Início', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'real nota1, nota2;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'real media;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'ler nota1, ler nota2;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, '...', 1);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'se (media >= 7) então', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'Imprimir \"Aprovado\";', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'se não', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'Imprimir \"Reprovado\";', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'fim se', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'Imprimir Media = +media;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 1, 'fim', 0);");

            //Alternativas
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 1, 'media = (nota1 + nota2) / 2;',  1);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 1, 'media == nota1 + nota2 / 2;',   0);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 1, 'nota = media - nota1 + nota 2;', 0);");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }



        //Algoritmo 2
        try{
            //Objetivo
            db.execSQL("INSERT INTO tab_algoritmos values (?, 'Seu algoritmo deverá efetuar a " +
                    "leitura de um valor que esteja entre 1 e 9, e informar ao usuário" +
                    " se o valor está ou não na faixa permitida.');");

            //Linhas
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'Início', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'inteiro valor;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'escreva: (\"Digite um valor: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'ler valor;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, '...', 1);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'escreva: (\"O valor está na faixa permitida: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'se não', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'escreva: (\"O valor não está na faixa permitida: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'fim se', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 2, 'fim', 0);");


            //Alternativas
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 2, 'se(valor >=1 ou valor <=9) então',   0);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 2, 'se(valor >10 e valor <0) então',   0);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 2, 'se(valor >=1 e valor <=9) então',   1);");



        }catch (Exception e){
            e.printStackTrace();
        }




        //Algoritmo 3
        try{
            //Objetivo
            db.execSQL("INSERT INTO tab_algoritmos values (?, 'Este algoritmo deverá selecionar o maior" +
                    " de vários valores inseridos pelo usuário. A condição de parada do laço é a entrada do valor \"0\"');");

            //Linhas
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'Início', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, '...', 1);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'repita', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'Escreva(\"Digite um número positivo maior que 0\");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'leia x;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'se (x>maior) entao', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'maior <- x;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'fim se', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'ate n = 0;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'Escreva(\"O maior número é: \", maior)', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 3, 'Fim', 0);");


            //Alternativas
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 3, 'maior, x inteiro;',   0);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 3, 'inteiro x, maior;',   1);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 3, 'inteiro x, maior',   0);");



        }catch (Exception e){
            e.printStackTrace();
        }



        //Algoritmo 4
        try{
            //Objetivo
            db.execSQL("INSERT INTO tab_algoritmos values (?, 'Este algoritmo deverá ler dois valores" +
                    " e efetuar a troca dos valores, de forma que o valor de A fique em B e o de B em A e apresentar os valores trocados.');");

            //Linhas
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'Início', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'inteiro a, b, troca;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'Escreva(\"Digite o valor de A: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'ler a', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'Escreva(\"Digite o valor de B: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'ler b', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'troca <- a;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, '...', 1);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'b <- troca;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'Escreva(\"O novo valor de A é: \", a);', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'Escreva(\"O novo valor de B é: \", b);', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 4, 'Fim', 0);");


            //Alternativas
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 4, 'a <- b;',   1);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 4, 'troca <- b;',   0);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 4, 'b <- a;',   0);");


        }catch (Exception e){
            e.printStackTrace();
        }


        //Algoritmo 5
        try{
            //Objetivo
            db.execSQL("INSERT INTO tab_algoritmos values (?, 'Este algoritmo irá receber o valor de custo de um produto" +
                    " e retornar o valor de venda deste, tomando como base do cálculo um percentual informado pelo usuário.');");

            //Linhas
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'Início', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'real custo, venda percent;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'Escreva(\"Digite o custo do produto: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'leia custo;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'Escreva(\"Digite o percentual de acrescimo: \");', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'leia percent;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'percent <- (percent/100)*custo;', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, '...', 1);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'Escreva(\"O valor de venda é: \", venda);', 0);");
            db.execSQL("INSERT INTO tab_linhasAlg values (?, 5, 'Fim', 0);");



            //Alternativas
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 5, 'venda <- custo + percent;',   1);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 5, 'custo <- venda + percent;',   0);");
            db.execSQL("INSERT INTO tab_opcoesCorretas values (?, 5, 'venda <- custo * percent;',   0);");


        }catch (Exception e){
            e.printStackTrace();
        }




    }



    public Cursor consultarAlgoritmo(String algoritmoSelecionado){

        String where = "idAlgoritmo = '"+algoritmoSelecionado+"'";

        try{
            Cursor cursor = db.query("tab_LinhasAlg", null, where, null, null, null, null);
            return  cursor;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Errrrrrrrrrrrrrrrow: "+e);
            return null;
        }
    }


    public Cursor consultarObjetivo(Context context, String idAlgoritmo){
        String where = "_idAlgoritmo ='"+idAlgoritmo+"'";
        try{

            Cursor cursor = db.query("tab_algoritmos", null, where, null, null, null, null);
            return cursor;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }



    }

    public Cursor consultarOpcoes(Context context, String idAlgoritmo){
        try{
            String where = "idAlgoritmo= '"+idAlgoritmo+"'";
            Cursor cursor = db.query("tab_opcoesCorretas", null, where, null, null, null, null);
            return cursor;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            return null;
        }


    }

    public void atulizarPontuacao(int pontuacao){

        String where = "_idJogador = '"+1+"'";
        Cursor ponnt = db.query("tab_jogador", null, where, null, null, null, null);
        ponnt.moveToFirst();
        int pontTemp = ponnt.getInt(3);



        if(pontuacao >= pontTemp){
            try{
                ContentValues dados = new ContentValues();
                dados.put("pontuacao", pontuacao);
                db.update("tab_jogador", dados, null, null);
            }catch (Exception e){
                e.printStackTrace();
                Log.d("Erro: ", e.getMessage());
            }
        }




    }




}
