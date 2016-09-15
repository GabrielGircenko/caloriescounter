package com.gircenko.gabriel.calcounter.main;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MainPresenter implements IMainPresenter {

    IMainView view;

    public MainPresenter(IMainView view) {
        this.view = view;
    }
}
