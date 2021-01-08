package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Constants;
import com.gmail.elbaglikov.bean.Language;
import com.gmail.elbaglikov.dao.LanguageJPARepository;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    @Autowired
    private LanguageJPARepository languageRepository;

    public String getAll(){
        List<Language> langs = languageRepository.getAll();
        String result = Constants.NULL_ARRAY;
        System.out.println(langs.size());
        if (langs.size() > 0) {
            JSONArray ja = new JSONArray();
            ja.put(Constants.LANGUAGE_ID_KEY);
            ja.put(Constants.LANGUAGE_NAME);
            ja.put(Constants.LANGUAGE_KEY);

            String strLangs = langs.stream()
                    .map(Language::toString)
                    .collect(Collectors.joining(Constants.OBJECT_DELIMITER));
            JSONArray jsonLangs = CDL.toJSONArray(ja, strLangs);
            result = jsonLangs.toString();
        }
        return result;
    }
}
