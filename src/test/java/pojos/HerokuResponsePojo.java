package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerokuResponsePojo {

    //Define fields
    private Integer bookingid;
    private HerokuAppPojo booking;

    //Constructors with and without parameters

    public HerokuResponsePojo() {
    }

    public HerokuResponsePojo(Integer bookingid, HerokuAppPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    //Getters and setters
    public Integer getBookingId() {
        return bookingid;
    }

    public void setBookingId(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public HerokuAppPojo getBooking() {
        return booking;
    }

    public void setBooking(HerokuAppPojo booking) {
        this.booking = booking;
    }

    //toString()
    @Override
    public String toString() {
        return "HerokuResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
