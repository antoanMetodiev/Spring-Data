import java.sql.*;
import java.util.Properties;

public class Diablo {

    public static void main(String[] args) throws SQLException {

        Properties credentials = new Properties();
        credentials.setProperty("user", "root");
        credentials.setProperty("password", "boksnem1059");


        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", credentials);


        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT name FROM games WHERE id < 10");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
    }
}
