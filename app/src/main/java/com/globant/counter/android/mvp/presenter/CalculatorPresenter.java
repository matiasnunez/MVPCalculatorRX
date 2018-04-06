package com.globant.counter.android.mvp.presenter;

import android.app.Activity;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.CalculatorButtonBusObserver;

public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public  CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onButtonPressed(String digit) {
        model.setDigit(digit);
        view.setDisplayText(model.getDigit());
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