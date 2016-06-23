package com.labo5.fvillella.activities;


import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.labo5.fvillella.listeners.AlertaListener;

/**
 * Created by Vicente on 17/06/2016.
 */
public class AlertaDialogo extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.error);
        builder.setMessage(R.string.msgError);
        AlertaListener listener = new AlertaListener();
        builder.setPositiveButton(R.string.aceptar, listener);

        AlertDialog ad = builder.create();
        return ad;
    }
}
