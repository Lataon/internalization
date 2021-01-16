package com.gmail.elbaglikov;

import com.gmail.elbaglikov.bean.*;
import com.gmail.elbaglikov.enums.LangEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static final Pageable PAGEABLE = PageRequest.of(2, 3);
    public static final List<CityEn> CITIES_EN;
    public static final List<CityDe> CITIES_DE;
    public static final List<CityEs> CITIES_ES;
    public static final List<Country> COUNTRIES;
    public static final List<Language> LANGS;

    static {
        CITIES_EN = new ArrayList<>();
        CITIES_EN.add(new CityEn(1, "en", "London"));
        CITIES_EN.add(new CityEn(2, "en", "Moscow"));
        CITIES_EN.add(new CityEn(3, "en", "Hamburg"));

        CITIES_DE = new ArrayList<>();
        CITIES_DE.add(new CityDe(4, "en", "London"));
        CITIES_DE.add(new CityDe(5, "en", "Moscow"));
        CITIES_DE.add(new CityDe(6, "en", "Hamburg"));

        CITIES_ES = new ArrayList<>();
        CITIES_ES.add(new CityEs(7, "en", "London"));
        CITIES_ES.add(new CityEs(8, "en", "Moscow"));
        CITIES_ES.add(new CityEs(9, "en", "Hamburg"));

        COUNTRIES = Arrays.asList(
                new Country(1, LangEnum.EN.name().toLowerCase(), "us", "United States of America"),
                new Country(2, LangEnum.EN.name().toLowerCase(), "ru", "Russian Federation"),
                new Country(3, LangEnum.EN.name().toLowerCase(), "by", "Republic of Belarus"),
                new Country(4, LangEnum.DE.name().toLowerCase(), "us", "United States of America"),
                new Country(5, LangEnum.DE.name().toLowerCase(), "ru", "Russian Federation"),
                new Country(6, LangEnum.DE.name().toLowerCase(), "by", "Republic of Belarus"),
                new Country(7, LangEnum.ES.name().toLowerCase(), "us", "United States of America"),
                new Country(8, LangEnum.ES.name().toLowerCase(), "ru", "Russian Federation"),
                new Country(9, LangEnum.ES.name().toLowerCase(), "by", "Republic of Belarus")
        );

        LANGS = Arrays.asList(
                new Language(1, LangEnum.EN.getFullName(), LangEnum.EN.name().toLowerCase()),
                new Language(2, LangEnum.DE.getFullName(), LangEnum.DE.name().toLowerCase()),
                new Language(3, LangEnum.ES.getFullName(), LangEnum.ES.name().toLowerCase())
        );
    }

}
