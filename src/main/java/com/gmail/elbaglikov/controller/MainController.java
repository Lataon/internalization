package com.gmail.elbaglikov.controller;

import com.gmail.elbaglikov.service.CityService;
import com.gmail.elbaglikov.service.CountriesService;
import com.gmail.elbaglikov.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @Autowired
    private CityService cityService;

    @Autowired
    private LanguageService langService;

    @Autowired
    private CountriesService countriesService;

    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public String getLanguages() {
        return langService.getAll();
    }
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public String getCountries(@RequestParam("lang") String lang) {
        return countriesService.getCountries(lang);
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public String getCities(@RequestParam("country") String countryCode, @RequestParam("lang") String lang) {
        return cityService.getCities(countryCode, lang);
    }
}
