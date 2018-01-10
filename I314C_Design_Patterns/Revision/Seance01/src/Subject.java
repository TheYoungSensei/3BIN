import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void readContent(BufferedReader lecteurAvecBuffer) throws IOException {
        String ligne;
        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
            notifyObs(ligne);
        }
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObs(String ligne) {
        for (Observer observer : observers) {
            observer.update(ligne);
        }
    }

    public interface Observer {
        public void update(String ligne);
    }

}
