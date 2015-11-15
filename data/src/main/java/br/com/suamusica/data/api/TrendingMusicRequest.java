package br.com.suamusica.data.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.suamusica.domain.entities.QueryType;

public class TrendingMusicRequest {
    @JsonProperty("page") int page;
    @JsonProperty("tipo") String queryType;

    public TrendingMusicRequest(int page, QueryType queryType) {
        this.page = page;
        this.queryType = queryType.typeString();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType.typeString();
    }
}
