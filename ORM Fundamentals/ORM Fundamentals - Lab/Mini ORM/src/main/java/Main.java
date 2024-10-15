import orm.MyConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        MyConnector.createConnection("root", scanner.nextLine(), "minions_db");
        Connection connection = MyConnector.getConnection();

        ResultSet resultSet =
                connection.prepareStatement("SELECT name FROM minions LIMIT 10;").executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s", resultSet.getString("name")).append(System.lineSeparator());
        }
    }
}
