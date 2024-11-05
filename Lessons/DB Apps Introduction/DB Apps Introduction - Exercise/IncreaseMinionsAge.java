import java.sql.*;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",
                        "root", scanner.nextLine());

        StringBuilder sb = new StringBuilder(
                "UPDATE minions\n" +
                        "SET\n" +
                        "age = age + 1,\n" +
                        "name = LOWER(SUBSTRING(name, 1))\n" +
                        "WHERE id IN (");

        String[] input = scanner.nextLine().split("\\s+");
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
            if (i < input.length - 1) sb.append(", ");
            else if (i == input.length - 1) sb.append(");");
        }

        connection.prepareStatement(sb.toString()).executeUpdate();

        ResultSet resultSet = connection.prepareStatement("SELECT name, age FROM minions;")
                .executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d",
                            resultSet.getString("name"), resultSet.getInt("age"))
                    .append(System.lineSeparator());
        }
    }
}
