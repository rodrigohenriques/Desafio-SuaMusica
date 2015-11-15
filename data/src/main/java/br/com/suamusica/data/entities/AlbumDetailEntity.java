package br.com.suamusica.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailEntity {
    @JsonProperty("downloads") protected int downloads;
    @JsonProperty("arquivo") protected String fileName;
    @JsonProperty("titulo") protected String name;
    @JsonProperty("dono") protected String dono;
    @JsonProperty("data_envio") protected String createdAt;
    @JsonProperty("tamanho") protected int fileSize;
    @JsonProperty("username") protected String username;
    @JsonProperty("image_ext") protected String imageExtension;
    @JsonProperty("likes") protected String likes;
    @JsonProperty("plays") protected int plays;
    @JsonProperty("cover") protected String coverUrl;
    @JsonProperty("files") protected List<SongEntity> songs = new ArrayList<>();
}
