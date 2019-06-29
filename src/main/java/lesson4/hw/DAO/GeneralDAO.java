package lesson4.hw.DAO;

import lesson4.hw.model.Entity;

import java.sql.*;
import java.util.List;


public abstract class GeneralDAO<T extends Entity> {

    private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cctyscdfcahc.us-east-2.rds.amazonaws.com:1521:ORCL";

    private final String USER = "main";
    private final String PASS = "pass";

    //Public CRUD
    public T save(T entity) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement statement = prepareForSave(connection.prepareStatement(getSaveSQL()), entity);
             Statement retIdSt = connection.createStatement()) {

            statement.executeUpdate();
            ResultSet rs = retIdSt.executeQuery(getSequenceCurrentSQL());
            rs.next();
            entity.setId(rs.getLong(1));

            return entity;

        } catch (SQLException e) {
            throw new SQLException("Saving the entity with id: " + entity.getId() + " was filed", e);
        }
    }

    public void delete(long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = prepareForDelete(connection.prepareStatement(getDeleteSQL()), id)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Deleting the entity with id: " + id + " was filed", e);
        }
    }

    public T update(T entity) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = prepareForUpdate(connection.prepareStatement(getUpdateSQL()), entity)) {

            statement.executeUpdate();
            return entity;

        } catch (SQLException e) {
            throw new SQLException("Updating the entity with id: " + entity.getId() + " was filed", e);
        }
    }

    public T getById(long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = prepareForFindById(connection.prepareStatement(getFindByIdSQL()), id)) {

            ResultSet set = statement.executeQuery();

            if (set.next())
                return getInstance(set);

        } catch (SQLException e) {
            throw new SQLException("Getting the entity with id: " + id + " was filed", e);
        }
        return null;
    }

    public List<T> update(List<T> entities) throws SQLException {

        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);
            updateList(connection, entities);
            return entities;

        } catch (SQLException e) {
            throw e;
        }
    }

    private void updateList(Connection connection, List<T> entities) throws SQLException {

        long currId = 0;
        int result = 0;

        try (PreparedStatement statement = connection.prepareStatement(getUpdateSQL())) {

            for (T en : entities) {
                currId = en.getId();
                result += prepareForUpdate(statement, en).executeUpdate();
            }
            connection.commit();

            System.out.println("Updated rows: " + result);

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Update filed in updating entity id: " + currId, e);
        }
    }

    //Conn maker
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    //Abstract
    protected abstract String getSequenceCurrentSQL();

    protected abstract String getSaveSQL();

    protected abstract String getDeleteSQL();

    protected abstract String getUpdateSQL();

    protected abstract String getFindByIdSQL();

    protected abstract PreparedStatement prepareForSave(PreparedStatement statement, T entity) throws SQLException;

    protected abstract PreparedStatement prepareForDelete(PreparedStatement statement, long id) throws SQLException;

    protected abstract PreparedStatement prepareForUpdate(PreparedStatement statement, T entity) throws SQLException;

    protected abstract PreparedStatement prepareForFindById(PreparedStatement statement, long id) throws SQLException;

    protected abstract T getInstance(ResultSet set) throws SQLException;
}
