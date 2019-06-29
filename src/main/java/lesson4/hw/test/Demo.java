package lesson4.hw.test;

import lesson4.hw.controller.FileController;
import lesson4.hw.controller.StorageController;
import lesson4.hw.model.File;
import lesson4.hw.model.Storage;
import lesson4.hw.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;


public class Demo {

    private static StorageController storageController = new StorageController();
    private static FileController fileController = new FileController();

    public static void main(String[] args) throws Exception {

        //testUtils(); //tested
        //testStorageCRUD(); //tested
        //testFileCRUD(); //tested
        //testIllegalUpdate(); //tested
        //testPutDel(); //tested
        //testPutAll(); //tested
        testTransfer(); //tested
    }

    private static void testUtils() {

        String[] strings = new String[]{"cat", "dog", "duck", "chupacabra"};
        System.out.println(Arrays.toString(strings));

        String result = Utils.arrayToString(strings);
        System.out.println(result);

        System.out.println(Utils.arrayToString(null));

        System.out.println(Arrays.toString(Utils.stringToArray(result)));
        System.out.println(Arrays.toString(Utils.stringToArray(null)));
    }

    private static void testStorageCRUD() throws Exception {

        String[] formats = new String[]{"pdf", "jpg"};
        Storage storage = new Storage(-1L, formats, "USA", 12000L);

        storage = storageController.save(storage);
        Long id = storage.getId();
        System.out.println(storage);

        System.out.println(storageController.findById(id));

        Storage storageX = storageController.findById(id);
        storageX.setStorageMaxSize(230456L);
        System.out.println(storageController.update(storageX));

        System.out.println(storageController.findById(id));

        File file = new File(-1, "Animal", "jpg", 120L, storage);
        System.out.println(fileController.save(file));

        storageController.delete(id);
        System.out.println(storageController.findById(id));

        //storage.setStorageCountry(null);
        //storageController.save(storage);
    }

    private static void testFileCRUD() throws Exception {

        String[] formats = new String[]{"pdf", "jpg"};
        Storage storage = new Storage(-1L, formats, "USA", 1200L);

        storage = storageController.save(storage);

        File file = new File(-1, "Animal", "jpg", 120L, storage);

        System.out.println(fileController.save(file));
        Long id = file.getId();

        File fileX = fileController.findById(id);
        fileX.setName("Wild");
        System.out.println(fileController.update(fileX));

        fileController.delete(id);
        System.out.println(fileController.findById(id));

        //Storage storage1 = new Storage(34536634354524L, formats , "USA", 12000L ); //not exists in base
        //File file1 = new File(-1, "Animal", "jpg", 120L, storage1);
        //System.out.println(fileController.save(file1));

        File file2 = new File(-1, "Animal", "jpg", 120L, null);
        file2 = fileController.save(file2);
        System.out.println(file2);
        System.out.println(fileController.findById(file2.getId()));
    }

    private static void testIllegalUpdate() throws Exception {

        File file = fileController.findById(83);

        //file.setStorage(null);
        file.setStorage(storageController.findById(260));

        fileController.update(file);
    }

    private static void testPutDel() throws Exception {

        String[] formats = new String[]{"pdf", "jpg"};
        Storage storage = new Storage(-1L, formats, "USA", 5000L);

        storage = storageController.save(storage);

        File file = new File(-1, "Animal", "jpg", 3000L, null);
        //File file = new File(-1, "Animal", "rtf", 3000L, null);

        System.out.println(fileController.save(file));
        fileController.put(storage, file);

        //System.out.println(fileController.save(file));
        //fileController.put(storage, file);

        System.out.println(file);
        fileController.delete(storage, file);
        System.out.println(file);
    }

    private static void testPutAll() throws Exception {

        String[] formats = new String[]{"pdf", "jpg"};
        Storage storage = storageController.findById(260);

        ArrayList<File> files = new ArrayList<File>();

        for (int i = 0; i < 5; i++) {

            File file = new File(-1, "file no: " + i, "jpg", 100L, null);

            //if(i == 3) file.setFormat("rtf"); //Wrong format
            //if(i == 2) file.setSize(4900); //Big size

            file = fileController.save(file);
            System.out.println(file);
            files.add(file);
        }

        System.out.println(files.size());

        fileController.putAll(storage, files);
    }

    private static void testTransfer() throws Exception {

        Storage storageFrom = storageController.findById(260);
        Storage storageTo = storageController.findById(261);

        File file = fileController.findById(83);

        //Wrong format
        file.setFormat("jpg");
        file.setSize(4900);
        fileController.update(file);

        //fileController.transferAll(storageFrom, storageTo);
        fileController.transferFile(storageFrom, storageTo, 83);

    }
}
