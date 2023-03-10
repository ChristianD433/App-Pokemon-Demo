package com.example.app7_christian_arias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bienvenida extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        Button botonComenzar = findViewById(R.id.buttonComenzar);
        botonComenzar.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, EleccionPokemon.class);
        startActivity(intent);
    }

}