package com.gmail.elbaglikov.dao.cities;

import com.gmail.elbaglikov.bean.CityEn;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityEnJPARepository extends CityJPARepository, JpaRepository<CityEn, Integer> {
    List<CityEn> getAllByCountryCode(String countryCode);

    @Query(value = "SELECT * FROM CITY_EN", nativeQuery = true)
    List<CityEn> getAll();
}
