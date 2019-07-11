package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "\"USER\"")
public class User {
    private Long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List <Order> orders;

    @Id
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "USER_ID")
    public Long getId() {
        return id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }

    @OneToMany(cascade = ALL, mappedBy="userOrdered")
    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
