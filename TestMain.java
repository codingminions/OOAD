import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import java.beans.Transient;

public class TestMain{

    @Test
    public void testFNCDOpeningVehicleFunctionality(){
        FNCD fncd = new FNCD();
        ManageStaff staffAdmin = new ManageStaff();
        VehicleInventory vehicleAdmin = new VehicleInventory(staffAdmin); 
        ArrayList<Subscriber> publisherList = new ArrayList<>();
            try {
                Subscriber logger = new Logger(1);
                publisherList.add(logger);
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
                
            }
            try {
                Subscriber tracker = new Tracker(1);
                publisherList.add(tracker);
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
            }
        fncd.OpeningOperation(staffAdmin, vehicleAdmin, fncd, publisherList);
        assertTrue("Number of cars at opening event needs to be 4", vehicleAdmin.getCarsCount()==4);
        assertTrue("Number of pickups at opening event needs to be 4",vehicleAdmin.getPickupCount()==4);
        assertTrue("Number of performance cars at opening event needs to be 4", vehicleAdmin.getPerformanceCarsCount()==4);
        assertTrue("Number of electric cars at opening event needs to be 4",vehicleAdmin.getElectricCarsCount()==4);
        assertTrue("Number of monster trucks at opening event needs to be 4", vehicleAdmin.getMonsterTrucksCount()==4);
        assertTrue("Number of motorcycles at opening event needs to be 4",vehicleAdmin.getMotorCyclesCount()==4);
    }

    @Test
    public void testInitialOperatingBudget()
    {
        FNCD fncd = new FNCD();
        assertTrue("operating budget needs to be 500000 when we intialize the object", fncd.operatingBudget=500000.00);
    }

    @Test
    public void testFNCDOpeningStaffFunctionality()
    {
        FNCD fncd = new FNCD();
        ManageStaff staffAdmin = new ManageStaff();
        VehicleInventory vehicleAdmin = new VehicleInventory(staffAdmin);
        ArrayList<Subscriber> publisherList = new ArrayList<>();
            try {
                Subscriber logger = new Logger(1);
                publisherList.add(logger);
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
                
            }
            try {
                Subscriber tracker = new Tracker(1);
                publisherList.add(tracker);
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
            }
        fncd.OpeningOperation(staffAdmin, vehicleAdmin, fncd, publisherList);
        assertTrue("Number of interns at opening event needs to be 3", staffAdmin.internTeam.size()==3);
        assertTrue("Number of Mechanics at opening event needs to be 3", staffAdmin.mechTeam.size()==3);
        assertTrue("Number of Salesmen at opening event needs to be 3", staffAdmin.salesTeam.size()==3);
        assertTrue("Number of Driver at opening event needs to be 3",staffAdmin.driverTeam.size()==3);
    }


}