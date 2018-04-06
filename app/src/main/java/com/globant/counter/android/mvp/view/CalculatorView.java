package com.globant.counter.android.mvp.view;

import android.app.Activity;
import android.widget.TextView;

import com.globant.counter.android.R;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.CalculatorButtonBusObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorView extends ActivityView {

    @BindView(R.id.display_text) TextView displayText;
    Activity activity;


    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        this.activity = activity;
    }

    @OnClick(R.id.zero_button)
    public void zeroButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_zero));
    }

    @OnClick(R.id.one_button)
    public void oneButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_one));
    }

    @OnClick(R.id.two_button)
    public void twoButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_two));
    }

    @OnClick(R.id.three_button)
    public void threeButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_three));
    }

    @OnClick(R.id.five_button)
    public void fiveButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_five));
    }

    @OnClick(R.id.six_button)
    public void sixButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_six));
    }

    @OnClick(R.id.seven_button)
    public void sevenButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_seven));
    }

    @OnClick(R.id.eight_button)
    public void eightButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_eight));
    }

    @OnClick(R.id.nine_button)
    public void nineButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_nine));
    }

    @OnClick(R.id.addition_button)
    public void additionButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_addition));
    }

    @OnClick(R.id.subtraction_button)
    public void subtractionButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_substraction));
    }

    @OnClick(R.id.division_button)
    public void divisionButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_divide));
    }

    @OnClick(R.id.multiplication_button)
    public void multiplicationButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_multiplication));
    }

    @OnClick(R.id.equal_button)
    public void equalButtonPressed() {
        buttonPressed(activity.getString(R.string.calculator_equal));
    }

    public void buttonPressed(String digit) {
        RxBus.post(new CalculatorButtonBusObserver.CalculatorButtonPressed(digit));
    }

    public void setDisplayText(String digit) {
        displayText.setText(digit);
    }

}



