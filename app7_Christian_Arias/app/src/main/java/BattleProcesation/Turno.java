package BattleProcesation;

import android.text.SpannableString;

import PokemonPackage.AtaquePokemon;

public class Turno {
    private final Pokemon_HPBar progressBarHP;
    private final AtaquePokemon ataquePokemon;
    private final Animacion animacion;
    private final String nombrePokemonAtacante;
    private final boolean isAttacking;

    public Turno(Pokemon_HPBar progressBarHP, AtaquePokemon ataquePokemon, Animacion animacion, String nombrePokemonAtacante, boolean isAttacking) {
        this.progressBarHP = progressBarHP;
        this.ataquePokemon = ataquePokemon;
        this.animacion = animacion;
        this.nombrePokemonAtacante = nombrePokemonAtacante;
        this.isAttacking = isAttacking;
    }

    public SpannableString procesarTurno() {
        int delayAttack = 500;
        if (this.isAttacking) {
            if (this.ataquePokemon.isPowered()) delayAttack = 1000;
            else if (this.ataquePokemon.isSpeeded()) delayAttack = 250;
            animacion.animacionAtaqueMiPokemon(delayAttack);
        } else animacion.animacionDefensaMiPokemon(delayAttack);
        return progressBarHP.recibeAtaque(this.nombrePokemonAtacante, this.ataquePokemon);
    }

}
