package jdbc.lesson4.hw.service;

import jdbc.lesson4.hw.dao.FileDAO;
import jdbc.lesson4.hw.exception.BadRequestException;
import jdbc.lesson4.hw.model.File;
import jdbc.lesson4.hw.model.Storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {

    private FileDAO fileDAO = new FileDAO();

    //CRUD
    public File save(File file) throws SQLException {
        return fileDAO.save(file);
    }

    public void delete(long id) throws SQLException {
        fileDAO.delete(id);
    }

    public File update(File file) throws Exception {

        File old = fileDAO.getById(file.getId());

        if (old != null && old.getStorage() != file.getStorage())
            throw new BadRequestException("Change storage for file id: " + file.getId() +
                    " using this method (update) is illegal. Please use 'Put' or 'Transfer' methods");

        return fileDAO.update(file);
    }

    public File findById(long id) throws SQLException {
        return fileDAO.getById(id);
    }

    //Specific
    public File put(Storage storage, File file) throws Exception {

        List<File> files = new ArrayList<>();

        files.add(file);

        try {

            validatePut(storage, files);

            file.setStorage(storage);
            fileDAO.update(file);
            return file;

        } catch (Exception e) {
            throw new Exception("Put operation for file id: " + file.getId() + " into storage id: " +
                    storage.getId() + " was filed", e);
        }
    }

    public List<File> putAll(Storage storage, ArrayList<File> files) throws Exception {

        ArrayList<File> filesToUpdate = new ArrayList<File>();
        try {
            validatePut(storage, files);

            for (File file : files) {

                if (file.getStorage() == null) {
                    file.setStorage(storage);
                    filesToUpdate.add(file);
                }
            }

            fileDAO.update(filesToUpdate);

            return files;

        } catch (Exception e) {
            throw new Exception("Put operation for group of files into storage id: " + storage.getId() + " was filed", e);
        }
    }

    public void delete(Storage storage, File file) throws Exception {

        if (file.getStorage().getId() != storage.getId())
            return;

        try {

            file.setStorage(null);
            fileDAO.update(file);

        } catch (Exception e) {
            throw new Exception("Delete file id: " + file.getId() + " from storage id: " + storage.getId() +
                    " was filed", e);
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        try {

            List<File> filesFrom = fileDAO.getFilesByStorageId(storageFrom.getId());

            for (File file : filesFrom) {
                file.setStorage(storageTo);
            }

            validatePut(storageTo, filesFrom);

            fileDAO.update(filesFrom);

        } catch (Exception e) {
            throw new Exception("Transfer all files from storage id: " + storageFrom.getId() + " to storage id: " +
                    storageTo.getId() + " was filed", e);
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        try {

            File file = fileDAO.getById(id);

            if (file.getStorage().getId() != storageFrom.getId())
                throw new BadRequestException("File id: " + id + " was not found in storage id: " + storageFrom.getId());

            file.setStorage(null);

            put(storageTo, file);
        } catch (Exception e) {
            throw new Exception("Transfer file id: " + id + " from storage id: " + storageFrom.getId()
                    + " to storage id: " + storageTo.getId() + " was filed", e);
        }
    }

    private void validatePut(Storage storage, List<File> files) throws Exception {

        long spaceNeeded = 0;

        for (File file : files) {

            if (file.getStorage() != null && file.getStorage().getId() != storage.getId())
                throw new BadRequestException("File id: " + file.getId() + " is already putted in other storage");

            if (!Arrays.asList(storage.getFormatsSupported()).contains(file.getFormat()))
                throw new BadRequestException("File id: " + file.getId() + " type no support for storage id:" +
                        storage.getId());

            spaceNeeded += file.getSize();
        }

        List<File> storageFiles = fileDAO.getFilesByStorageId(storage.getId());
        long spaceOccupied = 0;

        for (File f : storageFiles) {
            spaceOccupied += f.getSize();
        }

        if (storage.getStorageMaxSize() - spaceOccupied < spaceNeeded)
            throw new BadRequestException("No enough free space for put files");
    }
}
