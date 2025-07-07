import java.util.*;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }

    public String toString() {
        return id + " - " + name + " - " + department;
    }
}

public class EmployeeCRUD {
    List<Employee> employees = new ArrayList<>();

    public void create(Employee e) {
        employees.add(e);
    }

    public void read() {
        employees.forEach(System.out::println);
    }

    public void update(int id, String name, String dept) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(name);
                e.setDepartment(dept);
            }
        }
    }

    public void delete(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();
        crud.create(new Employee(1, "Alice", "HR"));
        crud.create(new Employee(2, "Bob", "IT"));
        crud.read();
        crud.update(2, "Bobby", "Engineering");
        crud.read();
        crud.delete(1);
        crud.read();
    }
}
