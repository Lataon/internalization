package com.gmail.elbaglikov.dao;

import com.gmail.elbaglikov.enums.LangEnum;
import com.gmail.elbaglikov.bean.Language;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class LanguageMemoryRepositoryImpl implements LanguageRepository {
    private final static List<Language> LANGUAGES;

    static {
        LANGUAGES = Arrays.asList(
                new Language(1, LangEnum.EN.getFullName(), LangEnum.EN.name().toLowerCase()),
                new Language(2, LangEnum.DE.getFullName(),LangEnum.DE.name().toLowerCase()),
                new Language(3, LangEnum.ES.getFullName(),LangEnum.ES.name().toLowerCase())
        );
    }

    public List<Language> getAll() {
        return LANGUAGES;
    }
}
