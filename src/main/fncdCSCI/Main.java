public class Main {
    public static void main(String[] args) {
        // Create the FNCD object and the two SuperFNCD objects that will be used to simulate the FNCD.
        FNCD fncdEastCoast = new FNCD("East Coast");
        FNCD fncdWestCoast = new FNCD("West Coast");

        SuperFNCD fncdAdmin = new SuperFNCD(fncdEastCoast, fncdWestCoast);

        ManageStaff staffEastCoastAdmin = new ManageStaff();
        VehicleInventory vehicleEastCoastAdmin = new VehicleInventory(staffEastCoastAdmin); 

        ManageStaff staffWestCoastAdmin = new ManageStaff();
        VehicleInventory vehicleWestCoastAdmin = new VehicleInventory(staffWestCoastAdmin);    

        // fncdAdmin.storeSimulationEvents("SimResults.txt");
        // Call the fncdSimulation method to simulate the FNCD operations for the day.
        fncdAdmin.fncdSimulation(staffEastCoastAdmin, vehicleEastCoastAdmin, staffWestCoastAdmin, vehicleWestCoastAdmin);
    }
}