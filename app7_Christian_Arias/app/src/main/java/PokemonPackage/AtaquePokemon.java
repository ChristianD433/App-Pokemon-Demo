package PokemonPackage;

import com.example.app7_christian_arias.R;

import java.io.Serializable;

public class AtaquePokemon implements Serializable {

    private final int nombre;
    private final int danio;
    private final TipoPokemon tipoAtaque;
    private final double porcentajeAcierto; //entre 0 y 1
    private boolean isSpeeded = false;
    private boolean isPowered = false;

    private AtaquePokemon(int nombre, int potencia, TipoPokemon tipoAtaque, double porcentajeAcierto) {
        this.nombre = nombre;
        this.danio = potencia;
        this.tipoAtaque = tipoAtaque;
        this.porcentajeAcierto = porcentajeAcierto;
    }

    public static AtaquePokemon PLACAJE() {
        return new AtaquePokemon(R.string.placaje, 8, TipoPokemon.TIPO_NORMAL, 1);
    }

    public static AtaquePokemon ASCUAS() {
        return new AtaquePokemon(R.string.ascuas, 8, TipoPokemon.TIPO_FUEGO, 0.8d);
    }

    public static AtaquePokemon BURBUJA() {
        return new AtaquePokemon(R.string.burbuja, 8, TipoPokemon.TIPO_AGUA, 0.8d);
    }

    public static AtaquePokemon LATIGOCEPA() {
        return new AtaquePokemon(R.string.latigocepa, 8, TipoPokemon.TIPO_PLANTA, 0.8d);
    }

    public static AtaquePokemon ARANIAZO() {
        return new AtaquePokemon(R.string.araniazo, 12, TipoPokemon.TIPO_NORMAL, 0.6d);
    }

    public int getNombre() {
        return nombre;
    }

    public int atacar(TipoPokemon tipoPokemonEnemigo) {
        int damage;

        if (Math.random() > this.porcentajeAcierto) return 0;
        else if (tipoPokemonEnemigo.esDebil(this.tipoAtaque)) damage = (int) Math.round(danio * 1.5);
        else if (tipoPokemonEnemigo.esFuerte(this.tipoAtaque)) damage = (int) Math.round(danio * 0.5);
        else damage=this.danio;

        if(this.isSpeeded) damage*=0.75d;
        else if (this.isPowered) damage*=2;

        return damage;
    }

    public boolean isSpeeded() {
        return isSpeeded;
    }

    public void setSpeeded(boolean speeded) {
        isSpeeded = speeded;
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    public TipoPokemon getTipoAtaque() {
        return tipoAtaque;
    }
}
