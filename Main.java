import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Create a new FNCD object and run the ops method.
        FNCD fncdAdmin = new FNCD();
        ManageStaff staffAdmin = new ManageStaff();
        VehicleInventory vehicleAdmin = new VehicleInventory(staffAdmin); 
        fncdAdmin.date = new Date();

        for(int i=1;i<=30;i++) {
            // Attach the subscibers to the publisher list to be used in the FNCD class.
            ArrayList<Subscriber> publisherList = new ArrayList<>();
            try {
                // Create a new logger and tracker object and add them to the publisher list.
                Subscriber logger = new Logger(i);
                publisherList.add(logger);
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
                
            }
            try {
                // Create a new tracker object and add it to the publisher list.
                Subscriber tracker = new Tracker(i);
                publisherList.add(tracker);
            }
            catch(Exception e) {
                System.out.println("Error: " + e);
            }
            System.out.println("\n****FNCD Day: "+ i +"****\n");
            fncdAdmin.ops(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            fncdAdmin.printCumulativeTrackData(publisherList);

            // Check if the operating budget is less than or equal to 0. If it is, replenish the budget by $250,000.00.
            if(fncdAdmin.operatingBudget <= 0.0d){
                fncdAdmin.operatingBudget += 250000.00;
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 250000.00, null, publisherList);
                String logMessage = "FNCD has run out of money. The operating budget has been replenished by $250,000.00";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, logMessage, publisherList);
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fncdAdmin.date);
            // add 1 day to the current date
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // get the updated date
            fncdAdmin.date = calendar.getTime();
        }

        System.out.print("\n\n\n Printing Report...");
        // Print the report at the end of the month.
        fncdAdmin.PrintReportOperation(staffAdmin, vehicleAdmin, fncdAdmin);
    }
}