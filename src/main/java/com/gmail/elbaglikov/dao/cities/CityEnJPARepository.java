package com.gmail.elbaglikov.dao.cities;

import com.gmail.elbaglikov.bean.CityEn;
import com.gmail.elbaglikov.dao.CityJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityEnJPARepository extends CityJPARepository, JpaRepository<CityEn, Integer> {
    Page<CityEn> getAllByCountryCode(String countryCode, Pageable pageable);
}
