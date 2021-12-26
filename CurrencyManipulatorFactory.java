package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode.toUpperCase(Locale.ROOT));
        map.putIfAbsent(currencyCode.toUpperCase(Locale.ROOT),currencyManipulator);
        return map.get(currencyCode.toUpperCase(Locale.ROOT));
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
