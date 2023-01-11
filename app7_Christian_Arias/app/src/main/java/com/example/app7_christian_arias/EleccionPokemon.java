package com.example.app7_christian_arias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import PokemonPackage.Pokemon;
import UI_Elements.RelativeLayoutButton;

public class EleccionPokemon extends AppCompatActivity
        implements DialogoPonerMote.RespuestaDialogoMote,
        DialogoEleccionPokemon.RespuestaEleccionPokemon,
        View.OnClickListener {

    DialogoEleccionPokemon dialogoEleccionPokemon;
    DialogoPonerMote dialogoPonerMote;
    Pokemon[] pokemonDisponibles = {Pokemon.BULBASUR(), Pokemon.CHARMANDER(), Pokemon.SQUIRTLE()};
    Pokemon pokemonElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_pokemon);

        RelativeLayoutButton buttonBulbasur = new RelativeLayoutButton(this, R.id.pokeball_bulbasur);
        RelativeLayoutButton buttonCharmander = new RelativeLayoutButton(this, R.id.pokeball_charmander);
        RelativeLayoutButton buttonSquirtle = new RelativeLayoutButton(this, R.id.pokeball_squirtle);

        buttonBulbasur.setOnClickListener(this);
        buttonCharmander.setOnClickListener(this);
        buttonSquirtle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Pokemon pokemonElegido = Pokemon.CHARMANDER();
        if (view.getId() == R.id.pokeball_bulbasur) pokemonElegido = pokemonDisponibles[0];
        else if (view.getId() == R.id.pokeball_charmander) pokemonElegido = pokemonDisponibles[1];
        else if (view.getId() == R.id.pokeball_squirtle) pokemonElegido = pokemonDisponibles[2];
        dialogoEleccionPokemon = new DialogoEleccionPokemon(pokemonElegido);
        dialogoEleccionPokemon.show(getSupportFragmentManager(), "Elecion");
    }

    @Override
    public void onRespuesta(Pokemon pokemonElegido) {
        dialogoEleccionPokemon.dismiss();
        if (pokemonElegido != null) {
            this.pokemonElegido = pokemonElegido;
            dialogoPonerMote = new DialogoPonerMote();
            dialogoPonerMote.show(getSupportFragmentManager(), "Poner Mote");
        }
    }

    @Override
    public void onRespuestaMote(String respuesta) {
        if (respuesta != null)
            this.pokemonElegido.setMote(respuesta);
        Intent intent = new Intent(this, BatallaPokemon.class);
        intent.putExtra("pokemonElegido", pokemonElegido);
        startActivity(intent);

    }
}