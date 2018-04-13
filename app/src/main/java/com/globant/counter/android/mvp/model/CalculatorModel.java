package com.globant.counter.android.mvp.model;

import com.globant.counter.android.core.CalculatorContants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorModel {

    private String currentDigit;
    private ArrayList<String> digitsList;
    private double total = 0;
    List<String> operators = Arrays.asList(CalculatorContants.ADDITION, CalculatorContants.SUBSTRACTION, CalculatorContants.DIVISION, CalculatorContants.MULTIPLICATION);

    public String getCurrentDigit() {
        return currentDigit;
    }

    public void setCurrentDigit(String digit) {
        this.currentDigit = digit;
    }

    public void addDigit(String digit) {
        if (digitsList == null) {
            digitsList = new ArrayList<>();
        }
        //If its numeric
        if (!isOperator(digit)) {
            addNumber(digit);
            setCurrentDigit(getCurrentDigit() + digit);
            //if is operator
        } else {
            if (digitsList.size() > 0) {
                addOperator(digit);
                setCurrentDigit(getCurrentDigit() + digit);
            }
        }
    }

    public void addNumber(String digit) {
        //if digitList is empty, we add the number
        if (digitsList.isEmpty()) {
            digitsList.add(digit);
        } else {
            //if last index is numeric, we attach the digit new to the last index value, ex 5+3 = 53
            if (isOperator(getLastDigitValue()) == false) {
                digitsList.set(getLastDigitIndex(), getLastDigitValue() + digit);
            } else {
                digitsList.add(digit);
            }
        }
    }

    //If user enters an operator twice in a row, the 2nd press will be ignored
    public void addOperator(String digit) {

        if ((digitsList.size() > 0) && isOperator(digitsList.get(digitsList.size() - 1))) {
        } else {
            digitsList.add(digit);
        }
    }


    public String getTotal() throws RuntimeException {
        double total = 0;

        try {
            for (int i = 0; i <= digitsList.size() - 2; i++) {
                //operates with the first 3 digits in the list
                if (i == 0) {
                    total += calculateTotal(Integer.parseInt(digitsList.get(i)), digitsList.get(i + 1), Integer.parseInt(digitsList.get(i + 2)));
                    //if it's not the first iteration, it wil operate with the 3th and next digits, ex 3th,4th and 5th from digitList.
                } else {
                    total = calculateTotal(total, digitsList.get(i + 1), Integer.parseInt(digitsList.get(i + 2)));
                }
                i++;
            }
        } catch (RuntimeException e) {
            return "";
        }


        return total + "";
    }

    public void deleteAll() {
        if (digitsList != null) {
            digitsList.clear();
        }
    }

    public boolean deleteLastDigit() {
        if (digitsList != null && digitsList.size() > 0) {
            digitsList.remove(getLastDigitIndex());
            return true;
        }
        return false;
    }


    public double calculateTotal(double val1, String operator, int val2) throws RuntimeException {
        double total = 0;
        switch (operator) {

            case CalculatorContants.ADDITION:
                total = val1 + val2;
                break;
            case CalculatorContants.SUBSTRACTION:
                total = val1 - val2;
                break;

            case CalculatorContants.MULTIPLICATION:
                total = val1 * val2;
                break;

            case CalculatorContants.DIVISION:
                total = val1 / val2;
                break;
        }

        this.total = total;

        return this.total;
    }

    public String getAllDigits() {
        String allDigits = "";
        if (digitsList != null && digitsList.size() > 0) {
            for (int i = 0; i <= digitsList.size() - 1; i++) {
                allDigits = allDigits + digitsList.get(i);
            }
        }
        return allDigits;
    }

    public boolean isOperator(String digit) {
        if (operators.contains(digit)) {
            return true;
        }
        return false;
    }

    public String getLastDigitValue() {
        if (digitsList != null && digitsList.size() > 0) {
            return digitsList.get(digitsList.size() - 1);
        } else {
            return "";
        }

    }

    public int getLastDigitIndex() {
        return digitsList.size() - 1;
    }
}
