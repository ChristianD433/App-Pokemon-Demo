package UI_Elements;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app7_christian_arias.R;

import PokemonPackage.AtaquePokemon;
import PokemonPackage.TipoPokemon;

public class RelativeAtaquePokemonLayoutButton extends RelativeLayoutButton {
    private AtaquePokemon ataquePokemon;

    public RelativeAtaquePokemonLayoutButton(Context context, int id, AtaquePokemon ataquePokemon) {
        super(context, id);
        this.ataquePokemon = ataquePokemon;
        RelativeLayout relativeLayout = findViewById(id);
        if (ataquePokemon != null) {
            TipoPokemon tipoAtaquePokemon = ataquePokemon.getTipoAtaque();
            TextView textViewTipoAtaque = relativeLayout.findViewById(R.id.nombreTipo);

            relativeLayout.setBackgroundColor(getResources().getColor(tipoAtaquePokemon.getColor()));
            setText(R.id.nombreAtaque,getResources().getString(ataquePokemon.getNombre()));
            setText(textViewTipoAtaque.getId(),getResources().getString(tipoAtaquePokemon.getNombre()));
            textViewTipoAtaque.setBackgroundColor(getResources().getColor(tipoAtaquePokemon.getColor()));

        }else relativeLayout.setVisibility(INVISIBLE);
    }

    public AtaquePokemon getAtaquePokemon() {
        return ataquePokemon;
    }
}
