package pojos;

public class BookingDatesPojo {

//     1) Create all variables as private (Encapsulation)
    private String checkin;
    private String checkout;

//     2) Create constructors with and without parameters

    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

//     3) Create getters and setters for all variables

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }


//     4) Create toString() method


    @Override
    public String toString() {
        return "{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
