package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Constants;
import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityJPARepository cityRepository;

    public String getCities(String countryCode, String language){
        List<City> cities = cityRepository.getCitiesByCountryCodeAndLanguage(countryCode, language);
        String result = Constants.NULL_ARRAY;
        if (cities.size() > 0) {
            JSONArray ja = new JSONArray();
            ja.put(Constants.CITY_ID_KEY);
            ja.put(Constants.LANGUAGE_KEY);
            ja.put(Constants.COUNTRY_CODE_KEY);
            ja.put(Constants.CITY_NAME_KEY);

            String strCities = cities.stream()
                    .map(City::toString)
                    .collect(Collectors.joining(Constants.OBJECT_DELIMITER));

            JSONArray jsonCities = CDL.toJSONArray(ja, strCities);
            result = jsonCities.toString();
        }
        return result;
    }
}
