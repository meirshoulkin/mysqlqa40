package org.example;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class MySQLExample {
    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://db4free.net:3306/telran40";
        String user = "telran40";
        String password = "telran40";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE employees;");
            statement.execute("SHOW DATABASES;");
            // SQL statement to create a table
            String createTableSQL = "CREATE  TABLE employees ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "first_name VARCHAR(50),"
                    + "last_name VARCHAR(50),"
                    + "email VARCHAR(100)"
                    + ")";

            // Execute the SQL statement
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'employees' created successfully.");
// SQL statement to insert data into the employees table
            String insertDataSQL = "INSERT INTO employees (first_name, last_name, email) VALUES (?, ?, ?)";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL);

            // Sample data to be inserted
            String firstName = "John";
            String lastName = "Doe";
            String email = "john.doe@example.com";

            // Set values for the parameters in the SQL statement
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);

            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " row(s) inserted successfully.");

            // Close resources
            preparedStatement.close();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees ");

            // Process the result set
            while (resultSet.next()) {
                // Access data from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("last_name");

                // Do something with the data (print it in this example)
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
