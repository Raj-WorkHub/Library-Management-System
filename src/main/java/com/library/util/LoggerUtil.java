package com.library.util;


import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/** Simple logger utility wrapper. */
public final class LoggerUtil {
    private LoggerUtil() {}


    public static Logger getLogger(Class<?> cls) {
        Logger logger = Logger.getLogger(cls.getName());
        logger.setUseParentHandlers(false);
        if (logger.getHandlers().length == 0) {
            ConsoleHandler handler = new ConsoleHandler();
            handler.setFormatter(new SimpleFormatter());
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
        }
        logger.setLevel(Level.ALL);
        return logger;
    }
}