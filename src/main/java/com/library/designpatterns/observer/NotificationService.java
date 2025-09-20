package com.library.designpatterns.observer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.library.util.LoggerUtil;


/**
 * Minimal notification service implementing Subject. Keyed by ISBN so patrons can subscribe
 * for availability notifications of specific books.
 */
public class NotificationService implements Subject {
    private final Logger logger = LoggerUtil.getLogger(NotificationService.class);
    private final Map<String, List<Observer>> observers = new HashMap<>();


    @Override
    public synchronized void registerObserver(String key, Observer observer) {
        observers.computeIfAbsent(key, k -> new ArrayList<>()).add(observer);
        logger.info(() -> "Observer registered for key=" + key);
    }


    @Override
    public synchronized void removeObserver(String key, Observer observer) {
        List<Observer> list = observers.get(key);
        if (list != null) {
            list.remove(observer);
        }
    }


    @Override
    public synchronized void notifyObservers(String key, String message) {
        List<Observer> list = observers.get(key);
        if (list != null) {
            for (Observer o : new ArrayList<>(list)) {
                try {
                    o.update(message);
                } catch (Exception e) {
                    logger.warning(() -> "Failed to notify observer: " + e.getMessage());
                }
            }
        }
    }
}