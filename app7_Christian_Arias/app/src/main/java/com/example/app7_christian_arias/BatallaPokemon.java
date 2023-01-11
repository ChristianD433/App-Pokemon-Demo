package com.example.app7_christian_arias;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Random;

import BattleProcesation.Animacion;
import BattleProcesation.Pokemon_HPBar;
import BattleProcesation.Turno;
import PokemonPackage.AtaquePokemon;
import PokemonPackage.Pokemon;
import UI_Elements.RelativeAtaquePokemonLayoutButton;
import UI_Elements.TypeFaceStringMaker;


public class BatallaPokemon extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ArrayList<SpannableString> listaMensajes = new ArrayList<>();
    private ArrayList<ArrayList<SpannableString>> listaRegistros = new ArrayList<>();
    private Pokemon_HPBar progressBarMiVida;
    private Pokemon_HPBar progressBarVidaEnemigo;
    private int delay;
    private MediaPlayer mediaPlayer;
    private RelativeAtaquePokemonLayoutButton buttonAtaque1;
    private RelativeAtaquePokemonLayoutButton buttonAtaque2;
    private RelativeAtaquePokemonLayoutButton buttonAtaque3;
    private RelativeAtaquePokemonLayoutButton buttonAtaque4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalla_pokemon);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musica_batalla);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        listaRegistros = new ArrayList<>();

        Switch switchSpeed = findViewById(R.id.switchSpeed);
        Switch switchPower = findViewById(R.id.switchPower);
        switchPower.setOnCheckedChangeListener(this);
        switchSpeed.setOnCheckedChangeListener(this);

        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(this);

        Button buttonRegistro = findViewById(R.id.buttonRegistro);
        buttonRegistro.setOnClickListener(this);

        updateMiPokemon();
        updatePokemonEnemigo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    private void updateMiPokemon() {
        Pokemon miPokemon = (Pokemon) getIntent().getSerializableExtra("pokemonElegido");
        miPokemon.curarVida();
        ImageView gifMiPokemon = findViewById(R.id.miPokemon);
        int imagenPokemon = miPokemon.getImagenEspalda();
        Glide.with(this).asGif().load(imagenPokemon).diskCacheStrategy(DiskCacheStrategy.NONE).into(gifMiPokemon);

        TextView textViewMiNombre = findViewById(R.id.nombreMiPokemon);
        setPokemonName(textViewMiNombre, miPokemon);

        this.progressBarMiVida = new Pokemon_HPBar(findViewById(R.id.vidaMiPokemon), miPokemon);
        this.progressBarMiVida.actualizarHPBar();

        this.buttonAtaque1 = new RelativeAtaquePokemonLayoutButton(this, R.id.buttonAtaque1, miPokemon.getAtaquesPokemon()[0]);
        this.buttonAtaque2 = new RelativeAtaquePokemonLayoutButton(this, R.id.buttonAtaque2, miPokemon.getAtaquesPokemon()[1]);
        this.buttonAtaque3 = new RelativeAtaquePokemonLayoutButton(this, R.id.buttonAtaque3, miPokemon.getAtaquesPokemon()[2]);
        this.buttonAtaque4 = new RelativeAtaquePokemonLayoutButton(this, R.id.buttonAtaque4, miPokemon.getAtaquesPokemon()[3]);

        if (buttonAtaque1.getAtaquePokemon() != null) buttonAtaque1.setOnClickListener(this);
        if (buttonAtaque2.getAtaquePokemon() != null) buttonAtaque2.setOnClickListener(this);
        if (buttonAtaque3.getAtaquePokemon() != null) buttonAtaque3.setOnClickListener(this);
        if (buttonAtaque4.getAtaquePokemon() != null) buttonAtaque4.setOnClickListener(this);
    }

    private void updatePokemonEnemigo() {
        int idPokemonRival = new Random().nextInt(4) + 1;
        Pokemon pokemonRival = Pokemon.buildPokemonbyId(idPokemonRival);
        pokemonRival.curarVida();
        ImageView gifPokemonRival = findViewById(R.id.pokemonRival);
        int imagenPokemonRival = pokemonRival.getImagen();
        Glide.with(this).asGif().load(imagenPokemonRival).diskCacheStrategy(DiskCacheStrategy.NONE).into(gifPokemonRival);

        TextView textViewNombreEnemigo = findViewById(R.id.nombreEnemigo);
        setPokemonName(textViewNombreEnemigo, pokemonRival);

        this.progressBarVidaEnemigo = new Pokemon_HPBar(findViewById(R.id.vidaEnemigo), pokemonRival);
        this.progressBarVidaEnemigo.actualizarHPBar();
    }

    private void setPokemonName(TextView textView, Pokemon pokemon) {
        String mote = pokemon.getMote();
        if (pokemon.getMote() != null) textView.setText(mote);
        else textView.setText(pokemon.getNombre());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Switch switchSpeed = findViewById(R.id.switchSpeed);
        Switch switchPower = findViewById(R.id.switchPower);
        if (buttonView.getId() == switchSpeed.getId()) {
            if (isChecked) switchPower.setChecked(false);
        } else if (buttonView.getId() == switchPower.getId()) {
            if (isChecked) switchSpeed.setChecked(false);
        }
    }

    public void onClick(View v) {
        //
        if (v.getId() == R.id.buttonRegistro) {
            verRegistro();
            return;
        }

        View imagenMiPokemon = findViewById(R.id.miPokemon);
        View imagenPokemonRival = findViewById(R.id.pokemonRival);

        if (v.getId() == R.id.buttonReset) {
            resetBatalla(imagenMiPokemon, imagenPokemonRival);
            return;
        }

        if (progressBarMiVida.isPokemonDebilitado() || progressBarVidaEnemigo.isPokemonDebilitado())
            return;

        Button buttonReset = findViewById(R.id.buttonReset);
        Button buttonRegistro = findViewById(R.id.buttonRegistro);
        this.buttonAtaque1.setOnClickListener(null);
        this.buttonAtaque2.setOnClickListener(null);
        this.buttonAtaque3.setOnClickListener(null);
        this.buttonAtaque4.setOnClickListener(null);
        buttonReset.setOnClickListener(null);
        buttonRegistro.setOnClickListener(null);


        AtaquePokemon miAtaque;
        if (v.getId() == R.id.buttonAtaque1) miAtaque = this.buttonAtaque1.getAtaquePokemon();
        else if (v.getId() == R.id.buttonAtaque2) miAtaque = this.buttonAtaque2.getAtaquePokemon();
        else if (v.getId() == R.id.buttonAtaque3) miAtaque = this.buttonAtaque3.getAtaquePokemon();
        else if (v.getId() == R.id.buttonAtaque4) miAtaque = this.buttonAtaque4.getAtaquePokemon();
        else return;

        AtaquePokemon ataqueRival = this.progressBarVidaEnemigo.getPokemon().getRandomAttack();

        Animacion animacionMiPokemon = new Animacion(imagenMiPokemon, imagenPokemonRival);
        Animacion animacionRival = new Animacion(imagenMiPokemon, imagenPokemonRival);

        TextView textViewMiNombre = findViewById(R.id.nombreMiPokemon);
        TextView textViewNombreEnemigo = findViewById(R.id.nombreEnemigo);
        String nombreMiPokemon = (String) textViewMiNombre.getText();
        String nombrePokemonEnemigo = (String) textViewNombreEnemigo.getText();

        Switch switchSpeed = findViewById(R.id.switchSpeed);
        Switch switchPower = findViewById(R.id.switchPower);
        boolean isSpeeded = switchSpeed.isChecked();
        boolean isPowered = switchPower.isChecked();
        miAtaque.setPowered(isPowered);
        miAtaque.setSpeeded(isSpeeded);

        this.delay = 0;
        int aumentoDelay = 750;

        //Podemos fallar en un 20% de ocasiones el ataque rapido
        if (isSpeeded && Math.random() < 0.8d) {
            Turno turnoJugadorSpeed = new Turno(progressBarVidaEnemigo, miAtaque, animacionMiPokemon, nombreMiPokemon, true);
            listaMensajes.add(turnoJugadorSpeed.procesarTurno());
            this.delay += aumentoDelay;
            if (progressBarVidaEnemigo.isPokemonDebilitado()) {
                finBatalla(false, imagenMiPokemon, imagenPokemonRival);
                return;
            }
        }

        new Handler().postDelayed(() -> {
            Turno turnoJugador = new Turno(progressBarVidaEnemigo, miAtaque, animacionMiPokemon, nombreMiPokemon, true);
            listaMensajes.add(turnoJugador.procesarTurno());
            if (this.progressBarVidaEnemigo.isPokemonDebilitado()) {
                finBatalla(false, imagenMiPokemon, imagenPokemonRival);
                return;
            }
            if (isPowered) delay += 500;
            this.delay += aumentoDelay;
            new Handler().postDelayed(() -> {
                Turno turnoEnemigo = new Turno(progressBarMiVida, ataqueRival, animacionRival, nombrePokemonEnemigo, false);
                listaMensajes.add(turnoEnemigo.procesarTurno());
                if (this.progressBarMiVida.isPokemonDebilitado()) {
                    finBatalla(true, imagenMiPokemon, imagenPokemonRival);
                    return;
                }

                //Hay un 60% de que nos ataquen por segunda vez
                if (isPowered && Math.random() < 0.6d) {
                    new Handler().postDelayed(() -> {
                        Turno turnoEnemigo2 = new Turno(progressBarMiVida, ataqueRival, animacionRival, nombrePokemonEnemigo, false);
                        listaMensajes.add(turnoEnemigo2.procesarTurno());
                        if (this.progressBarMiVida.isPokemonDebilitado()) {
                            finBatalla(true, imagenMiPokemon, imagenPokemonRival);
                            return;
                        }
                    }, this.delay);
                }

                this.delay += 200;
                new Handler().postDelayed(() -> {
                    if (this.buttonAtaque1.getAtaquePokemon() != null)
                        this.buttonAtaque1.setOnClickListener(this);
                    if (this.buttonAtaque2.getAtaquePokemon() != null)
                        this.buttonAtaque2.setOnClickListener(this);
                    if (this.buttonAtaque3.getAtaquePokemon() != null)
                        this.buttonAtaque3.setOnClickListener(this);
                    if (this.buttonAtaque4.getAtaquePokemon() != null)
                        this.buttonAtaque4.setOnClickListener(this);
                    buttonReset.setOnClickListener(this);
                    buttonRegistro.setOnClickListener(this);
                }, this.delay);
            }, this.delay);
        }, this.delay);
    }

    private void verRegistro() {
        DialogoVerRegistro dialogoVerRegistro = new DialogoVerRegistro(this.listaRegistros);
        dialogoVerRegistro.show(getSupportFragmentManager(), "Poner Mote");
    }

    private void resetBatalla(View imagenMiPokemon, View imagenPokemonRival) {
        updateMiPokemon();
        updatePokemonEnemigo();

        TypeFaceStringMaker typeFaceStringMaker = new TypeFaceStringMaker(R.font.pkmndp_peter_o_and_mr_gela);
        this.listaMensajes.add(typeFaceStringMaker.build(getApplicationContext(), getString(R.string.madeReset)));

        if (listaMensajes.size() < 1) this.listaRegistros.add(this.listaMensajes);
        this.listaMensajes = new ArrayList<>();

        Animacion animacionAparecerPokemon = new Animacion(imagenMiPokemon, imagenPokemonRival);
        animacionAparecerPokemon.animacionAparicion(getApplicationContext());
    }

    private void finBatalla(boolean isMiPokemon, View imagenMiPokemon, View imagenPokemonRival) {

        this.delay = 2000;
        new Handler().postDelayed(() -> {
            Button buttonReset = findViewById(R.id.buttonReset);
            buttonReset.setOnClickListener(this);
            Button buttonRegistro = findViewById(R.id.buttonRegistro);
            buttonRegistro.setOnClickListener(this);
        }, this.delay);

        TypeFaceStringMaker typeFaceStringMaker = new TypeFaceStringMaker(R.font.pkmndp_peter_o_and_mr_gela);
        this.listaMensajes.add(typeFaceStringMaker.build(getApplicationContext(), getString(R.string.finBatalla)));
        this.listaRegistros.add(this.listaMensajes);
        this.listaMensajes = new ArrayList<>();

        new Animacion(imagenMiPokemon, imagenPokemonRival).animacionMuerte(isMiPokemon, getApplicationContext());
    }




}