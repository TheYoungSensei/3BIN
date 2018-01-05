public class Counter implements Strategy {

    private Strategy component;
    private int counter;

    Counter(Strategy component) {
        this.component  = component;
    }

    @Override
    public boolean isPrintable(String line) {
        if(component.isPrintable(line)) {
            counter++;
            return true;
        }
        return false;
    }

    int getCounter() {
        return counter;
    }
}
