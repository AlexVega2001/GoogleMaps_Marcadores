package com.example.googlemaps_marcadores.Model;

public class Facultades {
    String idFacultad, nomFacultad, nomDecano, ubicacion, latitud, longitud, imgLogo;

    public Facultades() {

    }

    public Facultades(String idFacultad, String nomFacultad, String nomDecano, String ubicacion,
                      String latitud, String longitud, String imgLogo) {

        this.idFacultad = idFacultad;
        this.nomFacultad = nomFacultad;
        this.nomDecano = nomDecano;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imgLogo = imgLogo;
    }

    public String getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(String idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNomFacultad() {
        return nomFacultad;
    }

    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }

    public String getNomDecano() {
        return nomDecano;
    }

    public void setNomDecano(String nomDecano) {
        this.nomDecano = nomDecano;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }
}
