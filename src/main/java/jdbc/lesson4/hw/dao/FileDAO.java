package jdbc.lesson4.hw.dao;

import jdbc.lesson4.hw.model.File;
import jdbc.lesson4.hw.model.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO extends GeneralDAO<File> {

    private StorageDAO storageDAO = new StorageDAO();

    private final String SAVE = "INSERT INTO \"FILE\" VALUES(NULL, ?, ?, ?, ?)";
    private final String DELETE = "DELETE FROM \"FILE\" WHERE FILE_ID = ?";
    private final String UPDATE = "UPDATE \"FILE\" SET NAME = ?, FORMAT = ?, FILE_SIZE = ?, STORAGE_ID = ? WHERE FILE_ID = ?";
    private final String GET_BY_ID = "SELECT * FROM \"FILE\" WHERE FILE_ID = ?";
    private final String GET_SEC_VAL = "SELECT FILE_SEQ.CURRVAL FROM DUAL";
    private final String GET_FILES_BY_STORAGE = "SELECT * FROM \"FILE\" WHERE STORAGE_ID = ?";

    public List<File> getFilesByStorageId(long storageId) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_FILES_BY_STORAGE)) {

            statement.setLong(1, storageId);
            ResultSet set = statement.executeQuery();
            ArrayList<File> files = new ArrayList<>();

            while (set.next()) {
                files.add(getInstance(set));
            }

            return files;

        } catch (SQLException e) {
            throw new SQLException("Select files by storage id: " + storageId + " was filed", e);
        }
    }

    @Override
    protected String getSequenceCurrentSQL() {
        return GET_SEC_VAL;
    }

    @Override
    protected String getSaveSQL() {
        return SAVE;
    }

    @Override
    protected String getDeleteSQL() {
        return DELETE;
    }

    @Override
    protected String getUpdateSQL() {
        return UPDATE;
    }

    @Override
    protected String getFindByIdSQL() {
        return GET_BY_ID;
    }

    @Override
    protected PreparedStatement prepareForSave(PreparedStatement statement, File entity) throws SQLException {

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getFormat());
        statement.setLong(3, entity.getSize());
        if (entity.getStorage() != null) {
            statement.setLong(4, entity.getStorage().getId());
        } else {
            statement.setNull(4, Types.NUMERIC);
        }

        return statement;
    }

    @Override
    protected PreparedStatement prepareForDelete(PreparedStatement statement, long id) throws SQLException {

        statement.setLong(1, id);

        return statement;
    }

    @Override
    protected PreparedStatement prepareForUpdate(PreparedStatement statement, File entity) throws SQLException {

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getFormat());
        statement.setLong(3, entity.getSize());
        if (entity.getStorage() != null) {
            statement.setLong(4, entity.getStorage().getId());
        } else {
            statement.setNull(4, Types.NUMERIC);
        }
        statement.setLong(5, entity.getId());

        return statement;
    }

    @Override
    protected PreparedStatement prepareForFindById(PreparedStatement statement, long id) throws SQLException {

        statement.setLong(1, id);

        return statement;
    }

    @Override
    protected File getInstance(ResultSet set) throws SQLException {

        Storage storage = set.getObject(5) != null ? storageDAO.getById(set.getLong(5)) : null;

        return new File(set.getLong(1),
                set.getString(2),
                set.getString(3),
                set.getLong(4),
                storage);
    }
}
