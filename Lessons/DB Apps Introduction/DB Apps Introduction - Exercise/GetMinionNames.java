import java.sql.*;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",
                        "root", scanner.nextLine());

        int searchedVillainId = Integer.parseInt(scanner.nextLine());
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT v.name AS 'villain_name', m.name, m.age FROM minions m\n" +
                        "JOIN minions_villains mv ON mv.minion_id = m.id\n" +
                        "JOIN villains v ON v.id = mv.villain_id\n" +
                        "WHERE v.id = ?;"
        );
        preparedStatement.setInt(1, searchedVillainId);
        ResultSet resultSet = preparedStatement.executeQuery();

        int count = 0;
        boolean wasInside = false;
        while (resultSet.next()) {
            if (count == 0) {
                wasInside = true;
                System.out.printf("Villain: %s",
                resultSet.getString("villain_name")).
                        append(System.lineSeparator());
            }

            System.out.printf("%d. %s %d", ++count, resultSet.getString("name"),
                            resultSet.getInt("age"))
                    .append(System.lineSeparator());
        }

        if (!wasInside) System.out.println("No villain with ID 10 exists in the database.");
    }
}
