package br.com.suamusica.domain.entities;

public enum QueryType {
    EVER("geral"), THIS_MONTH("mes"), THIS_WEEK("semana"), THIS_YEAR("ano");

    final String typeString;

    QueryType(String typeString) {
        this.typeString = typeString;
    }

    public String typeString() {
        return typeString;
    }
}