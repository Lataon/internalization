package com.gmail.elbaglikov.enums;

import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.bean.CityDe;
import com.gmail.elbaglikov.bean.CityEn;
import com.gmail.elbaglikov.bean.CityEs;
import com.gmail.elbaglikov.dao.cities.CityDeJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEnJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEsJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        public Page<City> getAllByCountryCode(String countryCode, Pageable pageable) {
            return cityEnRepository.getAllByCountryCode(countryCode, pageable).map(e->e);
        }
        @Override
        public Page<City> getAll(Pageable pageable) {
            return cityEnRepository.findAll(pageable).map(e->e);
        }
        @Override
        public long getTotalSize() {
            return cityEnRepository.count();
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
        public Page<City> getAllByCountryCode(String countryCode, Pageable pageable) {
            return cityDeRepository.getAllByCountryCode(countryCode, pageable).map(e->e);
        }
        @Override
        public Page<City> getAll(Pageable pageable) {
            return cityDeRepository.findAll(pageable).map(e->e);
        }
        @Override
        public long getTotalSize() {
            return cityDeRepository.count();
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
        public Page<City> getAllByCountryCode(String countryCode, Pageable pageable) {
            return cityEsRepository.getAllByCountryCode(countryCode, pageable).map(e->e);
        }
        @Override
        public Page<City> getAll(Pageable pageable) {
            return cityEsRepository.findAll(pageable).map(e->e);
        }
        @Override
        public long getTotalSize() {
            return cityEsRepository.count();
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
    public abstract Page<City> getAllByCountryCode(String countryCode, Pageable pageable);
    public abstract Page<City> getAll(Pageable pageable);
    public abstract long getTotalSize();

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

    public static  Page<City> getAllCities(Pageable pageable) {
        return getCitiesByExecutable(null, pageable,
                (pageable1, l) -> l.getAll(pageable1));
    }

    public static  Page<City> getAllCitiesByCountryCode(String countryCode, Pageable pageable) {
        return getCitiesByExecutable(countryCode, pageable,
                (pageable1, l) -> l.getAllByCountryCode(countryCode, pageable1));
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

        @PostConstruct
        public void postConstruct() {
            LangEnum.cityDeRepository = cityDeRepository;
        }
    }

    @Component
    public static class CityEsRepositoryIniter {
        @Autowired
        private CityEsJPARepository cityEsRepository;

        @PostConstruct
        public void postConstruct() {
            LangEnum.cityEsRepository = cityEsRepository;
        }
    }

    private interface Executable {
        Page<City> execute(Pageable pageable, LangEnum l);
    }

    private static Page<City> getCitiesByExecutable(String countryCode, Pageable pageable, Executable executable) {
        final int PAGE_SIZE = pageable.getPageSize();
        final long PAGE_NUMBER = pageable.getPageNumber()+1;
        int totalPageNumber = 0;
        long totalLangElements = 0;
        boolean flag = true;
        Page<City> page = null;
        for (LangEnum l : LangEnum.values()) {
            int previousPageNumber = totalPageNumber;
            long langElements = l.getTotalSize();
            int addPage = langElements%PAGE_SIZE>0 ? 1 : 0;
            int pageNumber = (int)((langElements/PAGE_SIZE + addPage));
            totalPageNumber+=pageNumber;
            totalLangElements+=langElements;
            if ((PAGE_NUMBER <= totalPageNumber)&&flag) {
                page = executable.execute(PageRequest.of(pageable.getPageNumber()-previousPageNumber, PAGE_SIZE), l);
                flag = false;
            }
        }
        page = page !=null ? page : Page.empty();
        return new PageImpl<>(page.get().collect(Collectors.toList()), pageable, totalLangElements );
    }

}
