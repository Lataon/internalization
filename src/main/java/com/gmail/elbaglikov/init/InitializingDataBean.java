package com.gmail.elbaglikov.init;

import com.gmail.elbaglikov.SPARQLJAVA.*;
import com.gmail.elbaglikov.bean.*;
import com.gmail.elbaglikov.dao.CountryJPARepository;
import com.gmail.elbaglikov.dao.LanguageMemoryRepositoryImpl;
import com.gmail.elbaglikov.enums.LangEnum;
import com.gmail.elbaglikov.exceptions.InitDataException;
import com.gmail.elbaglikov.factory.CitiesFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class InitializingDataBean implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private LanguageMemoryRepositoryImpl languageRepository;
    private CountryJPARepository countryRepository;

    @Autowired
    public InitializingDataBean(LanguageMemoryRepositoryImpl languageRepository,
                                CountryJPARepository countryRepository) {
        this.languageRepository = languageRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Start getting data from wiki");
        LangEnum.deleteCitiesFromAllRepositories(); //clear database
        try {
            countryRepository.deleteAll();
            List<Language> languages = languageRepository.getAll();
            URI endpoint = new URI("https://query.wikidata.org/sparql");
            for (Language lang : languages) {
                String querySelect = "SELECT ?cityLabel ?countryLabel ?code\n" +
                        "WHERE \n" +
                        "{\n" +
                        "    ?city wdt:P31/wdt:P279* wd:Q515 .  # find instances of subclasses of city\n" +
                        "    ?city wdt:P17 ?country .   # Also find the country of the city\n" +
                        "    ?country wdt:P297 ?code ." +
                        "\n" +
                        "    # choose language\n" +
                        "    SERVICE wikibase:label {\n" +
                        "        bd:serviceParam wikibase:language \"" + lang.getLocale() + "\" .\n" +
                        "    }\n" +
                        "} ";
                SparqlClient sc = new SparqlClient(false);
                sc.setEndpointRead(endpoint);
                SparqlResult sr = sc.query(querySelect);
                saveResult(sr.getModel(), lang);
            }
            logger.info("Getting data from wiki was executed successfully");
        } catch (URISyntaxException | SparqlClientException e) {
            final String MESSAGE = "Problem with getting data from wiki";

            logger.error(MESSAGE);
            throw new InitDataException(MESSAGE, e);
        }
    }

    private void saveResult(SparqlResultModel rs, Language lang) {
        final int CITY_KEY = 0;
        final int COUNTRY_KEY = 1;
        final int CODE_KEY = 2;

        Set<Country> countries = new HashSet<>();
        LangEnum langEnum = LangEnum.valueOf(lang.getLocale().toUpperCase());

        for (HashMap<String, Object> row : rs.getRows()) {
            List<String> variables = rs.getVariables();
            String locale = lang.getLocale();
            String cityName = (String) row.get(variables.get(CITY_KEY));
            if (!cityName.matches(".*\\d+.*")) {//ignore illegal data from wiki
                String countryName = (String) row.get(variables.get(COUNTRY_KEY));
                String code = ((String) row.get(variables.get(CODE_KEY))).toLowerCase();

                City city = CitiesFactory.getCityByLocale(locale, code, cityName);
                langEnum.addCityToList(city);

                Country country = new Country(locale, code, countryName);
                countries.add(country);               
            }
        }
        countryRepository.saveAll(countries);
        langEnum.saveCities();
    }
}