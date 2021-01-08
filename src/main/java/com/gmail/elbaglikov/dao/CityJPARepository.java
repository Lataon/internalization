package com.gmail.elbaglikov.dao;

import com.gmail.elbaglikov.bean.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityJPARepository extends CrudRepository<City, Integer> {
    List<City> getCitiesByCountryCodeAndLanguage(String countryCode, String language);
}
