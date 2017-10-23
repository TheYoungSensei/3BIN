import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ContextListerMots {
    private Strategy strategy;

    private String file;
    ContextListerMots(Strategy strategy, String file) {
        this.strategy = strategy;
        this.file = file;
    }

    void execute() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(this.file));
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (strategy.isPrintable(buffer)) {
                System.out.println(buffer);
            }
        }
    }
}
