package herokuApp_SmokeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Creating a Smoke Test Runner with Junit
@RunWith(Suite.class)
@Suite.SuiteClasses({
        C01PostBooking.class,
        C02GetBooking.class,
        C03UpdateBooking.class,
        C04PartialUpdateBooking.class,
        C05DeleteBooking.class,
        C06GetBookingAfterDeleting.class
})


public class Runner {


}
