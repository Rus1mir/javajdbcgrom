package jdbc.lesson4.cw;

import jdbc.leson3.cw.Product;
import java.sql.*;
import java.util.List;

public class TransactionDemo {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "pass";

    public void save(List<Product> products) throws SQLException {

        try (Connection connection = getConnection()) {

            saveList(products, connection);

        } catch (SQLException e) {
            throw new SQLException("The transaction was filed", e);
        }
    }

    private void saveList(List<Product> products, Connection connection) throws SQLException {

        long currentId = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?,?)")) {

            connection.setAutoCommit(false);

            for (Product product : products) {

                currentId = product.getId();
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int resp = preparedStatement.executeUpdate();
                System.out.println("save was finished with result " + resp);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Product id: " + currentId + " saving failed", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
