package com.gmail.elbaglikov.dao.cities;

import com.gmail.elbaglikov.bean.CityDe;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDeJPARepository extends CityJPARepository, JpaRepository<CityDe, Integer> {
    Page<CityDe> getAllByCountryCode(String countryCode, Pageable pageable);
}
