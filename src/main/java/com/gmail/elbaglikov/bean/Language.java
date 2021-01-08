package com.gmail.elbaglikov.bean;

import javax.persistence.*;

@Entity
@Table(name = "languages")
public class Language {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Integer id;

    @Column(name = "lang_name", nullable = false)
    private String name;

    @Column(name = "locale", nullable = false)
    private String locale;

    public Language() {
    }

    public Language(Integer id, String name, String locale) {
        this.id = id;
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
