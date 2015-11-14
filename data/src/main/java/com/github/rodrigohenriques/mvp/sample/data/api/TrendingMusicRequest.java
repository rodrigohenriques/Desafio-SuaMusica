package com.github.rodrigohenriques.mvp.sample.data.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrendingMusicRequest {
    @JsonProperty("page") int page;
    @JsonProperty("tipo") String queryType;

    public enum QueryType {
        ALWAYS("geral"), THIS_MONTH("mes"), THIS_WEEK("emana"), THIS_YEAR("ano");

        final String typeString;

        QueryType(String typeString) {
            this.typeString = typeString;
        }
    }

    public TrendingMusicRequest(int page, QueryType queryType) {
        this.page = page;
        this.queryType = queryType.typeString;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType.typeString;
    }
}
