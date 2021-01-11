package com.gmail.elbaglikov.enums;

import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.bean.CityDe;
import com.gmail.elbaglikov.bean.CityEn;
import com.gmail.elbaglikov.bean.CityEs;
import com.gmail.elbaglikov.dao.cities.CityDeJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEnJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEsJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public enum LangEnum {
    EN("English"){
        @Override
        public void addCityToList(City city) {
            cities.add((CityEn)city);
        }
        @Override
        public void saveCities() {
            cityEnRepository.saveAll(cities);
        }
        @Override
        public void deleteAllCities() {
            cityEnRepository.deleteAll();
        }
        @Override
        public List<City> getAllByCountryCode(String countryCode) {
            List<City> cities = new ArrayList<>(cityEnRepository.getAllByCountryCode(countryCode));
            return cities;
        }
        @Override
        public List<City> getAll() {
            List<City> cities = new ArrayList<>(cityEnRepository.getAll());
            return cities;
        }

        private List<CityEn> cities = new ArrayList<>();
    },

    DE("Germany"){
        @Override
        public void addCityToList(City city) {
            cities.add((CityDe)city);
        }
        @Override
        public void saveCities() {
            cityDeRepository.saveAll(cities);
        }
        @Override
        public void deleteAllCities() {
            cityDeRepository.deleteAll();
        }
        @Override
        public List<City> getAllByCountryCode(String countryCode) {
            List<City> cities = new ArrayList<>(cityDeRepository.getAllByCountryCode(countryCode));
            return cities;
        }
        @Override
        public List<City> getAll() {
            List<City> cities = new ArrayList<>(cityDeRepository.getAll());
            return cities;
        }
        private List<CityDe> cities = new ArrayList<>();
    },

    ES("Spanish"){
        @Override
        public void addCityToList(City city) {
            cities.add((CityEs)city);
        }
        @Override
        public void saveCities() {
            cityEsRepository.saveAll(cities);
        }
        @Override
        public void deleteAllCities() {
            cityEsRepository.deleteAll();
        }
        @Override
        public List<City> getAllByCountryCode(String countryCode) {
            List<City> cities = new ArrayList<>(cityEsRepository.getAllByCountryCode(countryCode));
            return cities;
        }
        @Override
        public List<City> getAll() {
            List<City> cities = new ArrayList<>(cityEsRepository.getAll());
            return cities;
        }
        private List<CityEs> cities = new ArrayList<>();
    };
    private String fullName;

    LangEnum(String fullName) {
        this.fullName = fullName;
    }

    public abstract void addCityToList(City city);
    public abstract void saveCities();
    public abstract void deleteAllCities();
    public abstract List<City> getAllByCountryCode(String countryCode);
    public abstract List<City> getAll();

    public String getFullName(){
        return fullName;
    }

    private static CityEnJPARepository cityEnRepository;
    private static CityDeJPARepository cityDeRepository;
    private static CityEsJPARepository cityEsRepository;

    public static void deleteCitiesFromAllRepositories() {
        for (LangEnum le : LangEnum.values()) {
            le.deleteAllCities();
        }
    }

    public static  List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        for (LangEnum le : LangEnum.values()) {
            cities.addAll(le.getAll());
        }
        return cities;
    }

    @Component
    public static class CityEnRepositoryIniter {
        @Autowired
        private CityEnJPARepository cityEnRepository;

        @PostConstruct
        public void postConstruct() {
            LangEnum.cityEnRepository = cityEnRepository;
        }
    }

    @Component
    public static class CityDeRepositoryIniter {
        @Autowired
        private CityDeJPARepository cityDeRepository;

        public CityDeJPARepository getCityDeRepository() {
            return cityDeRepository;
        }
        @PostConstruct
        public void postConstruct() {
            LangEnum.cityDeRepository = cityDeRepository;
        }
    }

    @Component
    public static class CityEsRepositoryIniter {
        @Autowired
        private CityEsJPARepository cityEsRepository;

        public CityEsJPARepository getCityEsRepository() {
            return cityEsRepository;
        }
        @PostConstruct
        public void postConstruct() {
            LangEnum.cityEsRepository = cityEsRepository;
        }
    }

}
