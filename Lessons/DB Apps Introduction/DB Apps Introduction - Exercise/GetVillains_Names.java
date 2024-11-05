import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillains_Names {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();

        String user = scanner.nextLine();
        String password = scanner.nextLine();
        if (user.isEmpty()) user = "root";

        properties.setProperty("user", user);
        properties.setProperty("password", password);
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        ResultSet resultSet = connection.createStatement().executeQuery(
                "SELECT v.name, COUNT(v.name) AS 'minions' FROM minions m\n" +
                        "JOIN minions_villains mv ON m.id = mv.minion_id\n" +
                        "JOIN villains v ON v.id = mv.villain_id\n" +
                        "GROUP BY v.name\n" +
                        "HAVING minions > 15\n" +
                        "ORDER BY minions DESC;"
        );

        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString("name"),
                    resultSet.getInt("minions")).append(System.lineSeparator());
        }
    }
}
