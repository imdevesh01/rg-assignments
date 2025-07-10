import java.sql.*;

public class EmployeeJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "root";

    public void create(int id, String name, String dept) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement("INSERT INTO employees VALUES (?, ?, ?)")) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.executeUpdate();
            System.out.println("Inserted: " + id + ", " + name + ", " + dept);

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Employee with ID " + id + " already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employees")) {

            System.out.println("Employee List:");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String name) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement("UPDATE employees SET name=? WHERE id=?")) {

            ps.setString(1, name);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated name of ID " + id + " to " + name);
            } else {
                System.out.println("No employee found with ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement("DELETE FROM employees WHERE id=?")) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Deleted employee with ID " + id);
            } else {
                System.out.println("No employee found with ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for demo
    public static void main(String[] args) {
        EmployeeJDBC db = new EmployeeJDBC();

        db.create(1, "Alice", "HR");
        db.create(2, "Bob", "IT");
        db.create(1, "Charlie", "Sales");
        db.update(2, "Bobby");
        db.delete(1);
        db.read();
    }
}
