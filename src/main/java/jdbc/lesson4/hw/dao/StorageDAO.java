package jdbc.lesson4.hw.dao;

import jdbc.lesson4.hw.model.Storage;
import jdbc.lesson4.hw.utils.Utils;

import java.sql.*;

public class StorageDAO extends GeneralDAO<Storage> {

    private final String SAVE = "INSERT INTO STORAGE VALUES (NULL, ?, ?, ?)";
    private final String DELETE = "DELETE FROM STORAGE WHERE STORAGE_ID = ?";
    private final String UPDATE = "UPDATE STORAGE SET FORMAT_SUPPORTED = ?, STORAGE_COUNTRY = ?, MAX_SIZE = ? WHERE STORAGE_ID = ?";
    private final String GET_BY_ID = "SELECT * FROM STORAGE WHERE STORAGE_ID = ?";
    private final String GET_SEC_VAL = "SELECT STORAGE_SEQ.CURRVAL FROM DUAL";

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
    protected PreparedStatement prepareForSave(PreparedStatement statement, Storage entity) throws SQLException {

        statement.setString(1, Utils.arrayToString(entity.getFormatsSupported()));
        statement.setString(2, entity.getStorageCountry());
        statement.setLong(3, entity.getStorageMaxSize());

        return statement;
    }

    @Override
    protected PreparedStatement prepareForDelete(PreparedStatement statement, long id) throws SQLException {

        statement.setLong(1, id);

        return statement;
    }

    @Override
    protected PreparedStatement prepareForUpdate(PreparedStatement statement, Storage entity) throws SQLException {

        statement.setString(1, Utils.arrayToString(entity.getFormatsSupported()));
        statement.setString(2, entity.getStorageCountry());
        statement.setLong(3, entity.getStorageMaxSize());
        statement.setLong(4, entity.getId());

        return statement;
    }

    @Override
    protected PreparedStatement prepareForFindById(PreparedStatement statement, long id) throws SQLException {

        statement.setLong(1, id);

        return statement;
    }

    @Override
    protected Storage getInstance(ResultSet set) throws SQLException {

        return new Storage(set.getLong(1),
                Utils.stringToArray(set.getString(2)),
                set.getString(3),
                set.getLong(4));
    }
}
