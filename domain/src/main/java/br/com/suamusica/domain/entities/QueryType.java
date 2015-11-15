package br.com.suamusica.domain.entities;

public enum QueryType {
        ALWAYS("geral"), THIS_MONTH("mes"), THIS_WEEK("emana"), THIS_YEAR("ano");

        final String typeString;

        QueryType(String typeString) {
            this.typeString = typeString;
        }
    }