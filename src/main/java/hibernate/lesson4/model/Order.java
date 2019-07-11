package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"ORDER\"")
public class Order {
    private Long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    double moneyPaid;

    public Order(Long id, User userOrdered, Room room, Date dateFrom, Date dateTo, double moneyPaid) {
        this.id = id;
        this.userOrdered = userOrdered;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }

    public Order() {
    }

    @Id
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @Column(name = "ORDER_ID")
    public Long getId() {
        return id;
    }

    public User getUserOrdered() {
        return userOrdered;
    }

    public Room getRoom() {
        return room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserOrdered(User userOrdered) {
        this.userOrdered = userOrdered;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }
}
