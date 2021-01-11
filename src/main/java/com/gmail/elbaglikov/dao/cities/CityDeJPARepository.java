package com.gmail.elbaglikov.dao.cities;

import com.gmail.elbaglikov.bean.CityDe;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityDeJPARepository extends CityJPARepository, JpaRepository<CityDe, Integer> {
    List<CityDe> getAllByCountryCode(String countryCode);

    @Query(value = "SELECT * FROM CITY_DE", nativeQuery = true)
    List<CityDe> getAll();
}
