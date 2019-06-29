package lesson4.hw.controller;

import lesson4.hw.model.Storage;
import lesson4.hw.service.StorageService;

import java.sql.SQLException;

public class StorageController {

    private StorageService storageService = new StorageService();

    public Storage save(Storage storage) throws SQLException {

        return storageService.save(storage);
    }

    public void delete(long id) throws Exception {

        storageService.delete(id);
    }

    public Storage update(Storage storage) throws SQLException {

        return storageService.update(storage);
    }

    public Storage findById(long id) throws SQLException {

        return storageService.findById(id);
    }
}
