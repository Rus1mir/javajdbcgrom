package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "HOTELS")
public class Hotel  implements Identifiable {
   private Long id = -1L;
   private String name;
   private String country;
   private String city;
   private String street;
   private List<Room> rooms;

    public Hotel(String name, String country, String city, String street) {

        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Hotel() {
    }

    @Id
    @SequenceGenerator(name = "HOTEL_SEQ", sequenceName = "HOTEL_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = ALL)
    public List<Room> getRooms() {
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

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {

        if (rooms == null)
            rooms = new ArrayList<Room>();

        room.setHotel(this);
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", contain rooms=" + rooms.size() +
                '}';
    }
}

























































