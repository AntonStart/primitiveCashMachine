package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ArrayList;
import java.util.Collection;

class InfoCommand implements Command{
    @Override
    public void execute() {
        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.size()==0) System.out.println("No money available.");
        else {
            for (CurrencyManipulator currencyManipulator : currencyManipulators) {
                if (currencyManipulator.hasMoney())
                System.out.println((currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount()));
            }
        }
    }
}
