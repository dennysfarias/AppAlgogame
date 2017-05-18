package com.algogame.appalgogame;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class DatabaseAlg extends AppCompatActivity {
    SQLiteDatabase db;


    public void inicializarDB(Context context){
        db = context.openOrCreateDatabase("algoGame", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS" +
                " tab_jogador (_id integer primary key autoincrement ,"+
                " nome text, fase_id integer, pontuacao integer, tempo_total text)");

        db.execSQL("CREATE TABLE IF NOT EXISTS tab_algoritmos (" +
                "_id integer primary key autoincrement," +
                "objetivo text)");




    }

    public void addJogador(Context context, ContentValues dados){
        db.insert("tab_jogador", null, dados);
        Toast.makeText(context, "Jogador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void deleteJogador(){

    }

    public void updateJogador() {

    }

    public Cursor consultJogador(){
       Cursor jogador = db.query("tab_jogador", null, null, null, null, null, null);
        return jogador;
    }


    public void addAlgoritmo(){

    }
    public void deleteAlgoritmo(){

    }

    public void updateAlgoritmo(){

    }

    public void consultarAlgoritmo(){

    }







}
