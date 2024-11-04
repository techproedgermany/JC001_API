package pojos;

public class HerokuResponsePojo {
    private int bookingid;
    private HerokuAppPojo booking;

    public HerokuResponsePojo() {
    }

    public HerokuResponsePojo(int bookingid, HerokuAppPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public HerokuAppPojo getBooking() {
        return booking;
    }

    public void setBooking(HerokuAppPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerokuResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
