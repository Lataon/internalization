package com.gmail.elbaglikov.controller;

import com.gmail.elbaglikov.service.CityService;
import com.gmail.elbaglikov.service.CountriesService;
import com.gmail.elbaglikov.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getCountries(@RequestParam(required = false) String lang) {
        String response = "";
        if (lang!=null) {
            response = countriesService.getAllByLang(lang);
        }
        else {
            response = countriesService.getAll();
        }
        return response;
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public String getCities(@RequestParam(required = false) String country,
                            @RequestParam String lang) {
        String response = "";
        if (country!=null){
            response = cityService.getAllByCodeAndLang(country, lang);
        } else {
            response = cityService.getAllByLang(lang);
        }
        return response;
    }
}
