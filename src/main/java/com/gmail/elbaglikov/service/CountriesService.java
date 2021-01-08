package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Constants;
import com.gmail.elbaglikov.bean.Country;
import com.gmail.elbaglikov.dao.CountryJPARepository;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesService {

    @Autowired
    private CountryJPARepository countryRepository;

    public String getCountries(String lang) {
        List<Country> countries = countryRepository.getCountriesByLanguage(lang);
        String result = Constants.NULL_ARRAY;
        if (countries.size() > 0) {
            JSONArray ja = new JSONArray();
            ja.put(Constants.COUNTRY_ID_KEY);
            ja.put(Constants.LANGUAGE_KEY);
            ja.put(Constants.COUNTRY_CODE_KEY);
            ja.put(Constants.COUNTRY_NAME_KEY);

            String strCountries = countries.stream()
                    .map(Country::toString)
                    .collect(Collectors.joining(Constants.OBJECT_DELIMITER));

            JSONArray jsonCountries = CDL.toJSONArray(ja, strCountries);
            result = jsonCountries.toString();
        }
        return result;
    }
}
