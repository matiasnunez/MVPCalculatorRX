package com.globant.counter.android.util.bus.observers;

public abstract class CalculatorButtonBusObserver extends BusObserver<CalculatorButtonBusObserver.CalculatorButtonPressed> {


    public CalculatorButtonBusObserver() {
        super(CalculatorButtonPressed.class);
    }

    public static class CalculatorButtonPressed {

        private String digit;

        public CalculatorButtonPressed(String digit) {
            this.digit = digit;
        }

        public String getDigit() {
            return digit;
        }

    }


}
