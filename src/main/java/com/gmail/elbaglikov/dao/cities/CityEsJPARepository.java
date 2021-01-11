package com.gmail.elbaglikov.dao.cities;

import com.gmail.elbaglikov.bean.CityEs;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityEsJPARepository extends CityJPARepository, JpaRepository<CityEs, Integer> {
    List<CityEs> getAllByCountryCode(String countryCode);

    @Query(value = "SELECT * FROM CITY_ES", nativeQuery = true)
    List<CityEs> getAll();
}
