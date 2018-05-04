package com.globant.counter.android.test;


import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.presenter.CalculatorPresenter;
import com.globant.counter.android.mvp.view.CalculatorView;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class CalculatorPresenterTest {

    private CalculatorPresenter presenter;
    @Mock CalculatorModel model;
    @Mock CalculatorView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        model = new CalculatorModel();
        presenter = new CalculatorPresenter(model, view);
    }


    @Test
    public void shouldGetTotalAndDeleteAllButtonWorks() {
        presenter.onButtonPressed("1");
        presenter.onButtonPressed("+");
        presenter.onButtonPressed("2");
        assertEquals("3.0", model.getTotal());

        presenter.onButtonPressed("CE");

        presenter.onButtonPressed("9");
        presenter.onButtonPressed("-");
        presenter.onButtonPressed("1");
        assertEquals("8.0", model.getTotal());

        presenter.onButtonPressed("CE");

        presenter.onButtonPressed("15");
        presenter.onButtonPressed("/");
        presenter.onButtonPressed("2");
        assertEquals("7.5", model.getTotal());

        presenter.onButtonPressed("CE");

        presenter.onButtonPressed("49");
        presenter.onButtonPressed("X");
        presenter.onButtonPressed("2");
        assertEquals("98.0", model.getTotal());

    }

    @Test
    public void deleteLastDigitButtonShouldWork(){
        presenter.onButtonPressed("0");
        presenter.onButtonPressed("+");
        presenter.onButtonPressed("1");
        presenter.onButtonPressed("-");
        presenter.onButtonPressed("2");

        assertEquals(4, model.getLastDigitIndex());

        presenter.onButtonPressed("C");
        presenter.onButtonPressed("C");

        assertEquals(2, model.getLastDigitIndex());
    }

    @Test
    public void allNumbersAndOperatorsShouldWork() {
        presenter.onButtonPressed("0");
        presenter.onButtonPressed("+");
        presenter.onButtonPressed("1");
        presenter.onButtonPressed("-");
        presenter.onButtonPressed("2");
        presenter.onButtonPressed("/");
        presenter.onButtonPressed("3");
        presenter.onButtonPressed("X");
        presenter.onButtonPressed("4");
        presenter.onButtonPressed("+");
        presenter.onButtonPressed("5");
        presenter.onButtonPressed("-");
        presenter.onButtonPressed("6");
        presenter.onButtonPressed("/");
        presenter.onButtonPressed("7");
        presenter.onButtonPressed("X");
        presenter.onButtonPressed("8");
        presenter.onButtonPressed("+");
        presenter.onButtonPressed("9");

        assertEquals(18, model.getLastDigitIndex());
    }

}
