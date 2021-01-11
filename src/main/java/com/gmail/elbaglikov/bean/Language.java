package com.gmail.elbaglikov.bean;

public class Language {
    private Integer id;

    private String name;

    private String locale;

    public Language() {
    }

    public Language(Integer id, String name, String locale) {
        this(name, locale);
        this.id = id;
    }

    public Language(String name, String locale) {
        this.name = name;
        this.locale = locale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String toString() {
        return String.format("%d, %s, %s", id, name, locale);
    }
}
