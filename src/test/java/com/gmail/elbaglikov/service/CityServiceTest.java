package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.bean.CityEn;
import com.gmail.elbaglikov.dao.cities.CityDeJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEnJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEsJPARepository;
import com.gmail.elbaglikov.enums.LangEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.elbaglikov.Data.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CityServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public CityService cityService() {
            return new CityService();
        }

        @Bean
        public LangEnum.CityEnRepositoryIniter cityEnRepositoryIniter() {
            return new LangEnum.CityEnRepositoryIniter();
        }

        @Bean
        public LangEnum.CityDeRepositoryIniter cityDeRepositoryIniter() {
            return new LangEnum.CityDeRepositoryIniter();
        }

        @Bean
        public LangEnum.CityEsRepositoryIniter cityEsRepositoryIniter() {
            return new LangEnum.CityEsRepositoryIniter();
        }
    }

    @Autowired
    private CityService service;

    @Autowired
    private LangEnum.CityEnRepositoryIniter cityEnRepositoryIniter;

    @Autowired
    private LangEnum.CityDeRepositoryIniter cityDeRepositoryIniter;

    @Autowired
    private LangEnum.CityEsRepositoryIniter cityEsRepositoryIniter;


    @MockBean
    private CityDeJPARepository cityDeJPARepository;

    @MockBean
    private CityEnJPARepository cityEnJPARepository;

    @MockBean
    private CityEsJPARepository cityEsJPARepository;

    @Before
    public void initRepositoryCount(){
        Mockito.when(cityEnJPARepository.count()).thenReturn((long)CITIES_EN.size());
        Mockito.when(cityDeJPARepository.count()).thenReturn((long)CITIES_DE.size());
        Mockito.when(cityEsJPARepository.count()).thenReturn((long)CITIES_ES.size());
    }

    @Test
    public void getAllByCodeAndLang() {
        final Pageable PAGEABLE = PageRequest.of(0, 5);
        final  int PAGE_SIZE = PAGEABLE.getPageSize();

        int subStart = 0;
        int subFinish = subStart + PAGE_SIZE;

        List<CityEn> filteredSubList = CITIES_EN.stream().
                filter(e -> e.getCountryCode().equals("en")).
                collect(Collectors.toList());

        Mockito.when(cityEnJPARepository.getAllByCountryCode("en", PAGEABLE)).
                thenReturn(new PageImpl<>(filteredSubList.subList(subStart, Math.min(subFinish, filteredSubList.size()))));

        Page<City> actualPage = service.getAllByCodeAndLang("en", "en", PAGEABLE);
        Page<City> expectedPage = new PageImpl<>(filteredSubList.subList(subStart, Math.min(subFinish, filteredSubList.size()))).map(e->e);

        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements());
        assertEquals(expectedPage.getTotalPages(), actualPage.getTotalPages());
        assertThat(actualPage, is(expectedPage));
    }

    @Test
    public void getAllByLang() {
        final Pageable PAGEABLE = PageRequest.of(1, 5);
        final  int PAGE_SIZE = PAGEABLE.getPageSize();

        Pageable actualPageable = PageRequest.of(1, PAGE_SIZE);
        int subStart = 5;
        int subFinish = subStart + PAGE_SIZE;

        List<CityEn> subList = CITIES_EN.subList(subStart, Math.min(subFinish, CITIES_EN.size()));

        Mockito.when(cityEnJPARepository.findAll(actualPageable)).
                thenReturn(new PageImpl<>(subList));

        Page<City> actualPage = service.getAllByCodeAndLang(null,"en", PAGEABLE);
        Page<City> expectedPage = new PageImpl<>(subList).map(e->e);

        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements());
        assertEquals(expectedPage.getTotalPages(), actualPage.getTotalPages());
        assertThat(actualPage, is(expectedPage));
    }



    @Test
    public void getAll() {
        final Pageable PAGEABLE = PageRequest.of(1, 5);
        final  int PAGE_SIZE = PAGEABLE.getPageSize();

        List<City> cities = new ArrayList<>();
        cities.addAll(CITIES_EN);
        cities.addAll(CITIES_DE);
        cities.addAll(CITIES_ES);

        Pageable actualPageable = PageRequest.of(1, PAGE_SIZE);
        int subStart = 5;
        int subFinish = subStart + PAGE_SIZE;
        Mockito.when(cityEnJPARepository.findAll(actualPageable)).
                thenReturn(new PageImpl<>(CITIES_EN.subList(subStart, Math.min(subFinish, CITIES_EN.size()))));
        /*Mockito.when(cityDeJPARepository.findAll(actualPageable)).
                thenReturn(new PageImpl<>(CITIES_DE.subList(subStart, Math.min(subFinish, CITIES_DE.size()))));
        Mockito.when(cityEsJPARepository.findAll(actualPageable)).
                thenReturn(new PageImpl<>(CITIES_ES.subList(subStart, Math.min(subFinish, CITIES_ES.size()))));*/

        Page<City> actualPage = service.getAllByCodeAndLang(null, null, PAGEABLE);

        subStart = 5;
        subFinish = 6;
        Page<City> expectedPage = new PageImpl<>(cities.subList(subStart, subFinish), PAGEABLE, cities.size());

        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements());
        assertEquals(expectedPage.getTotalPages(), actualPage.getTotalPages());
        assertThat(actualPage, is(expectedPage));
    }
}