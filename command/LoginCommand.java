package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    @Override
    public void execute() throws InterruptOperationException {

        while (true) {
            ConsoleHelper.writeMessage("Введите номер карты (12 цифр):");
            String currentCardNumber = ConsoleHelper.readString();
            if (currentCardNumber.matches("^[0-9]{12}")) {
                ConsoleHelper.writeMessage("Введите PIN-код:");
                String currentPin = ConsoleHelper.readString();
                if (currentPin.matches("^[0-9]{4}")) {
                    if (validCreditCards.containsKey(currentCardNumber) && validCreditCards.getString(currentCardNumber).equals(currentPin)) {
                        ConsoleHelper.writeMessage("верификация прошла успешно!");
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Неправильный пин код или номер карты!");
                        continue;
                    }
                } else {
                    ConsoleHelper.writeMessage("Не валидные данные!");
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage("Не валидные данные!");
                continue;
            }


        }
    }
}
