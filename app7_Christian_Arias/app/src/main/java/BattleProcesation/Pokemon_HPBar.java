package BattleProcesation;

import android.os.Build;
import android.text.SpannableString;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.app7_christian_arias.R;

import PokemonPackage.AtaquePokemon;
import PokemonPackage.Pokemon;
import UI_Elements.TypeFaceStringMaker;

public class Pokemon_HPBar {
    private final Pokemon pokemon;
    ProgressBar barraVida;


    public Pokemon_HPBar(ProgressBar progressBar, Pokemon pokemon) {
        this.barraVida = progressBar;
        this.pokemon = pokemon;
        DrawableCompat.setTint(this.barraVida.getProgressDrawable(), ContextCompat.getColor(this.barraVida.getContext(), R.color.green));
    }

    public SpannableString recibeAtaque(String nombrePokemonaAtacante, AtaquePokemon ataquePokemon) {
        int damage = ataquePokemon.atacar(this.pokemon.getTipoPokemon());
        this.pokemon.recibeDanio(damage);
        actualizarHPBar();
        return buildMensaje(damage, nombrePokemonaAtacante, ataquePokemon);
    }

    public void actualizarHPBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.barraVida.setProgress(this.pokemon.getPorcentajeVida(), true);

        if (this.barraVida.getProgress() < 25)
            DrawableCompat.setTint(this.barraVida.getProgressDrawable(), ContextCompat.getColor(this.barraVida.getContext(), R.color.red));
        else if (this.barraVida.getProgress() < 50)
            DrawableCompat.setTint(this.barraVida.getProgressDrawable(), ContextCompat.getColor(this.barraVida.getContext(), R.color.yellow));
        else
            DrawableCompat.setTint(this.barraVida.getProgressDrawable(), ContextCompat.getColor(this.barraVida.getContext(), R.color.green));
    }

    private SpannableString buildMensaje(int damage, String nombrePokemonAtacante, AtaquePokemon ataquePokemon) {
        TypeFaceStringMaker typeFaceStringMaker = new TypeFaceStringMaker(R.font.pkmndp_peter_o_and_mr_gela);
        String nombrePokemon = getNombrePokemon();
        String nombreAtaque = getString(ataquePokemon.getNombre());
        String mensaje = getString(R.string.ataqueNormal);
        if (ataquePokemon.isPowered()) mensaje = getString(R.string.ataquePotente);
        else if (ataquePokemon.isSpeeded()) mensaje = getString(R.string.ataqueRapido);
        mensaje = mensaje + " ";
        if (damage == 0) {
            mensaje = nombrePokemonAtacante + " " + getString(R.string.mensajeFallo);
            Toast.makeText(this.barraVida.getContext(), typeFaceStringMaker.build(this.barraVida.getContext(), mensaje), Toast.LENGTH_SHORT).show();
        } else if (ataquePokemon.getTipoAtaque().esDebil(this.pokemon.getTipoPokemon()))
            mensaje = mensaje + (nombrePokemonAtacante + " ha usado " + nombreAtaque
                    + " contra " + nombrePokemon + " ,no es muy efectivo, ha causado "
                    + damage + " puntos de daño.A " + nombrePokemon + " le quedan "
                    + this.pokemon.getVidaRestante() + " puntos de vida");
        else if (ataquePokemon.getTipoAtaque().esFuerte(this.pokemon.getTipoPokemon()))
            mensaje = mensaje + (nombrePokemonAtacante + " ha usado " + nombreAtaque
                    + " contra " + nombrePokemon + " ,es muy efectivo, ha causado "
                    + damage + " puntos de daño.A " + nombrePokemon + " le quedan "
                    + this.pokemon.getVidaRestante() + " puntos de vida");
        else
            mensaje = mensaje + (nombrePokemonAtacante + " ha usado " + nombreAtaque
                    + " contra " + nombrePokemon + " ha causado "
                    + damage + " puntos de daño.A " + nombrePokemon + " le quedan "
                    + this.pokemon.getVidaRestante() + " puntos de vida");
        return typeFaceStringMaker.build(this.barraVida.getContext(), mensaje);
    }


    public boolean isPokemonDebilitado() {return !(this.pokemon.estaVivo());}

    private String getString(int id) {
        return barraVida.getContext().getString(id);
    }

    public Pokemon getPokemon() {
        return pokemon;
    }


    private String getNombrePokemon() {
        String mote = this.pokemon.getMote();
        if (mote!= null) return mote;
        else return getString(this.pokemon.getNombre());
    }
}