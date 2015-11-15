package br.com.suamusica.domain.repository;

import java.util.List;

import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;

public interface AlbumsRepository {
    List<Album> listTrendingMusic(int page, QueryType queryType);
}