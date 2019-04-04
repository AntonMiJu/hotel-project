package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Room extends GeneralClass{
    private long id;
    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private int hotelId;
    SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");

    public Room(long id, int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, int hotelId) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotelId = hotelId;
    }

    public Room(int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, int hotelId) {
        this.id = Math.abs(Objects.hash(numberOfGuests,price,breakfastIncluded,petsAllowed,dateAvailableFrom,hotelId));
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotelId = hotelId;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public long getId() {
        return id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public int getHotelId() {
        return hotelId;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", numberOfGuests = " + numberOfGuests +
                ", price = " + price +
                ", breakfastIncluded = " + breakfastIncluded +
                ", petsAllowed = " + petsAllowed +
                ", dateAvailableFrom = " + format.format(dateAvailableFrom) +
                ", hotelId = " + hotelId;
    }
}