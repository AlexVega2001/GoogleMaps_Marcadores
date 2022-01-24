package com.example.googlemaps_marcadores;

import android.util.Log;

import com.google.android.gms.maps.model.Marker;

public class CallBackMarker implements com.squareup.picasso.Callback{

    Marker mkr = null;

    public CallBackMarker(Marker marker) {
        this.mkr = marker;
    }

    @Override
    public void onSuccess() {
        if (mkr!= null && mkr.isInfoWindowShown()){
            mkr.hideInfoWindow();
            mkr.showInfoWindow();
        }
    }

    @Override
    public void onError(Exception e) {
        Log.e(getClass().getSimpleName(),"Error al cargar: " + e.getMessage());
    }
}
