package com.gmail.elbaglikov.dao.cities;

import com.gmail.elbaglikov.bean.CityEs;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityEsJPARepository extends CityJPARepository, JpaRepository<CityEs, Integer> {
    Page<CityEs> getAllByCountryCode(String countryCode, Pageable pageable);
}
