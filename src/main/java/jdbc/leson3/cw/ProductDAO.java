package jdbc.leson3.cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "xthtgf[f1";

    public Product save(Product product) {



        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?,?)")) {

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int resp = preparedStatement.executeUpdate();
            System.out.println("save was finished with result " + resp);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public Product update(Product product) {

        String sql = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(4, product.getId());
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());

            int resp = preparedStatement.executeUpdate();
            System.out.println("update was finished with result " + resp);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProducts() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT")) {

            List<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));

                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public Product delete(long id) {

        String sqlSelect = "SELECT * FROM PRODUCT WHERE ID = ?";
        String sqlDelete = "DELETE FROM PRODUCT WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
             PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete)) {

            selectStatement.setLong(1, id);

            try (ResultSet resultSet = selectStatement.executeQuery()) {

                if (resultSet.next()) {

                    deleteStatement.setLong(1, id);
                    deleteStatement.executeUpdate();

                    return new Product(resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4));
                }
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
