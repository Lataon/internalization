package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.dao.cities.CityDeJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEnJPARepository;
import com.gmail.elbaglikov.dao.cities.CityEsJPARepository;
import com.gmail.elbaglikov.enums.LangEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


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

    @Test
    public void getAllByCodeAndLang() {
        //TODO
    }

    @Test
    public void getAllByLang() {
        //TODO
    }

    @Test
    public void getAll() {
        //TODO
    }
}