package com.gmail.elbaglikov.bean;

import javax.persistence.*;

@Entity
public class CityEn extends City {
    private static final String LOCALE = "en";

    public CityEn() {
    }

    public CityEn(Integer id, String countryCode, String name) {
        super(id, countryCode, name);
    }

    public CityEn(String countryCode, String name) {
        super(countryCode, name);
        setLang(LOCALE);
    }

    @Override
    protected String getLocale() {
        return LOCALE;
    }
}
