package PokemonPackage;

import com.example.app7_christian_arias.R;

import java.io.Serializable;

public class TipoPokemon implements Serializable {

    public static final TipoPokemon TIPO_FUEGO =
            new TipoPokemon(
                    R.string.fuego,
                    NombreTipoPokemon.FUEGO,
                    new NombreTipoPokemon[]{NombreTipoPokemon.AGUA},
                    new NombreTipoPokemon[]{NombreTipoPokemon.PLANTA},
                    R.color.tipoFuego);

    public static final TipoPokemon TIPO_AGUA =
            new TipoPokemon(
                    R.string.agua,
                    NombreTipoPokemon.AGUA,
                    new NombreTipoPokemon[]{NombreTipoPokemon.PLANTA},
                    new NombreTipoPokemon[]{NombreTipoPokemon.FUEGO},
                    R.color.tipoAgua);

    public static final TipoPokemon TIPO_PLANTA =
            new TipoPokemon(
                    R.string.planta,
                    NombreTipoPokemon.PLANTA,
                    new NombreTipoPokemon[]{NombreTipoPokemon.FUEGO},
                    new NombreTipoPokemon[]{NombreTipoPokemon.AGUA},
                    R.color.tipoPlanta);

    public static final TipoPokemon TIPO_NORMAL =
            new TipoPokemon(
                    R.string.normal,
                    NombreTipoPokemon.NORMAL,
                    new NombreTipoPokemon[]{},
                    new NombreTipoPokemon[]{},
                    R.color.tipoNormal);

    private final int nombre;
    private final NombreTipoPokemon tipoPokemon;
    private final NombreTipoPokemon[] debilidades;
    private final NombreTipoPokemon[] fortalezas;
    private final int color;

    private TipoPokemon(int nombre, NombreTipoPokemon tipoPokemon, NombreTipoPokemon[] debilidades, NombreTipoPokemon[] fortalezas, int color) {
        this.nombre = nombre;
        this.tipoPokemon = tipoPokemon;
        this.debilidades = debilidades;
        this.fortalezas = fortalezas;
        this.color = color;
    }

    public boolean esDebil(TipoPokemon otroTipo) {
        return contains(this.debilidades, otroTipo.getTipoPokemon());
    }

    public boolean esFuerte(TipoPokemon otroTipo) {
        return contains(this.fortalezas, otroTipo.getTipoPokemon());
    }

    private boolean contains(NombreTipoPokemon[] arrayNombres, NombreTipoPokemon nombreTipo) {
        for (NombreTipoPokemon miTipoPokemon : arrayNombres)
            if (nombreTipo == miTipoPokemon)
                return true;
        return false;
    }

    public int getNombre() {
        return nombre;
    }

    public NombreTipoPokemon getTipoPokemon() {
        return tipoPokemon;
    }

    public int getColor() {
        return color;
    }

    private enum NombreTipoPokemon implements Serializable {
        AGUA, FUEGO, PLANTA, NORMAL
    }
}
