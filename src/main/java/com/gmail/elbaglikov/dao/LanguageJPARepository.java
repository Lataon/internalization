package com.gmail.elbaglikov.dao;

import com.gmail.elbaglikov.bean.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageJPARepository extends CrudRepository<Language, Integer> {

    @Query(value = "SELECT * FROM languages", nativeQuery = true)
    List<Language> getAll();
}
