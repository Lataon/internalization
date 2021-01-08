package com.gmail.elbaglikov.bean;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Integer id;

    @Column(name = "locale", nullable = false)
    private String language;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "country_name", nullable = false)
    private String name;

    public Country() {
    }

    public Country(Integer id, String language, String code, String name) {
        this.id = id;
        this.language = language;
        this.code = code;
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return String.format("%d, %s, %s, %s", id, language, code, name);
    }
}
