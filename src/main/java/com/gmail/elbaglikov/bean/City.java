package com.gmail.elbaglikov.bean;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Integer id;

    @Column(name = "locale", nullable = false)
    private String language;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "city_name", nullable = false)
    private String name;

    public City() {
    }

    public City(Integer id, String language, String countryCode, String name) {
        this.id = id;
        this.language = language;
        this.countryCode = countryCode;
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        return String.format("%d, %s, %s, %s", id, language, countryCode, name);
    }
}
