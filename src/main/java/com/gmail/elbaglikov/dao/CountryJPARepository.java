package com.gmail.elbaglikov.dao;

import com.gmail.elbaglikov.bean.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryJPARepository extends CrudRepository<Country, Integer> {
    List<Country> getCountriesByLanguage (String language);
}
