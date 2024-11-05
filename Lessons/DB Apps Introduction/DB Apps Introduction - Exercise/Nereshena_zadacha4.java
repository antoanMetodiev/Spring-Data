import java.sql.*;
import java.util.Scanner;

public class Nereshena_zadacha4 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // boksnem1059
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",
                        "root", scanner.nextLine());

        String[] minionData = scanner.nextLine().split("\\s+");
        String searchedTown = minionData[minionData.length - 1]; // търсен град
        String minionName = minionData[1];

        String[] villainData = scanner.nextLine().split("\\s+");
        String searchedVillain = villainData[villainData.length - 1]; // търсен злодей

        checkMinionIfContains(connection, searchedTown);
        checkVillainIfCointains(connection, searchedVillain);
        setBeA_Minion(connection, minionName, searchedTown, searchedVillain);
    }

    private static void setBeA_Minion(Connection connection, String minionName, String searchedTown, String searchedVillain) throws SQLException {
        PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT id FROM minions WHERE name = ?");
        preparedStatement1.setString(1, minionName);
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT id FROM villains WHERE name = ?");
        preparedStatement2.setString(1, searchedVillain);
        ResultSet resultSet2 = preparedStatement2.executeQuery();

        if (resultSet1.next() && resultSet2.next()) {

            int minionId = resultSet1.getInt("id");
            int villainId = resultSet2.getInt("id");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT minions_villains(minion_id, villain_id)\n" +
                            "VALUES (?, ?);"
            );
            preparedStatement.setInt(minionId, villainId);

            try {
                preparedStatement.executeQuery();
                System.out.printf("Successfully added %s to be minion of %s.",
                        minionName, searchedVillain).append(System.lineSeparator());
            } catch (Error error) {
                System.out.println(error);
            }
        }
    }

    private static void checkMinionIfContains(Connection connection, String searchedTown) throws SQLException {


        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT name FROM towns WHERE name = ?");

        preparedStatement.setString(1, searchedTown);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            connection.prepareStatement(
                    "INSERT towns(name)\n" +
                            "VALUES (?);");

            preparedStatement.setString(1, searchedTown);
            try {
                preparedStatement.executeQuery();
                System.out.printf("Town %s was added to the database.", searchedTown)
                        .append(System.lineSeparator());
            } catch (Error error) {
                System.out.println(error);
            }
        }
    }


    private static void checkVillainIfCointains(Connection connection, String searchedVillain) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT name FROM villains\n" +
                        "WHERE name = ?;");
        preparedStatement.setString(1, searchedVillain);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {

            try {
                String evil = "evil";
                PreparedStatement preparedStatement1 = connection.prepareStatement(
                        "INSERT villains(name, evilness_factor)\n" +
                                "VALUES (? , ?);"
                );
                preparedStatement1.setString(1, searchedVillain);
                preparedStatement1.setString(1, evil);

                preparedStatement1.executeQuery();

                System.out.printf("Villain %s was added to the database.", searchedVillain)
                        .append(System.lineSeparator());
            } catch (Error error) {
                System.out.println(error);
            }
        }
    }
}
