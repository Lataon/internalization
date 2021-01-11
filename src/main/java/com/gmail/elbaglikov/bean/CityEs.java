package com.gmail.elbaglikov.bean;

import javax.persistence.Entity;

@Entity
public class CityEs extends City {
    private static final String LOCALE = "es";

    public CityEs() {
    }

    public CityEs(Integer id, String countryCode, String name) {
        super(id, countryCode, name);
    }

    public CityEs(String countryCode, String name) {
        super(countryCode, name);
    }

    @Override
    protected String getLocale() {
        return LOCALE;
    }
}
