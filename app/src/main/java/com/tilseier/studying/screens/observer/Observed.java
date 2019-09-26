package com.tilseier.studying.screens.observer;

public interface Observed {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}
