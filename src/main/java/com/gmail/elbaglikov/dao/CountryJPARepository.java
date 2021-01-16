package com.gmail.elbaglikov.dao;

import com.gmail.elbaglikov.bean.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryJPARepository extends JpaRepository<Country, Integer> {
    List<Country> getAllByLanguage(String language);
    List<Country> findAll();

    @Query(value = "SELECT CODE FROM COUNTRIES", nativeQuery = true)
    List<String> getCodes();
}
