package br.com.suamusica.data.entities;

public class AlbumEntity {
    protected String titulo;
    protected int id;
    protected int downloads;
    protected String username;
    protected String dono;
    protected String cover;

    public String getCover() {
        return cover;
    }

    public String getDono() {
        return dono;
    }

    public String getUsername() {
        return username;
    }

    public int getDownloads() {
        return downloads;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
