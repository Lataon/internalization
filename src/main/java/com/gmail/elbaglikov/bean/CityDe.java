package com.gmail.elbaglikov.bean;

import javax.persistence.Entity;

@Entity
public class CityDe extends City {
    private static final String LOCALE = "de";

    public CityDe() {
    }

    public CityDe(Integer id, String countryCode, String name) {
        super(id, countryCode, name);
    }

    public CityDe(String countryCode, String name) {
        super(countryCode, name);
        setLang(LOCALE);
    }

    @Override
    protected String getLocale() {
        return LOCALE;
    }
}
