package com.example.app7_christian_arias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

import PokemonPackage.Pokemon;
import UI_Elements.TypeFaceStringMaker;


public class DialogoEleccionPokemon extends DialogFragment implements View.OnClickListener {
    RespuestaEleccionPokemon respuestaDialogoJava;
    Pokemon pokemonElegido;

    public DialogoEleccionPokemon(Pokemon pokemonElegido) {
        super();
        this.pokemonElegido = pokemonElegido;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String nombrePokemon = getResources().getString(this.pokemonElegido.getNombre());

        TypeFaceStringMaker typeFaceStringMaker = new TypeFaceStringMaker(R.font.pkmndp_peter_o_and_mr_gela);
        builder.setTitle(typeFaceStringMaker.build(getContext(), getString(R.string.preguntaEleccion) +" "+ nombrePokemon + "?"));

        View constraintLayout = getLayoutInflater().inflate(R.layout.dialog_confirmar_pokemon, null);
        ImageView gifPokemon = constraintLayout.findViewById(R.id.imageConfirmacion);
        int imagenPokemon = this.pokemonElegido.getImagen();
        Glide.with(this).asGif().load(imagenPokemon).into(gifPokemon);

        constraintLayout.findViewById(R.id.buttonSI).setOnClickListener(this);
        constraintLayout.findViewById(R.id.buttonNO).setOnClickListener(this);

        if (this.pokemonElegido.isShiny())
            Toast.makeText(getContext(),typeFaceStringMaker.build(getContext(), getString(R.string.mensajeShiny)), Toast.LENGTH_SHORT).show();

        builder.setView(constraintLayout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonNO) respuestaDialogoJava.onRespuesta(null);
        if (view.getId() == R.id.buttonSI) respuestaDialogoJava.onRespuesta(this.pokemonElegido);
    }

    public interface RespuestaEleccionPokemon {
        void onRespuesta(Pokemon pokemonElegido);
    }

    public void onAttach(Context activity) {
        super.onAttach(activity);
        respuestaDialogoJava = (RespuestaEleccionPokemon) activity;
    }


}