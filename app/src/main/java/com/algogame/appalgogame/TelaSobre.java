package com.algogame.appalgogame;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaSobre extends AppCompatActivity {

    TextView txtTelaFimJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);


        txtTelaFimJogo = (TextView)findViewById(R.id.textViewFinal);





    }



    public void contateNos(View view){
        Intent j = new Intent(Intent.ACTION_SEND);
        j.setData(Uri.parse("email"));
        String [] s = {"dennysfarias96@gmail.com", "renato.telecom@gmail.com", "denilson9617@gmail.com"};
        j.putExtra(Intent.EXTRA_EMAIL, s);
        j.putExtra(Intent.EXTRA_SUBJECT, "Feedback AlgoGame");
        j.setType("message/rfc822");
        Intent chooser = Intent.createChooser(j, "Enviar...");
        startActivity(chooser);

    }

}
