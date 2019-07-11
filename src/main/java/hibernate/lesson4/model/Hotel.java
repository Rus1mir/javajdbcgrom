package hibernate.lesson4.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Hotel {
   private Long id;
   private String name;
   private String country;
   private String city;
   private String street;
   private List rooms;

    public Hotel(Long id, String name, String country, String city, String street, List rooms) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.rooms = rooms;
    }

    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public List getRooms() {
        return rooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setRooms(List rooms) {
        this.rooms = rooms;
    }
}
