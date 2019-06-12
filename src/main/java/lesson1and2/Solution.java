package lesson1and2;

import java.sql.*;

public class Solution {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "pass";

    public static void main(String[] args) {
        //saveProduct();
        //deleteProducts();
        //deleteProductsByPrice();
        //getAllProducts();
        //getProductsByPrice();
        //getProductsByDescription();
        increasePrice();
        changeDescription();
    }

    private static void saveProduct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            int resp = statement.executeUpdate("INSERT INTO PRODUCT VALUES(999, 'toy', 'for children', 60)");

            System.out.println(resp);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void deleteProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            int resp = statement.executeUpdate("DELETE FROM PRODUCT WHERE PRODUCT.NAME NOT LIKE 'toy'");

            System.out.println(resp);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void deleteProductsByPrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            int resp = statement.executeUpdate("DELETE FROM PRODUCT WHERE PRODUCT.PRICE < 100");

            System.out.println(resp);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getAllProducts() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            try (ResultSet set = statement.executeQuery("SELECT * FROM PRODUCT")) {
                int columnCount = set.getMetaData().getColumnCount();
                while (set.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(set.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByPrice() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            try (ResultSet set = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRODUCT.PRICE <= 100")) {
                int columnCount = set.getMetaData().getColumnCount();
                while (set.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(set.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByDescription() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            try (ResultSet set = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(PRODUCT.DESCRIPTION) > 50")) {
                int columnCount = set.getMetaData().getColumnCount();
                while (set.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(set.getString(i) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void increasePrice() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            int resp = statement.executeUpdate("UPDATE PRODUCT SET PRICE = PRICE + 100 WHERE PRICE < 970");

            System.out.println(resp);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void changeDescription() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            int resp = statement.executeUpdate("UPDATE PRODUCT " +
                    "SET DESCRIPTION = REGEXP_REPLACE (DESCRIPTION, '([A-Z]{1}[^\\!\\.]*?[\\.\\!]{1}\\s*)$') " +
                    "WHERE LENGTH(DESCRIPTION) > 100");

            System.out.println(resp);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
