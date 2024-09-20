import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // 1. Connect to DB:

        Properties credentials = new Properties();

        credentials.setProperty("user", "root");
        credentials.setProperty("password", "12345");
        // "12345" не ми е паролата за базата:
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/soft_uni", credentials);

        // 2. Execute Query:

        PreparedStatement preparedStatement = connection.
                prepareStatement("SELECT first_name FROM employees LIMIT 10");


        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            String first_name = resultSet.getString("first_name");

            System.out.println(first_name);
        }

        // 3: Print Result:


    }
}