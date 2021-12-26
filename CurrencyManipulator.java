package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int newCount = denominations.get(denomination) + count;
            denominations.remove(denomination);
            denominations.put(denomination, newCount);
        } else denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int allAmount = 0;
        for (Map.Entry<Integer,Integer> map : denominations.entrySet()) {
            allAmount += (map.getKey() * map.getValue());
        }
        return allAmount;
    }

    public boolean hasMoney() {
        return (denominations.size()>0);
    }

    public boolean isAmountAvailable(int expectedAmount) {
        if (expectedAmount<=getTotalAmount()) return true;
        else return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        int sum = expectedAmount;
        HashMap<Integer, Integer> copyDenominations = new HashMap<>(denominations);

        ArrayList<Integer> keys = new ArrayList<>(this.denominations.keySet());

        Collections.sort(keys);
        Collections.reverse(keys);

        TreeMap<Integer, Integer> resultMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (Integer denomination : keys) {
            final int key = denomination;
            int value = copyDenominations.get(key);
            while (true) {
                if (sum < key || value == 0) {
                    copyDenominations.put(key, value);
                    break;
                }
                sum -= key;
                value--;

                if (resultMap.containsKey(key))
                    resultMap.put(key, resultMap.get(key) + 1);
                else
                    resultMap.put(key, 1);
            }
        }

        if (sum > 0)
            throw new NotEnoughMoneyException();
        else {
            this.denominations.clear();
            this.denominations.putAll(copyDenominations);
        }
        return resultMap;
    }
}
