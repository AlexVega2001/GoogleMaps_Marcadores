package com.example.googlemaps_marcadores;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.googlemaps_marcadores.Adaptador.InfoWindowAdapter;
import com.example.googlemaps_marcadores.Model.Facultades;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMaps;
    private List<Facultades> lstmarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstmarker = new ArrayList<>();

        //Consumir servicio web
        getInfoMarker();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.idMap);
        mapFragment.getMapAsync(this);

    }

    //Evento Onclick del boton UTEQ-CENTRAL
    public void cargarMarcadoresUTEQC(View view) {
        LatLng latLngUteq = new LatLng(-1.0125490551133802, -79.46949964389147);
        CameraUpdate camUpdUteq = CameraUpdateFactory.newLatLngZoom(latLngUteq, 17);
        gMaps.moveCamera(camUpdUteq);

        //Agregamos los marcadores alojados en la lista
        for (Facultades marketFacul : lstmarker) {
            if (!marketFacul.getUbicacion().contains("María")){
                LatLng ltlgFacultad = new LatLng(Float.parseFloat(marketFacul.getLatitud())
                        , Float.parseFloat(marketFacul.getLongitud()));

                gMaps.addMarker(new MarkerOptions()
                        .position(ltlgFacultad)
                        .title(marketFacul.getIdFacultad()));
            }
        }

        //Se aplica el diseño del diseno info window.xml
        gMaps.setInfoWindowAdapter(new InfoWindowAdapter(MainActivity.this, lstmarker));


    }

    //Evento Onclick del boton UTEQ-MARÍA
    public void cargarMarcadoresUTEQM(View view) {
        LatLng latLngUteq = new LatLng(-1.080541029564768, -79.50170031245554);
        CameraUpdate camUpdUteq = CameraUpdateFactory.newLatLngZoom(latLngUteq, 17);
        gMaps.moveCamera(camUpdUteq);

        //Agregamos los marcadores alojados en la lista
        for (Facultades marketFacul : lstmarker) {
            if (marketFacul.getUbicacion().contains("María")){
                LatLng ltlgFacultad = new LatLng(Float.parseFloat(marketFacul.getLatitud())
                        , Float.parseFloat(marketFacul.getLongitud()));

                gMaps.addMarker(new MarkerOptions()
                        .position(ltlgFacultad)
                        .title(marketFacul.getIdFacultad()));
            }
        }

        //Se aplica el diseño del diseno info window.xml
        gMaps.setInfoWindowAdapter(new InfoWindowAdapter(MainActivity.this, lstmarker));


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMaps = googleMap;

        LatLng latLngUteq = new LatLng(-1.239058521767784, -78.49668588517677);
        gMaps.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Activar los controles del Zoom
        gMaps.getUiSettings().setZoomControlsEnabled(true);

        //Zoom del mapa en la UTEQ
        CameraUpdate camUpdUteq = CameraUpdateFactory.newLatLngZoom(latLngUteq,6    );
        gMaps.moveCamera(camUpdUteq);
    }

    public void getInfoMarker() {
        String url = "https://my-json-server.typicode.com/AlexVega2001/jsonServerFake/UTEQ";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest requestJson = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> sendResponse(response),
                error -> Toast.makeText(getApplicationContext(),
                        "Error al conectarse:" + error.getMessage(),
                        Toast.LENGTH_LONG).show());
        queue.add(requestJson);
    }

    public void sendResponse(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                //Pasamos el JsonObject de nuestro JsonArray
                JSONObject objJson = new JSONObject(jsonArray.get(i).toString());

                //Asignar el dato en cada variables de la clase Facultades.
                Facultades fct = new Facultades();
                fct.setIdFacultad(objJson.getString("idFacultad"));
                fct.setNomFacultad(objJson.getString("nomFacultad"));
                fct.setNomDecano(objJson.getString("nomDecano"));
                fct.setUbicacion(objJson.getString("ubicacion"));
                fct.setLatitud(objJson.getString("latitud"));
                fct.setLongitud(objJson.getString("longitud"));
                fct.setImgLogo(objJson.getString("imgLogo"));
                lstmarker.add(fct);
            }
        } catch (JSONException jsonException) {
            Toast.makeText(getApplicationContext(),
                    "Error llenado a la lista: " + jsonException.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}