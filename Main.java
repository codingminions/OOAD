public class Main {
    public static void main(String[] args) {
        // Create a new FNCD object and run the ops method.
        FNCD fncdAdmin = new FNCD();
        ManageStaff staffAdmin = new ManageStaff();
        VehicleInventory vehicleAdmin = new VehicleInventory();        
        for(int i=1;i<=30;i++) {
            System.out.println("\n****FNCD Day: "+ i +"****\n");
	    // The end user doesn't need to know the details of the operations and hence this is a
            // good examples of Abstraction.
            fncdAdmin.ops(staffAdmin, vehicleAdmin, fncdAdmin);
        }
        System.out.print("\n\n\n");
        // Print the report at the end of the month.
        System.out.println("****FNCD 30 Days Simulation Report****");
	fncdAdmin.PrintReportOperation(staffAdmin, vehicleAdmin, fncdAdmin);
    }
}
