public class Main {
    public static void main(String[] args) {
        // Create a new FNCD object and run the ops method.
        FNCD fncdAdmin = new FNCD();
        ManageStaff staffAdmin = new ManageStaff();
        VehicleInventory vehicleAdmin = new VehicleInventory();        
        for(int i=1;i<=30;i++) {
            System.out.println("\n****FNCD Day: "+ i +"****\n");
            fncdAdmin.ops(staffAdmin, vehicleAdmin, fncdAdmin);
        }
        System.out.print("\n\n\n");
        // Print the report at the end of the month.
        fncdAdmin.PrintReportOperation(staffAdmin, vehicleAdmin, fncdAdmin);
    }
}