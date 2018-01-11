import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static int INFO = 0;
    public static int DEBUG = 1;
    public static int ERROR = 2;

    private List<Observer> observers = new ArrayList<>();

    private void attachObserver(Observer observer) {
        observers.add(observer);
    }

    private void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    private void logMessage(int sev, String message) {
        for(Observer obs : observers) {
            obs.notifyObserver(sev, message);
        }
        System.out.println(message);
    }

    public interface Observer {
        public void notifyObserver(int sev, String message);
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.attachObserver(new DebugLogger());
        logger.attachObserver(new ErrorLogger());
        logger.logMessage(Logger.INFO, "This is an information.");
        logger.logMessage(Logger.DEBUG, "This is a debug level information.");
        logger.logMessage(Logger.ERROR, "This is an error information.");

    }
}
