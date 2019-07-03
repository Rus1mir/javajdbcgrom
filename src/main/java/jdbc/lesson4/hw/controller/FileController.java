package jdbc.lesson4.hw.controller;

import jdbc.lesson4.hw.model.File;
import jdbc.lesson4.hw.model.Storage;
import jdbc.lesson4.hw.service.FileService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileController {

    private FileService fileService = new FileService();

    //CRUD
    public File save(File file) throws SQLException {

        return fileService.save(file);
    }

    public void delete(long id) throws SQLException {

        fileService.delete(id);
    }

    public File update(File file) throws Exception {

        return fileService.update(file);
    }

    public File findById(long id) throws SQLException {

        return fileService.findById(id);
    }

    //Specific
    public File put(Storage storage, File file) throws Exception {

       return fileService.put(storage, file);
    }

    public List<File> putAll(Storage storage, ArrayList<File> files) throws Exception {

        return fileService.putAll(storage, files);
    }

    public void delete(Storage storage, File file) throws Exception {

        fileService.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        fileService.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        fileService.transferFile(storageFrom, storageTo, id);
    }
}
