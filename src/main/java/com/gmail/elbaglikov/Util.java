package com.gmail.elbaglikov;

import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.bean.Country;
import com.gmail.elbaglikov.bean.Language;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
    private Util(){}

    public static final String NULL_ARRAY = "[]";
    public static final String OBJECT_DELIMITER = " \n";

    //JSON keys
    //Lang
    public static final String LANGUAGE_ID_KEY = "id";
    public static final String LANGUAGE_NAME = "name";
    public static final String LANGUAGE_KEY = "locale";
    //Country
    public static final String COUNTRY_ID_KEY = "id";
    public static final String COUNTRY_CODE_KEY = "code";
    public static final String COUNTRY_NAME_KEY = "name";

    //City
    public static final String CITY_ID_KEY = "id";
    public static final String CITY_NAME_KEY = "name";

    private static final JSONArray JA_CITIES;
    private static final JSONArray JA_COUNTRIES;
    private static final JSONArray JA_LANGS;

    static {
        JA_CITIES = new JSONArray();
        JA_CITIES.put(CITY_ID_KEY);
        JA_CITIES.put(LANGUAGE_KEY);
        JA_CITIES.put(COUNTRY_CODE_KEY);
        JA_CITIES.put(CITY_NAME_KEY);

        JA_COUNTRIES = new JSONArray();
        JA_COUNTRIES.put(COUNTRY_ID_KEY);
        JA_COUNTRIES.put(LANGUAGE_KEY);
        JA_COUNTRIES.put(COUNTRY_CODE_KEY);
        JA_COUNTRIES.put(COUNTRY_NAME_KEY);

        JA_LANGS = new JSONArray();
        JA_LANGS.put(LANGUAGE_ID_KEY);
        JA_LANGS.put(LANGUAGE_NAME);
        JA_LANGS.put(LANGUAGE_KEY);
    }

    public static String getArrayJsonCountry(List<Country> countries) {
        return getArrayJson(countries, JA_COUNTRIES);
    }

    public static String getArrayJsonCity(List<City> cities) {
        return getArrayJson(cities, JA_CITIES);
    }

    public static String getArrayJsonLang(List<Language> languages) {
        return getArrayJson(languages, JA_LANGS);
    }

    private static <T> String getArrayJson(List<T> list, JSONArray ja) {
        try {
            String result = NULL_ARRAY;
            if (list.size() > 0) {
                String strList = list.stream()
                        .map(T::toString)
                        .collect(Collectors.joining(OBJECT_DELIMITER));
                JSONArray jsonCities = null;
                jsonCities = CDL.toJSONArray(ja, strList);
                result = jsonCities.toString();
            }
            return result;
        } catch (JSONException e) {
            throw new IllegalArgumentException("some JSON problem", e);
        }
    }
}
