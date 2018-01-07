import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String dpt;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name, String dpt, int sal) {
        this.name = name;
        this.dpt = dpt;
        this.salary = sal;
        subordinates = new ArrayList<>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return ("Employee :[ Name : " + name + ", dept : " + dpt + ", salary :" + salary+" ]");
    }
}
