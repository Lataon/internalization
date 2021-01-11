package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Util;
import com.gmail.elbaglikov.enums.LangEnum;
import com.gmail.elbaglikov.bean.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    public String getAllByCodeAndLang(String countryCode, String language) {
        List<City> cities = getAllFromRepository(countryCode, language);
        return Util.getArrayJsonCity(cities);
    }

    public String getAllByLang(String language) {
        List<City> cities = LangEnum.valueOf(language.toUpperCase()).getAll();
        return Util.getArrayJsonCity(cities);
    }

    public String getAll() {
        List<City> cities = LangEnum.getAllCities();
        return Util.getArrayJsonCity(cities);
    }

    private List<City> getAllFromRepository(String countryCode, String language){
        return LangEnum.valueOf(language.toUpperCase()).getAllByCountryCode(countryCode);
    }
}
