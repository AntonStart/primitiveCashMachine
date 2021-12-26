package com.javarush.task.task26.task2613;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) throw new InterruptOperationException();
        } catch (IOException e) {
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Введите пожалуйста код валюты:");
        String s = readString();
        while (s.length()!=3) {
            writeMessage("Код валюты некорректен. Код должен содержать три латинских символа. Повторите попытку");
            s = readString();
        }
        return s.toUpperCase(Locale.ROOT);
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage("Введите пожалуйста номинал и количество купюр через пробел:");
        String s = readString();
        s = s.trim();
        while(true) {
            if(s.matches("^[1-9]\\d*\\s[1-9]\\d*")) {
            String[] array = s.split("\\s");
                if (Integer.parseInt(array[0])<=0 || Integer.parseInt(array[1])<=0) {
                    writeMessage("Ошибка, отрицательные числа, повторите попытку:");
                    s = readString();
                } else return array;
            } else {
                writeMessage("Ошибка, неверно введенные данные, повторите попытку:");
                s = readString();
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage("Выберете пожалуйста необходимую операцию (1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT):");
        String s = readString();
        try {
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            writeMessage("Ошибка, неверно введенный номер операции, повторите попытку:");
            continue;
        }  catch (IllegalArgumentException e) {
            writeMessage("Ошибка, неверно введенный номер операции, повторите попытку:");
            continue;
        }
    }
    }
}
