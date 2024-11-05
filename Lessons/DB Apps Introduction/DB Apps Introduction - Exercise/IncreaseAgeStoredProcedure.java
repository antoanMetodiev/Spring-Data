import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",
                "root", scanner.nextLine());

        CallableStatement callableStatement =
                connection.prepareCall("CALL usp_get_older(?)");

        callableStatement.setInt(1, Integer.parseInt(scanner.nextLine()));

        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %d",
                    resultSet.getString("name"),
                    resultSet.getInt("age")).append(System.lineSeparator());
        }
    }
}
