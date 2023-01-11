package PokemonPackage;

import com.example.app7_christian_arias.R;

import java.io.Serializable;
import java.util.Random;

//Implementamos serializable para poder pasarlo por Intent.putExtra();
public class Pokemon implements Serializable {

    private final int id;
    private final int nombre;
    private String mote;

    private final boolean isShiny;
    private final int imagenNormal;
    private final int imagenShiny;
    private final int imagenNormalEspalda;
    private final int imagenShinyEspalda;

    private final TipoPokemon tipoPokemon;
    private final AtaquePokemon[] ataquesPokemon;
    private int vidaRestantePokemon;
    private final int vidaTotalPokemon;

    private Pokemon(int id, int nombre, String mote, int imagenNormal, int imagenShiny, int imagenNormalEspalda, int imagenShinyEspalda, TipoPokemon tipoPokemon, AtaquePokemon[] ataquesPokemon, double porcentajeShiny, int vidaRestantePokemon, int vidaTotalPokemon) {
        this.id = id;
        this.nombre = nombre;
        this.mote = mote;
        this.imagenNormal = imagenNormal;
        this.imagenShiny = imagenShiny;
        this.imagenNormalEspalda = imagenNormalEspalda;
        this.imagenShinyEspalda = imagenShinyEspalda;
        this.tipoPokemon = tipoPokemon;
        this.ataquesPokemon = ataquesPokemon;
        this.vidaRestantePokemon = vidaRestantePokemon;
        this.vidaTotalPokemon = vidaTotalPokemon;
        this.isShiny = (Math.random() < porcentajeShiny);
    }

    public static Pokemon BULBASUR() {

        AtaquePokemon[] ataquesPokemon = new AtaquePokemon[4];
        ataquesPokemon[0] = AtaquePokemon.PLACAJE();
        ataquesPokemon[1] = AtaquePokemon.LATIGOCEPA();
        if (Math.random() > 0.3d) ataquesPokemon[2] = AtaquePokemon.ARANIAZO();
        else ataquesPokemon[2] = null;
        ataquesPokemon[3] = null;

        return new Pokemon(1,
                R.string.bulbasur,
                null,
                R.drawable.bulbasaur,
                R.drawable.bulbasur_shiny,
                R.drawable.bulbasur_back,
                R.drawable.bulbasur_shiny_back,
                TipoPokemon.TIPO_PLANTA,
                ataquesPokemon,
                0.25d,
                24,
                24
        );
    }

    public static Pokemon CHARMANDER() {

        AtaquePokemon[] ataquesPokemon = new AtaquePokemon[4];
        ataquesPokemon[0] = AtaquePokemon.PLACAJE();
        ataquesPokemon[1] = AtaquePokemon.ASCUAS();
        if (Math.random() > 0.7d) ataquesPokemon[2] = AtaquePokemon.ARANIAZO();
        else ataquesPokemon[2] = null;
        ataquesPokemon[3] = null;

        return new Pokemon(2, R.string.charmander,
                null, R.drawable.charmander,
                R.drawable.charmander_shiny,
                R.drawable.charmander_back,
                R.drawable.charmander_shiny_back,
                TipoPokemon.TIPO_FUEGO,
                ataquesPokemon,
                0.25d,
                19,
                19
        );
    }

    public static Pokemon SQUIRTLE() {

        AtaquePokemon[] ataquesPokemon = new AtaquePokemon[4];
        ataquesPokemon[0] = AtaquePokemon.PLACAJE();
        ataquesPokemon[1] = AtaquePokemon.BURBUJA();
        if (Math.random() > 0.5d) ataquesPokemon[2] = AtaquePokemon.ARANIAZO();
        else ataquesPokemon[2] = null;
        ataquesPokemon[3] = null;

        return new Pokemon(3,
                R.string.squirtle,
                null,
                R.drawable.squirtle,
                R.drawable.squirtle_shiny,
                R.drawable.squirtle_back,
                R.drawable.squirtle_shiny_back,
                TipoPokemon.TIPO_AGUA,
                ataquesPokemon,
                25d,
                22,
                22);
    }

    public static Pokemon buildPokemonbyId(int id) {
        if (id == 1) return Pokemon.BULBASUR();
        else if (id == 2) return Pokemon.CHARMANDER();
        else if (id == 3) return Pokemon.SQUIRTLE();
        else return Pokemon.CHARMANDER();
    }

    public int getNombre() {
        return nombre;
    }

    public int getImagen() {
        if (this.isShiny) return this.imagenShiny;
        else return this.imagenNormal;
    }

    public int getId() {
        return id;
    }

    public int getImagenEspalda() {
        if (this.isShiny) return this.imagenShinyEspalda;
        else return this.imagenNormalEspalda;
    }

    public TipoPokemon getTipoPokemon() {
        return tipoPokemon;
    }

    public int getPorcentajeVida() {
        return (this.vidaRestantePokemon * 100) / this.vidaTotalPokemon;
    }


    public boolean estaVivo() {
        return this.vidaRestantePokemon > 0;
    }

    public void setMote(String mote){
        this.mote=mote;
    }

    public boolean isShiny() {
        return isShiny;
    }

    public String getMote() {
        return mote;
    }

    public AtaquePokemon[] getAtaquesPokemon() {
        return ataquesPokemon;
    }

    public int getVidaRestante() {
        return vidaRestantePokemon;
    }

    public void recibeDanio(int vidaPerdida) {
        this.vidaRestantePokemon = this.vidaRestantePokemon - vidaPerdida;
        if (this.vidaRestantePokemon < 0) this.vidaRestantePokemon = 0;
    }

    public AtaquePokemon getRandomAttack() {
        int numeroAtaque;
        AtaquePokemon ataqueAleatorio;
        do {
            numeroAtaque = new Random().nextInt(4);
            ataqueAleatorio = this.getAtaquesPokemon()[numeroAtaque];
        } while (ataqueAleatorio == null);

        return ataqueAleatorio;
    }


    public void curarVida() {
        this.vidaRestantePokemon = this.vidaTotalPokemon;
    }



}
