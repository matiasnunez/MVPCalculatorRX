package com.globant.counter.android.mvp.presenter;

import android.app.Activity;

import com.globant.counter.android.core.CalculatorContants;
import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.CalculatorButtonBusObserver;

public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onButtonPressed(String digit) {
        switch (digit) {
            //if CE
            case CalculatorContants.DELETE_ALL:
                deleteAll();
                break;
            //If C
            case CalculatorContants.DELETE_LAST_DIGIT:
                deleteLastDigit();
                break;
            //If =
            case CalculatorContants.EQUAL:
                showTotal();
                break;
            //if Numeric or operator
            default:
                addDigit(digit);
                break;
        }
    }

    public void addDigit(String digit) {

        model.addDigit(digit);

        setDisplayText(model.getLastDigitValue());
        setDisplayAllText(model.getAllDigits());

    }

    //shows on bottom textView the last button pressed
    public void setDisplayText(String digits) {
        view.setDisplayText(digits);
    }

    //shows on the upper textview, all the digits typed by the user
    public void setDisplayAllText(String digits) {
        view.setDisplayAllText(digits);
    }

    public void deleteAll() {
        model.deleteAll();
        setDisplayText("");
        setDisplayAllText("");
    }

    public void deleteLastDigit() {
        if(model.deleteLastDigit()) {
            setDisplayText(model.getLastDigitValue());
            setDisplayAllText(model.getAllDigits());
        }
    }

    public void showTotal() {
      if(!model.getTotal().equals("")){
          setDisplayText(model.getTotal() + "");
          setDisplayAllText(model.getAllDigits() + CalculatorContants.EQUAL);
      }
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.subscribe(activity, new CalculatorButtonBusObserver() {
            @Override
            public void onEvent(CalculatorButtonPressed value) {
                onButtonPressed(value.getDigit());
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}