package com.gmail.elbaglikov.dao;

import com.gmail.elbaglikov.bean.Language;

import java.util.List;

public interface LanguageRepository {
    List<Language> getAll();
}
