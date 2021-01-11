package com.gmail.elbaglikov.bean;

import javax.persistence.*;

@MappedSuperclass
@Table(name = "cities")
public abstract class City {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Integer id;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "city_name", nullable = false)
    private String name;

    public City() {
    }

    public City(Integer id, String countryCode, String name) {
        this(countryCode, name);
        this.id = id;
    }

    public City(String countryCode, String name) {
        this.countryCode = countryCode;
        this.name = name;
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

    protected abstract String getLocale();

    public String toString() {
        return String.format("%d, %s, %s, %s", id, getLocale(), countryCode, name);
    }
}
