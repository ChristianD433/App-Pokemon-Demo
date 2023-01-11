package com.example.app7_christian_arias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import UI_Elements.TypeFaceStringMaker;


public class DialogoPonerMote extends DialogFragment {
    RespuestaDialogoMote respuestaDialogoJava;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        TypeFaceStringMaker typeFaceStringMaker = new TypeFaceStringMaker(R.font.pkmndp_peter_o_and_mr_gela);
        builder.setTitle(typeFaceStringMaker.build(getContext(), getString(R.string.ponerMote)));

        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_poner_mote, null);
        EditText editTextMote = linearLayout.findViewById(R.id.editTextMote);

        builder.setView(linearLayout);

        builder.setPositiveButton(typeFaceStringMaker.build(getContext(),R.string.Si), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editTextMote.getText().equals("")) respuestaDialogoJava.onRespuestaMote(null);
                else respuestaDialogoJava.onRespuestaMote(String.valueOf(editTextMote.getText()));
            }
        });
        builder.setNegativeButton(typeFaceStringMaker.build(getContext(),R.string.No), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                respuestaDialogoJava.onRespuestaMote(null);
            }
        });
        return builder.create();
    }

    public void onAttach(Context activity) {
        super.onAttach(activity);
        respuestaDialogoJava = (RespuestaDialogoMote) activity;
    }

    public interface RespuestaDialogoMote {
        void onRespuestaMote(String respuesta);

    }

}
