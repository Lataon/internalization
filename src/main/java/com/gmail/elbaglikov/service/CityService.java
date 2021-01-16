package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.enums.LangEnum;
import com.gmail.elbaglikov.bean.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    public Page<City> getAllByCodeAndLang(String countryCode, String language, Pageable pageable) {
        if (countryCode == null && language == null) {
            return getAll(pageable);
        }
        if (countryCode == null) {
            return getAllByLang(language, pageable);
        }
        if (language == null) {
            return getAllByCountryCode(countryCode, pageable);
        }
        return getAllFromRepository(countryCode, language, pageable);
    }

    private Page<City> getAllByLang(String language, Pageable pageable) {
        return LangEnum.valueOf(language.toUpperCase()).getAll(pageable);
    }

    private Page<City> getAllByCountryCode(String countryCode, Pageable pageable) {
        return LangEnum.getAllCitiesByCountryCode(countryCode, pageable);
    }

    private Page<City> getAll(Pageable pageable) {
        return LangEnum.getAllCities(pageable);
    }

    private Page<City> getAllFromRepository(String countryCode, String language, Pageable pageable){
        return LangEnum.valueOf(language.toUpperCase()).getAllByCountryCode(countryCode, pageable);
    }
}
