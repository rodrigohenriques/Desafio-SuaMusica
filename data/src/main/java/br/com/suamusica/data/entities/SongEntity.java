package br.com.suamusica.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongEntity {
    @JsonProperty("id") protected int id;
    @JsonProperty("arquivo") protected String fileName;
    @JsonProperty("plays") protected int plays;
}