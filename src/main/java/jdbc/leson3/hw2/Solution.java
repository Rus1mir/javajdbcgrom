package jdbc.leson3.hw2;

import java.sql.*;

public class Solution {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "pass";

    //Result 159967 ms
    public long testSavePerformance() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEST_SPEED VALUES(?,?,?)")) {

            long index = 0, stop, start = System.currentTimeMillis();

            while (index < 1000) {

                preparedStatement.setLong(1, index);
                preparedStatement.setString(2, "demo");
                preparedStatement.setLong(3, index);

                preparedStatement.executeUpdate();
                index++;
            }

            stop = System.currentTimeMillis();
            return stop - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return -1;
    }

    //Result 160144 ms
    public long testDeleteByIdPerformance() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TEST_SPEED " +
                     "WHERE ID = ?")) {

            long index = 0, stop, start = System.currentTimeMillis();

            while (index < 1000) {

                preparedStatement.setLong(1, index);

                preparedStatement.executeUpdate();
                index++;
            }

            stop = System.currentTimeMillis();
            return stop - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return -1;
    }

    //Result 202 ms
    public long testDeletePerformance() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            long stop, start = System.currentTimeMillis();

            statement.executeQuery("DELETE FROM TEST_SPEED WHERE ID BETWEEN 0 AND 1000");

            stop = System.currentTimeMillis();
            return stop - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return -1;
    }

    //Result 156310 ms
    public long testSelectByIdPerformance() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TEST_SPEED " +
                     "WHERE ID = ?")) {

            long index = 0, stop, start = System.currentTimeMillis();

            while (index < 1000) {

                preparedStatement.setLong(1, index);

                preparedStatement.executeQuery();
                index++;
            }

            stop = System.currentTimeMillis();
            return stop - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return -1;
    }

    //Result 163 ms
    public long testSelectPerformance() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            long stop, start = System.currentTimeMillis();

            statement.executeQuery("SELECT * FROM TEST_SPEED WHERE ID BETWEEN 0 AND 1000");

            stop = System.currentTimeMillis();
            return stop - start;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return -1;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
    /*testSavePerformance() - который будет успешно добавлять 1000 записей в таблицу TEST_SPEED c произвольными значениями
    testDeleteByIdPerformance() - будет удалять 1000 добавленных перед этим записей, отдельными запросами по полю ID
    testDeletePerformance - будет удалять 1000, одним sql запросом()
    testSelectByIdPerformance() - будет выбирать по очереди 1000 добавленных перед этим записей, отдельными запросами по полю ID
    testSelectPerformance() - будет выбирать 1000 записей, одним sql запросом
     */