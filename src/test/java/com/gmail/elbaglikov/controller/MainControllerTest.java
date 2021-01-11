package com.gmail.elbaglikov.controller;

import com.gmail.elbaglikov.Util;
import com.gmail.elbaglikov.bean.*;
import com.gmail.elbaglikov.service.CityService;
import com.gmail.elbaglikov.service.CountriesService;
import com.gmail.elbaglikov.service.LanguageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.elbaglikov.Data.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;
    @MockBean
    private LanguageService langService;
    @MockBean
    private CountriesService countriesService;

    @Test
    public void getLanguages() throws Exception {
        given(langService.getAll()).willReturn(Util.getArrayJsonLang(LANGS));
        mvc.perform(get("/languages")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(LANGS.get(0).getName())));
    }

    @Test
    public void getCountries() throws Exception {
        given(countriesService.getAll()).willReturn(Util.getArrayJsonCountry(COUNTRIES));
        mvc.perform(get("/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[8].name", is(COUNTRIES.get(8).getName())));
    }

    @Test
    public void getCities() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.addAll(CITIES_EN);
        cities.addAll(CITIES_DE);
        cities.addAll(CITIES_ES);
        given(cityService.getAll()).willReturn(Util.getArrayJsonCity(cities));

        mvc.perform(get("/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[8].name", is(cities.get(8).getName())));
    }
}