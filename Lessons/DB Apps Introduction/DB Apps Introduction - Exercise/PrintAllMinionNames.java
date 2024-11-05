import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root",
                        scanner.nextLine());

        ResultSet resultSet = connection.prepareStatement("SELECT name FROM minions;")
                .executeQuery();

        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }

        int firstCounter = 0;
        int lastCounter = 0;
        for (int i = 0; i < names.size() / 2; i++) {
            System.out.println(names.get(firstCounter++));
            System.out.println(names.get(names.size() - 1 - lastCounter++));
        }
    }
}
