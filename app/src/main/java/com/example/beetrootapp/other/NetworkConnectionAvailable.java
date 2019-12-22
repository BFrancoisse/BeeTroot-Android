/*package com.example.beetrootapp.other;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnectionAvailable {

    public void checkNetworkConnection(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pas de connection internet");
        builder.setMessage("Veuillez vous connecter à internet");
        builder.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionIsAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected


    }
}*/