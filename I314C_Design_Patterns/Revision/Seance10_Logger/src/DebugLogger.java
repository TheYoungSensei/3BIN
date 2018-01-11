import java.io.*;

public class DebugLogger implements Logger.Observer {

    @Override
    public void notifyObserver(int sev, String message) {
        if(Logger.DEBUG <= sev) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("out.log", true), "utf-8"))) {
                writer.write(message+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}