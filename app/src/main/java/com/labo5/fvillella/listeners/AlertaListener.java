package com.labo5.fvillella.listeners;

import android.content.DialogInterface;

/**
 * Created by Vicente on 17/06/2016.
 */
public class AlertaListener implements DialogInterface.OnClickListener {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
    }
}
