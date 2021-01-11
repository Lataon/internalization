package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Util;
import com.gmail.elbaglikov.bean.Language;
import com.gmail.elbaglikov.dao.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public String getAll(){
        List<Language> langs = languageRepository.getAll();
        return Util.getArrayJsonLang(langs);
    }
}
