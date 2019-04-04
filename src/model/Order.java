package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order extends GeneralClass{
    private long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;
    private SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");

    public Order(long id, User user, Room room, Date dateFrom, Date dateTo, double moneyPaid) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }
    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    @Override
    public String toString() {
        return  "id = " + id +
                ", userId = " + user.getId() +
                ", roomId = " + room.getId() +
                ", dateFrom = " + format.format(dateFrom) +
                ", dateTo = " + format.format(dateTo) +
                ", moneyPaid = " + moneyPaid;
    }
}
