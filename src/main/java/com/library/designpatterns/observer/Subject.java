package com.library.designpatterns.observer;


public interface Subject {
    void registerObserver(String key, Observer observer);
    void removeObserver(String key, Observer observer);
    void notifyObservers(String key, String message);
}