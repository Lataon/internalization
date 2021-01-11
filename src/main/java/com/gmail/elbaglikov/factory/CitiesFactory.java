package com.gmail.elbaglikov.factory;

import com.gmail.elbaglikov.bean.City;
import com.gmail.elbaglikov.bean.CityDe;
import com.gmail.elbaglikov.bean.CityEn;
import com.gmail.elbaglikov.bean.CityEs;

public class CitiesFactory {
    private enum CityEnum {
        EN {
            @Override
            City getCity(String code, String name) {
                return new CityEn(code, name);
            }
        },
        DE {
            @Override
            City getCity(String code, String name) {
                return new CityDe(code, name);
            }
        },
        ES {
            @Override
            City getCity(String code, String name) {
                return new CityEs(code, name);
            }
        };

        abstract City getCity(String code, String name);
    }

    public static City getCityByLocale(String locale, String code, String name) {
        return CityEnum.valueOf(locale.toUpperCase()).getCity(code, name);
    }
}
