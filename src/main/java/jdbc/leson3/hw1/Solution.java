package jdbc.leson3.hw1;

import jdbc.leson3.cw.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "pass";

    public List<Product> findProductsByPrice(int price, int delta) {

        String sql = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, (price - delta));
            preparedStatement.setInt(2, (price + delta));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<Product> products = new ArrayList<>();

                while (resultSet.next()) {
                    products.add(mapping(resultSet));
                }
                return products;
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findProductsByName(String word) throws Exception {

        validate(word);
        String sql = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, ("%" + word + "%"));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<Product> products = new ArrayList<>();

                while (resultSet.next()) {
                    products.add(mapping(resultSet));
                }
                return products;
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;

    }

    public List<Product> findProductsWithEmptyDescription() {

        String sql = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL OR LENGTH(TRIM(DESCRIPTION)) = 0";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ArrayList<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                products.add(mapping(resultSet));
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private Product mapping(ResultSet set) throws SQLException {
        return new Product(set.getLong(1),
                set.getString(2),
                set.getString(3),
                set.getInt(4));
    }

    private void validate(String request) throws Exception {

        //больше одного слова в стринге, длина меньше 3, содержит спецсимволы
        if (request.contains(" "))
            throw new Exception("Request " + request + " is wrong cause it contains space symbols");

        if (request.length() < 3)
            throw new Exception("Request " + request + " is too short, minimum 3 symbols required");

        if (!Pattern.matches("[A-Za-z]+", request))
            throw new Exception("Request " + request + " haz illegal symbols");

    }
}
