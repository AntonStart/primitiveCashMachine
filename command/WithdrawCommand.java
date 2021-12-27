package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String vCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(vCode);
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String s = ConsoleHelper.readString();
            if (s.matches("^[1-9]\\d*")) {
                int expectedAmount = Integer.parseInt(s);
                if (expectedAmount<0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if(currencyManipulator.isAmountAvailable(expectedAmount)) {
                    Map<Integer, Integer> map = new HashMap<>();
                    try {
                        map.putAll(currencyManipulator.withdrawAmount(expectedAmount));
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                        continue;
                    }
                    List<Integer> list = new ArrayList<>();
                    list.addAll(map.keySet());
                    Collections.sort(list);
                    Collections.reverse(list);
                    for (Integer key: list) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),key, map.get(key)));
                    }
                    ConsoleHelper.writeMessage("Операция завершена");
                    break;
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
            }
        }

    }
}
