package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Util;
import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.dao.cities.CityDeJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEnJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEsJPARepository;
import com.gmail.elbaglikov.enums.LangEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.elbaglikov.Data.*;
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
        public LangEnum.CityEnRepositoryIniter cityEnRepositoryIniter(){
            return new LangEnum.CityEnRepositoryIniter();
        }
        @Bean
        public LangEnum.CityDeRepositoryIniter cityDeRepositoryIniter(){
            return new LangEnum.CityDeRepositoryIniter();
        }
        @Bean
        public LangEnum.CityEsRepositoryIniter cityEsRepositoryIniter(){
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

    @Test
    public void getAllByCodeAndLang() {

        Mockito.when(cityEnJPARepository.getAllByCountryCode("en")).thenReturn(CITIES_EN);
        String cityEnActual = service.getAllByCodeAndLang("en", "en");
        String cityEnExpected = Util.getArrayJsonCity(new ArrayList<>(CITIES_EN));
        assertEquals(cityEnExpected, cityEnActual);
    }

    @Test
    public void getAllByLang() {

        Mockito.when(cityDeJPARepository.getAll()).thenReturn(CITIES_DE);
        String cityEnActual = service.getAllByLang("en");
        String cityEnExpected =Util.getArrayJsonCity(new ArrayList<>(CITIES_DE));
        assertNotEquals(cityEnExpected, cityEnActual);
        assertEquals("[]", cityEnActual);
        cityEnActual = service.getAllByLang("de");
        assertEquals(cityEnExpected, cityEnActual);
    }

    @Test
    public void getAll() {
        Mockito.when(cityEnJPARepository.getAll()).thenReturn(CITIES_EN);
        Mockito.when(cityDeJPARepository.getAll()).thenReturn(CITIES_DE);
        Mockito.when(cityEsJPARepository.getAll()).thenReturn(CITIES_ES);
        String cityEnActual = service.getAll();
        List<City> cities = new ArrayList<>();
        cities.addAll(CITIES_EN);
        cities.addAll(CITIES_DE);
        cities.addAll(CITIES_ES);
        String cityEnExpected = Util.getArrayJsonCity(cities);
        assertEquals(cityEnExpected, cityEnActual);
    }
}