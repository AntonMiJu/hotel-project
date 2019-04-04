package model;

import java.util.Date;

public class Filter {
    private int numberOfGuests;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;

    public Filter(int numberOfGuests, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom) {
        this.numberOfGuests = numberOfGuests;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
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
}
