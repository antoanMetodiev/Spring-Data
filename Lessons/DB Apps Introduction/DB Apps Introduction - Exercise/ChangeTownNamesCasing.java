import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", scanner.nextLine());

        String country = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE towns\n" +
                        "SET name = UPPER(name)\n" +
                        "WHERE country = ?; ");
        preparedStatement.setString(1, country);

        int updatesCount = preparedStatement.executeUpdate();

        if (updatesCount > 0) {
            System.out.printf("%d town names were affected.", updatesCount).append(System.lineSeparator());

            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "SELECT name FROM towns\n" +
                            "WHERE country = ?;");
            preparedStatement1.setString(1, country);

            ResultSet resultSet = preparedStatement1.executeQuery();
            List<String> townsAffected = new ArrayList<>();
            while (resultSet.next()) {
                townsAffected.add(resultSet.getString("name"));
            }

            System.out.println(townsAffected);

        } else {
            System.out.println("No town names were affected.");
        }
    }
}
