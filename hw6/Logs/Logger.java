package hw6.Logs;

import hw6.Interfaces.Observer;

import java.util.concurrent.atomic.AtomicInteger;

public class Logger implements Observer {

    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final int loggerID;

    public Logger() {
        loggerID = idCounter.getAndIncrement();
    }

    @Override
    public void update(String message) {
        System.out.println("loggerID: " + loggerID + ", Log: " + message);
    }

}
