
import java.util.ArrayList; 
import java.util.Date;
import java.util.Calendar;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class SuperFNCD {
    FNCD fncdEastCoast, fncdWestCoast;

    // Constructor for the SuperFNCD class that takes in two FNCD objects as parameters and assigns them to the class variables.
    public SuperFNCD(FNCD fncdEastCoast, FNCD FNCDWestCoast){
        this.fncdEastCoast = fncdEastCoast;
        this.fncdWestCoast = FNCDWestCoast; 
    }

    public Integer getDateDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    // // reference: https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
    public void storeSimulationEvents(String simulatedFileLog) {
        try{
            System.setOut(new PrintStream(new FileOutputStream(simulatedFileLog)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String fncdSelectCommand(){
        // Create the invoker object to be used to execute the command.
        Invoker invoker = new Invoker();

       // Give the user the option to select the FNCD location.
        System.out.println("Select FNCD Location:");
        System.out.println("1.EastCoast 2.WestCoast");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        SelectFNCDLocation selectedFNCD = null;


        if(option==1) {
            selectedFNCD= new SelectFNCDLocation(fncdEastCoast);
        } else if(option==2) {
            selectedFNCD = new SelectFNCDLocation(fncdWestCoast);
        }

        // Execute the command to select the FNCD location and return the location of the selected FNCD.
        invoker.take(selectedFNCD);
        invoker.execute();
        return selectedFNCD.getFNCD().location;
    }

    public void fncdSimulation(ManageStaff staffEastCoastAdmin, VehicleInventory vehicleEastCoastAdmin, ManageStaff staffWestCoastAdmin, VehicleInventory vehicleWestCoastAdmin){
        // Create the logger and tracker objects to be used to log the events that occur during the simulation.
        // The logger and tracker objects are created using the Singleton pattern.
        Subscriber logger = Logger.getLoggerInstance();
        Subscriber tracker = Tracker.getTrackerInstance();

        // Create the array lists to store the publisher objects for the east and west coast FNCDs.
        ArrayList<Subscriber> publisherList_east = new ArrayList<>();
        ArrayList<Subscriber> publisherList_west = new ArrayList<>();

        Date date = new Date();

        // Add the logger and tracker objects to the publisher lists for the east and west coast FNCDs.
        publisherList_east.add(logger);
        publisherList_east.add(tracker);

        publisherList_west.add(logger);
        publisherList_west.add(tracker);

        fncdEastCoast.date = date;
        fncdWestCoast.date = date;

        for(int i=1;i<=30;i++) {
            // Set the stream output to the log file for the day.
            if(logger instanceof Logger){
                try {
                    ((Logger)logger).writeOutput(i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if(tracker instanceof Tracker){
                ((Tracker)tracker).setSimulationDay(i);
            }

            System.out.println("\n****FNCD Day: "+ i +"****\n");
            
            // Initiate the operations for the day
            // Check if the day is a Wednesday to have all the operations + Racing operation.
            if(getDateDay(date) != 1){
                // Initiate the operations for the day.
                fncdEastCoast.OpeningOperation(staffEastCoastAdmin,vehicleEastCoastAdmin, publisherList_east);
                fncdWestCoast.OpeningOperation(staffWestCoastAdmin, vehicleWestCoastAdmin, publisherList_west);

                fncdEastCoast.WashingOperation(staffEastCoastAdmin,vehicleEastCoastAdmin, publisherList_east);
                fncdWestCoast.WashingOperation(staffWestCoastAdmin, vehicleWestCoastAdmin,  publisherList_west);

                fncdEastCoast.RepairOperation(staffEastCoastAdmin,vehicleEastCoastAdmin,  publisherList_east);
                fncdWestCoast.RepairOperation(staffWestCoastAdmin, vehicleWestCoastAdmin,  publisherList_west);

                fncdEastCoast.SalesOperation(staffEastCoastAdmin,vehicleEastCoastAdmin,  publisherList_east, date);
                fncdWestCoast.SalesOperation(staffWestCoastAdmin, vehicleWestCoastAdmin,  publisherList_west, date);
            }

            // Check if the day is a Sunday to have the Racing operation.
            if(getDateDay(date) == 1 || getDateDay(date)==4)
            {
                fncdEastCoast.RaceOperation(staffEastCoastAdmin,vehicleEastCoastAdmin,  publisherList_east);
                fncdWestCoast.RaceOperation(staffWestCoastAdmin, vehicleWestCoastAdmin,  publisherList_west);
            }

            if(getDateDay(date) != 1)
            {
                fncdEastCoast.EndOfDayOperation(staffEastCoastAdmin,vehicleEastCoastAdmin, publisherList_east);
                fncdWestCoast.EndOfDayOperation(staffWestCoastAdmin, vehicleWestCoastAdmin,  publisherList_west);
            }

            fncdWestCoast.printCumulativeTrackData(publisherList_west);

            // Check if the operating budget is less than or equal to 0. If it is, replenish the budget by $250,000.00.
            if(fncdEastCoast.operatingBudget <= 0.0d){
                fncdEastCoast.operatingBudget += 250000.00;
                fncdEastCoast.pushEventInfoToSubscribers(0.0d, 250000.00, null, publisherList_east);
                String logMessage = "FNCD East Coast has run out of money. The operating budget has been replenished by $250,000.00";
                fncdEastCoast.pushEventInfoToSubscribers(0.0d, 0.0d, logMessage,publisherList_east);
            }

            if(fncdEastCoast.operatingBudget <= 0.0d){
                fncdEastCoast.operatingBudget += 250000.00;
                fncdEastCoast.pushEventInfoToSubscribers(0.0d, 250000.00, null, publisherList_west);
                String logMessage = "FNCD West Coast has run out of money. The operating budget has been replenished by $250,000.00";
                fncdEastCoast.pushEventInfoToSubscribers(0.0d, 0.0d, logMessage, publisherList_west);
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // add 1 day to the current date
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            // get the updated date
            date = calendar.getTime();
            fncdEastCoast.date = date;
            fncdWestCoast.date = date;

            // Clear the tracker data at the end of the day.
            if(tracker instanceof Tracker){
                ((Tracker)tracker).clearTrackerData();
            }
            
        }
        System.out.print("\n\n\n Printing Report for the East Coast...");
        // Print the report at the end of the month.
        fncdWestCoast.PrintReportOperation(staffEastCoastAdmin,vehicleEastCoastAdmin);
        System.out.print("\n\n\n Printing Report for the West Coast...");
        fncdEastCoast.PrintReportOperation(staffWestCoastAdmin, vehicleWestCoastAdmin);

        FNCD selectedFNCD = null;
        ManageStaff staffAdmin = null;
        VehicleInventory vehicleAdmin = null;
        ArrayList<Subscriber> publisherList = new ArrayList<Subscriber>();

        System.setOut(System.out);

        // Get the selected location from the user and set the FNCD, staff admin, vehicle admin and publisher list objects.
        String selectedLocation = fncdSelectCommand();
        if(selectedLocation == "East Coast"){
            selectedFNCD = fncdEastCoast;
            staffAdmin = staffEastCoastAdmin;
            vehicleAdmin = vehicleEastCoastAdmin;
            publisherList = publisherList_east;
        }
        else if(selectedLocation == "West Coast"){
            selectedFNCD = fncdWestCoast;
            staffAdmin = staffWestCoastAdmin;
            vehicleAdmin = vehicleWestCoastAdmin;
            publisherList = publisherList_west;
        }

        selectedFNCD.OpeningOperation(staffAdmin, vehicleAdmin, publisherList);
        selectedFNCD.WashingOperation(staffAdmin, vehicleAdmin, publisherList);
        selectedFNCD.RepairOperation(staffAdmin, vehicleAdmin, publisherList);

        // Initiate the the user interface for the selected location and print the report.
        selectedFNCD.InteractiveInterface(staffAdmin, vehicleAdmin, publisherList);
        selectedFNCD.PrintReportOperation(staffAdmin, vehicleAdmin);
    }
}
