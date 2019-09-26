package com.tilseier.studying.screens.observer;

import java.util.List;

public class Subscriber implements Observer {

    private String name;

    Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(List<String> vacancies) {
        System.out.println("Dear " + name + "\nWe have some vacancy changes:\n" + vacancies +
                "\n========================================================\n");
    }
}
