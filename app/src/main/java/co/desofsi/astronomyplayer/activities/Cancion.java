package co.desofsi.astronomyplayer.activities;

import java.io.Serializable;
import java.util.ArrayList;



public class Cancion implements Serializable {

    private int id;
    private String nombre;
    private String cantante;
    private String album;
    private int cancion;

    private ArrayList<Cancion> list_songs;

    public Cancion() {
    }

    public Cancion(int id, String nombre, String cantante, String album, int cancion) {
        this.id = id;
        this.nombre = nombre;
        this.cantante = cantante;
        this.album = album;
        this.cancion = cancion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getCancion() {
        return cancion;
    }

    public void setCancion(int cancion) {
        this.cancion = cancion;
    }


}
