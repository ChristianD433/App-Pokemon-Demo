package com.example.app7_christian_arias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import UI_Elements.TypeFaceStringMaker;


public class DialogoVerRegistro extends DialogFragment {
    ArrayList<ArrayList<SpannableString>> listaRegistros;

    public DialogoVerRegistro(ArrayList<ArrayList<SpannableString>> listaRegistros) {
        super();
        this.listaRegistros = listaRegistros;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ScrollView scrollView = new ScrollView(getContext());
        LinearLayout linearLayoutGeneral = new LinearLayout(getContext());
        linearLayoutGeneral.setOrientation(LinearLayout.VERTICAL);
        TypeFaceStringMaker typeFaceStringMaker = new TypeFaceStringMaker(R.font.pkmndp_peter_o_and_mr_gela);

        linearLayoutGeneral.addView(buildLayoutSeparacion(typeFaceStringMaker));
        int numeroCombate = 1;
        for (ArrayList<SpannableString> registro : listaRegistros) {
            LinearLayout linearLayoutRegistro = new LinearLayout(getContext());
            linearLayoutRegistro.setOrientation(LinearLayout.VERTICAL);
            linearLayoutRegistro.setBackgroundColor(getResources().getColor(R.color.lightBackground));
            linearLayoutRegistro.addView(buildTypefaceTextView(typeFaceStringMaker.build(getContext(), getText(R.string.registro) + " " + numeroCombate),
                    getResources().getColor(R.color.textoTitulo),
                    24));
            for (SpannableString mensaje : registro) {
                linearLayoutRegistro.addView(buildTypefaceTextView(mensaje,
                        getResources().getColor(R.color.textoRegistro),
                        18));
            }
            linearLayoutGeneral.addView(linearLayoutRegistro);
            linearLayoutGeneral.addView(buildLayoutSeparacion(typeFaceStringMaker));
            numeroCombate++;
        }

        scrollView.addView(linearLayoutGeneral);

        builder.setView(scrollView);
        builder.setPositiveButton(typeFaceStringMaker.build(getContext(), R.string.salir), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    private LinearLayout buildLayoutSeparacion(TypeFaceStringMaker typeFaceStringMaker) {
        LinearLayout linearLayoutSeparacion = new LinearLayout(getContext());
        linearLayoutSeparacion.setOrientation(LinearLayout.VERTICAL);
        linearLayoutSeparacion.setBackgroundColor(getResources().getColor(R.color.darkBackground));
        linearLayoutSeparacion.addView(buildTypefaceTextView(typeFaceStringMaker.build(getContext(), " "),
                getResources().getColor(R.color.textoRegistro),
                24));
        return linearLayoutSeparacion;
    }

    private View buildTypefaceTextView(SpannableString mensaje, int color, int textSize) {
        TextView textView = new TextView(getContext());
        textView.setText(mensaje);
        textView.setTextSize(textSize);
        textView.setTextColor(color);
        return textView;
    }

}

