package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        String vCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(vCode);

        while(true) {
            ConsoleHelper.writeMessage("Пожалуйста введите необходимую сумму:");
            String s = ConsoleHelper.readString();
            if (s.matches("^[1-9]\\d*")) {
                int expectedAmount = Integer.parseInt(s);
                if(currencyManipulator.isAmountAvailable(expectedAmount)) {
                    Map<Integer, Integer> map = new HashMap<>();
                    try {
                        map.putAll(currencyManipulator.withdrawAmount(expectedAmount));
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage("Нехватает купюр");
                        continue;
                    }
                    List<Integer> list = new ArrayList<>();
                    list.addAll(map.keySet());
                    Collections.sort(list);
                    Collections.reverse(list);
                    for (Integer key: list) {
                        ConsoleHelper.writeMessage("\t" + key + " - " + map.get(key));
                    }
                    ConsoleHelper.writeMessage("Операция завершена");
                    break;
                }
            }
        }

    }
}
