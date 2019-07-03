package jdbc.lesson1and2;

import java.sql.*;

public class JDBCFirstStep {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "rjycnbnewbz";

    /*
    Напишите sql запрос для создания таблицы PRODUCT с полями:
    ID - уникальной идендификатор
    NAME - текстовое поле, которое не может быть пустым
    DESCRIPTION - текстовое поле, которое может содержать максимально большой текст
    PRICE - целочисельное поле которое не может быть пустым
    */
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            int resp = statement.executeUpdate("CREATE TABLE PRODUCT (" +
                    "ID NUMBER UNIQUE," +
                    "NAME NVARCHAR2(50) NOT NULL," +
                    "DESCRIPTION CLOB," +
                    "PRICE NUMBER(12) NOT NULL)");

            System.out.println(resp);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
