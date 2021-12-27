package com.javarush.task.task26.task2613;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine .class.getPackage().getName() + ".resources.common_en");

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = null;
        try {
            s = bis.readLine();
            if (s.equalsIgnoreCase(res.getString("operation.EXIT"))) throw new InterruptOperationException();
        } catch (IOException e) {
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String s = readString();
        while (s.length()!=3) {
            writeMessage(res.getString("invalid.data"));
            s = readString();
        }
        return s.toUpperCase(Locale.ROOT);
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String s = readString();
        s = s.trim();
        while(true) {
            if(s.matches("^[1-9]\\d*\\s[1-9]\\d*")) {
            String[] array = s.split("\\s");
                if (Integer.parseInt(array[0])<=0 || Integer.parseInt(array[1])<=0) {
                    writeMessage(res.getString("invalid.data"));
                    s = readString();
                } else return array;
            } else {
                writeMessage(res.getString("invalid.data"));
                s = readString();
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(String.format("%s(1 - %s, 2 - %s, 3 - %s, 4 - %s):",res.getString("choose.operation"),res.getString("operation.INFO"), res.getString("operation.DEPOSIT"), res.getString("operation.WITHDRAW"), res.getString("operation.EXIT")));
        String s = readString();
        try {
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            writeMessage(res.getString("invalid.data"));
            continue;
        }  catch (IllegalArgumentException e) {
            writeMessage(res.getString("invalid.data"));
            continue;
        }
    }
    }
    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
