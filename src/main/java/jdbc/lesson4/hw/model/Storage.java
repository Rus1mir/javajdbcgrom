package jdbc.lesson4.hw.model;

import java.util.Arrays;

public class Storage extends Entity {

    private String[] formatsSupported;
    private String storageCountry;
    private Long storageMaxSize;

    public Storage(Long id, String[] formatsSupported, String storageCountry, Long storageMaxSize) {
        super(id);
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public Long getStorageMaxSize() {
        return storageMaxSize;
    }

    public void setFormatsSupported(String[] formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public void setStorageMaxSize(Long storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id= " + super.getId() +
                " formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize=" + storageMaxSize +
                '}';
    }
}
