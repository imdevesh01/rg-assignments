import java.sql.*;

public class EmployeeJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "password";

    public void create(int id, String name, String dept) throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement("INSERT INTO employees VALUES (?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, dept);
        ps.executeUpdate();
        con.close();
    }

    public void read() throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employees");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
        }
        con.close();
    }

    public void update(int id, String name) throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement("UPDATE employees SET name=? WHERE id=?");
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.executeUpdate();
        con.close();
    }

    public void delete(int id) throws Exception {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = con.prepareStatement("DELETE FROM employees WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    public static void main(String[] args) throws Exception {
        EmployeeJDBC db = new EmployeeJDBC();
        db.create(1, "Alice", "HR");
        db.create(2, "Bob", "IT");
        db.read();
        db.update(2, "Bobby");
        db.read();
        db.delete(1);
        db.read();
    }
}
