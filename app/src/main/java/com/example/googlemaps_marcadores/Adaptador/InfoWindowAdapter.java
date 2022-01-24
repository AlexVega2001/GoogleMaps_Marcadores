package com.example.googlemaps_marcadores.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.googlemaps_marcadores.CallBackMarker;
import com.example.googlemaps_marcadores.Model.Facultades;
import com.example.googlemaps_marcadores.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View vista;
    private final Context ctx;
    private final List<Facultades> lstFacultades;

    public InfoWindowAdapter(Context context, List<Facultades> listaFac) {
        this.ctx = context;
        this.lstFacultades = listaFac;
        vista = LayoutInflater.from(context).inflate(R.layout.info_window_design, null);
    }

    public void getInformationMarket(View view, Marker marker) {
        TextView facultad, ubicacion, decano;
        ImageView logo;

        facultad = view.findViewById(R.id.txtFacultad);
        ubicacion = view.findViewById(R.id.txtUbicacion);
        decano = view.findViewById(R.id.txtDecano);
        logo = view.findViewById(R.id.imgLogo);

        //Cargar Informacion de las facultades
        try {
            for (Facultades markerFacultad : lstFacultades) {
                if (markerFacultad.getIdFacultad().equals(marker.getTitle())) {

                    facultad.setText(markerFacultad.getNomFacultad());
                    ubicacion.setText(markerFacultad.getUbicacion());
                    decano.setText(markerFacultad.getNomDecano());

                    Picasso.get().load(markerFacultad.getImgLogo())
                            .into(logo, new CallBackMarker(marker));
                }
            }
        } catch (Exception error) {
            Toast.makeText(ctx, "Error al cargar los datos: " + error.getMessage()
                    , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        getInformationMarket(vista, marker);
        return vista;
    }

    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return vista;
    }
}
