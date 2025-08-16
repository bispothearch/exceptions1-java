package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {}

    public Reservation(Integer roomNumber, Date checkIn, Date checkout) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckout() {
        return checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date checkin, Date checkout){
        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)) {
           return "Reservation dates for update must be future dates";
        }
        if (!checkout.after(checkin)) {
            return "Check-out date must be after check-in date";
        }
        this.checkIn = checkin;
        this.checkOut = checkout;
        return null;
    }

    @Override
    public String toString(){
        return "Room "
                + roomNumber
                +", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                +", "
                + duration()
                +" nights."
                ;
    }

}
