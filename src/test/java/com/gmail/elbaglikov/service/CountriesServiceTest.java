package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.Util;
import com.gmail.elbaglikov.bean.Country;
import com.gmail.elbaglikov.dao.CountryJPARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.gmail.elbaglikov.Data.COUNTRIES;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CountriesServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public CountriesService countriesService() {
            return new CountriesService();
        }
    }

    @Autowired
    private CountriesService service;

    @MockBean
    private CountryJPARepository countryJPARepository;

    @Test
    public void getAllByLang() {
        List<Country> countriesEn = COUNTRIES.subList(0, 3);
        Mockito.when(countryJPARepository.getAllByLanguage("en")).thenReturn(countriesEn);
        String actual = service.getAllByLang("en");
        String expected = Util.getArrayJsonCountry(countriesEn);
        assertEquals(expected, actual);
    }

    @Test
    public void getAll() {
        Mockito.when(countryJPARepository.findAll()).thenReturn(COUNTRIES);
        String actual = service.getAll();
        String expected = Util.getArrayJsonCountry(COUNTRIES);
        assertEquals(expected, actual);
    }
}