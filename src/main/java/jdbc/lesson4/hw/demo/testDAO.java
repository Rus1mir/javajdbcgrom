package jdbc.lesson4.hw.demo;

import jdbc.lesson4.hw.dao.FileDAO;
import jdbc.lesson4.hw.dao.StorageDAO;
import jdbc.lesson4.hw.model.File;
import jdbc.lesson4.hw.model.Storage;

import java.sql.SQLException;
import java.util.ArrayList;

public class testDAO {

    private static StorageDAO storageDAO = new StorageDAO();
    private static FileDAO fileDAO = new FileDAO();

    public static void main(String[] args) throws SQLException{

        //testStorageDAO(); //tested
        testFileDAO(); //tested
        //testGroupStorage();

    }

    static void testStorageDAO () throws SQLException {

        String[] formats = new String[] {"pdf", "jpg"};
        Storage storage = new Storage(-1L, formats , "USA", 12000L );

        storage = storageDAO.save(storage);
        Long id = storage.getId();
        System.out.println(storage);

        System.out.println(storageDAO.getById(id));

        Storage storageX = storageDAO.getById(id);
        storageX.setStorageMaxSize(230456L);
        System.out.println(storageDAO.update(storageX));

        System.out.println(storageDAO.getById(id));

        storageDAO.delete(id);
        System.out.println(storageDAO.getById(id));
    }

    static void testFileDAO () throws SQLException {

        String[] formats = new String[] {"pdf", "jpg"};
        Storage storage = new Storage(-1L, formats , "USA", 12000L );

        //Storage storage1 = new Storage(34536634354524L, formats , "USA", 12000L ); //not exists in base
        storage = storageDAO.save(storage);

        File file = new File(-1, "Animal", "jpg", 120L, storage);

        System.out.println(fileDAO.save(file));
        Long id = file.getId();

        File fileX = fileDAO.getById(id);
        fileX.setName("Wild");
        System.out.println(fileDAO.update(fileX));

        fileDAO.delete(id);
        System.out.println(fileDAO.getById(id));

    }

    static void testGroupStorage () throws SQLException {

        ArrayList<Storage> storages = new ArrayList<>();
        String[] f = {"aaa", "bbb"};

        storages.add(new Storage(18L, f, "UA", 123456L));
        storages.add(new Storage(19L, f, "Ger", 434343L));

        storageDAO.update(storages);
    }
}
