package com.mybot1.feature;


import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MonoBankCurrencyService implements CurrencyService {
    @Override
    public double getRate(Currency currency) {
            String url = "https://api.monobank.ua/bank/currency";

            //Get JSON
            String json;
            try {
                json = Jsoup
                        .connect(url)
                        .ignoreContentType(true)
                        .get()
                        .body()
                        .text();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalStateException("Can't connect to Monobank API");
            }

            //Convert json => Java Object
            Type typeToken = TypeToken
                    .getParameterized(List.class, CurrencyItem.class)
                    .getType();
            List<CurrencyItem> currencyItems = new Gson().fromJson(json, typeToken);

            return currencyItems.stream()
                    .filter(it -> it.getCcy() == currency)
                    .filter(it -> it.getBase_ccy() == Currency.UAH)
                    .map(CurrencyItem::getBuy)
                    .findFirst()
                    .orElseThrow();
        }
    }
