package com.algogame.appalgogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//ACTIVITY = sinônimo de tela
//INTENT = transição entre telas
public class TelaInicial extends AppCompatActivity {

    //declarar componentes dinâmicos:
    EditText editNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //criação do objeto:
        editNome = (EditText) findViewById(R.id.edit_nome);
    }

    public void irTelaMenu(View v) {
        //pegando o valor digitado no campo:
        String nomeDigitado = editNome.getText().toString();

        if (nomeDigitado.length() >= 5){

            //ir para a TelaMenu, levando o nomeDigitado:
            //dever de casa: fazer um controle, caso o usuário não tenha (Pronto)
            //informado um nome

            Intent i = new Intent(this, TelaMenu.class);
            i.putExtra("nomeJogador", nomeDigitado);
            startActivity( i );
            this.finish();

        }else {
            Toast.makeText(this, "Nome muito curto!", Toast.LENGTH_SHORT).show();
        }






    }

}
