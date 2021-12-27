package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".login_en");
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String currentCardNumber = ConsoleHelper.readString();
            if (currentCardNumber.matches("^[0-9]{12}")) {
                ConsoleHelper.writeMessage("Введите PIN-код:");
                String currentPin = ConsoleHelper.readString();
                if (currentPin.matches("^[0-9]{4}")) {
                    if (validCreditCards.containsKey(currentCardNumber) && validCreditCards.getString(currentCardNumber).equals(currentPin)) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), currentCardNumber));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), currentCardNumber));
                        ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"), currentCardNumber));
                        ConsoleHelper.writeMessage(String.format(res.getString("try.again.with.details"), currentCardNumber));
                        continue;
                    }
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), currentCardNumber));
                    ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"), currentCardNumber));
                    ConsoleHelper.writeMessage(String.format(res.getString("try.again.with.details"), currentCardNumber));
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), currentCardNumber));
                ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"), currentCardNumber));
                ConsoleHelper.writeMessage(String.format(res.getString("try.again.with.details"), currentCardNumber));
                continue;
            }


        }
    }
}
