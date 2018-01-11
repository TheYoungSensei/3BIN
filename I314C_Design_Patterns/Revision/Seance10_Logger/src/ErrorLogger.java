public class ErrorLogger implements Logger.Observer {

    @Override
    public void notifyObserver(int sev, String message) {
        if(Logger.ERROR == sev) {
            System.err.println(message);
        }
    }
}