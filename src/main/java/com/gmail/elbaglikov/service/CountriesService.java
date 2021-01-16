package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Util;
import com.gmail.elbaglikov.bean.Country;
import com.gmail.elbaglikov.dao.CountryJPARepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {

    @Autowired
    private CountryJPARepository countryRepository;

    public String getAllByLang(String lang) {
        List<Country> countries = countryRepository.getAllByLanguage(lang);
        return Util.getArrayJsonCountry(countries);
    }

    public String getAll(){
        List<Country> countries = countryRepository.findAll();
        return Util.getArrayJsonCountry(countries);
    }

    public String getAllCodes() {
        return new JSONArray(countryRepository.getCodes()).toString();
    }
}
