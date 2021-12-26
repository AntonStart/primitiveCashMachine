package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Вы действительно хотите завершить работу с банкоматом? (y - да, n - нет):");
        String s = ConsoleHelper.readString();
        if (s.equalsIgnoreCase("y")) {
                ConsoleHelper.writeMessage("Bye!!!!");
        } else {

        }
    }
}
