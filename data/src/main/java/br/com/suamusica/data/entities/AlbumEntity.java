package br.com.suamusica.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumEntity {
    @JsonProperty("titulo") protected String titulo;
    @JsonProperty("id") protected int id;
    @JsonProperty("downloads") protected int downloads;
    @JsonProperty("username") protected String username;
    @JsonProperty("dono") protected String dono;
    @JsonProperty("cover") protected String cover;

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
