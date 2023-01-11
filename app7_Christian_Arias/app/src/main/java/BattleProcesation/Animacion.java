package BattleProcesation;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;

import com.example.app7_christian_arias.R;

public class Animacion {
    private View imagenMiPokemon;
    private View imagenPokemonRival;

    public Animacion(View imagenMiPokemon, View imagenPokemonRival) {
        this.imagenMiPokemon = imagenMiPokemon;
        this.imagenPokemonRival = imagenPokemonRival;
    }

    public void animacionAtaqueMiPokemon(int delay) {
        float desplazamientoXIda = imagenPokemonRival.getRight() - imagenMiPokemon.getRight();
        float desplazamientoYIda = imagenMiPokemon.getTop() - imagenPokemonRival.getTop();
        float desplazamientoEmpuje = 15;

        desplazamientoLateral(desplazamientoXIda, desplazamientoYIda, imagenMiPokemon);
        desplazamientoLateral(desplazamientoEmpuje, -desplazamientoEmpuje, imagenPokemonRival);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            float desplazamientoXVuelta = ((imagenMiPokemon.getRight() - imagenMiPokemon.getLeft()) * 0.75f) - desplazamientoXIda;
            desplazamientoLateral(desplazamientoXVuelta, -desplazamientoYIda, imagenMiPokemon);
            desplazamientoLateral(-desplazamientoEmpuje, desplazamientoEmpuje, imagenPokemonRival);
        }, delay);
    }

    public void animacionDefensaMiPokemon(int delay) {
        float desplazamientoXIda = imagenMiPokemon.getRight() - imagenPokemonRival.getRight();
        float desplazamientoYIda = imagenMiPokemon.getTop() - imagenPokemonRival.getTop() + 40;
        float desplazamientoEmpuje = -15;

        desplazamientoLateral(desplazamientoXIda, desplazamientoYIda, imagenPokemonRival);
        desplazamientoLateral(desplazamientoEmpuje, -desplazamientoEmpuje, imagenMiPokemon);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            float desplazamientoXVuelta = (imagenMiPokemon.getRight() - imagenPokemonRival.getLeft()) * 0.3f;
            desplazamientoLateral(-desplazamientoXVuelta, -desplazamientoYIda, imagenPokemonRival);
            desplazamientoLateral(-desplazamientoEmpuje, desplazamientoEmpuje, imagenMiPokemon);
        }, delay);
    }

    public void animacionMuerte(boolean isMiPokemon,Context context) {
        View imagen;
        if (isMiPokemon) imagen = this.imagenMiPokemon;
        else imagen = this.imagenPokemonRival;

        Animation animacion = AnimationUtils.loadAnimation(context, R.anim.desaparecer);
        animacion.setFillAfter(true);
        imagen.startAnimation(animacion);
        new Handler().postDelayed(() -> imagen.setAlpha(0), animacion.getDuration());
    }


    public void animacionAparicion(Context context){
        Animation animacion = AnimationUtils.loadAnimation(context, R.anim.aparecer);
        animacion.setFillAfter(true);
        this.imagenPokemonRival.startAnimation(animacion);
        this.imagenMiPokemon.startAnimation(animacion);
        this.imagenPokemonRival.setAlpha(1);
        this.imagenMiPokemon.setAlpha(1);
    }

    private void desplazamientoLateral(float desplazamientoX, float desplazamientoY, View view) {
        // Setting up a spring animation to animate the view1 and view2 translationX and translationY properties
        final SpringAnimation anim1X = new SpringAnimation(view,
                DynamicAnimation.TRANSLATION_X, desplazamientoX);
        final SpringAnimation anim1Y = new SpringAnimation(view,
                DynamicAnimation.TRANSLATION_Y, desplazamientoY);
        anim1X.start();
        anim1Y.start();
    }
}
