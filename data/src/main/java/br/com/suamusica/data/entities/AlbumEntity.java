package br.com.suamusica.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumEntity {
    @JsonProperty("titulo") protected String titulo;
    @JsonProperty("id") protected int id;
    @JsonProperty("downloads") protected int downloads;
    @JsonProperty("username") protected String username;
    @JsonProperty("dono") protected String dono;
    @JsonProperty("cover") protected String cover;
}
