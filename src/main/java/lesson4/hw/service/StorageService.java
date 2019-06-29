package lesson4.hw.service;

import lesson4.hw.DAO.FileDAO;
import lesson4.hw.DAO.StorageDAO;
import lesson4.hw.Exception.BadRequestException;
import lesson4.hw.model.Storage;

import java.sql.SQLException;

public class StorageService {

    private StorageDAO storageDAO = new StorageDAO();
    private FileDAO fileDAO = new FileDAO();

    public Storage save(Storage storage) throws SQLException {

       return storageDAO.save(storage);
    }

    public void delete(long id) throws Exception {

        //validateDelete(id);

        storageDAO.delete(id);
    }

    public Storage update(Storage storage) throws SQLException {

       return storageDAO.update(storage);
    }

    public Storage findById(long id) throws SQLException {

        return storageDAO.getById(id);
    }

    private void validateDelete(long id) throws Exception {

        if (fileDAO.getFilesByStorageId(id).size() > 0)
            throw new BadRequestException("Storage id: " + id + " still contains referenced files, please delete " +
                    "all files or transfer to another storage before delete it");
    }
}
