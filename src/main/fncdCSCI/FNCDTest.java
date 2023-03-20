import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class FNCDTest {

    // testFNCDOperatingBudget() method to test the operating budget of the FNCD.
    @Test
    public void testFNCDOperatinglBudget(){
        FNCD fncd = new FNCD();
        assertTrue("Expected FNCD Operating Budget is: ", fncd.operatingBudget==500000);
    }

    // testSubscriberNotification() method to test the notification of the subscribers.
    @Test
    public void testSubscriberNotification(){
        FNCD fncd = new FNCD();
        Tracker tracker = Tracker.getTrackerInstance();
        ArrayList<Subscriber> publisherList = new ArrayList<Subscriber>();
        publisherList.add(tracker);
        fncd.pushEventInfoToSubscribers(1000.00, 20000.00, null, publisherList);
        assertTrue("Expected Staff Revenue: 1000.00", tracker.staffTotalRevenue==1000.00);
        assertTrue("Expected FNCD Revenue:20000.00 ",tracker.fncdTotalRevenue==20000.00);
    }

    // testFNCDOpeningOperation() method to test the opening operation of the FNCD.
    @Test
    public void testFNCDOpeningOperation(){
        FNCD fncd = new FNCD();
        Tracker tracker = Tracker.getTrackerInstance();
        Logger logger = Logger.getLoggerInstance();

        ArrayList<Subscriber> publisherList = new ArrayList<Subscriber>();
        publisherList.add(tracker);
        publisherList.add(logger);

        ManageStaff staffAdmin = new ManageStaff();
        VehicleInventory vehicleAdmin = new VehicleInventory(staffAdmin); 

        fncd.OpeningOperation(staffAdmin, vehicleAdmin, publisherList);
        assertTrue("Expected Number of Interns:3", staffAdmin.internTeam.size()==3);
        assertTrue("Expected Number of Mechanics:3",staffAdmin.mechTeam.size()==3);
        assertTrue("Expected Number of SalesPerson:3", staffAdmin.salesTeam.size()==3);
        assertTrue("Expected Number of PerformanceCars:6",vehicleAdmin.PerformanceCars.size()==6);
        assertTrue("Expected Number of PickupCars:6", vehicleAdmin.Pickup.size()==6);
        assertTrue("Expected Number of Cars:6",vehicleAdmin.Cars.size()==6);
        assertTrue("Expected Number of Electric Cars:6",vehicleAdmin.ElectricCars.size()==6);
        assertTrue("Expected Number of MonsterTrucks:6", vehicleAdmin.MonsterTrucks.size()==6);
        assertTrue("Expected Number of MotorCycles:6",vehicleAdmin.MotorCycles.size()==6);
        assertTrue("Expected Number of Ambulances:6",vehicleAdmin.Ambulances.size()==6);
        assertTrue("Expected Number of Buses", vehicleAdmin.Bus.size()==6);
        assertTrue("Expected Number of SkateBoards",vehicleAdmin.SkateBoards.size()==6);
    }

}
