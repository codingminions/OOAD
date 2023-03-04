import java.util.*;

class FNCD {
    Double operatingBudget;
    Date date;
    // ArrayLists to track staff, buyers, and vehicles.
    ArrayList<Buyer> buyerList = new ArrayList<>();
    ArrayList<ManageStaff> departedStaff = new ArrayList<>();
    ArrayList<VehicleInventory> soldInventory = new ArrayList<>();

    public FNCD() {
        this.operatingBudget = 500000.00;
    }

    public Integer getDateDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    // Initiate the operations for the day
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        
        // Check if the day is a Wednesday to have all the operations + Racing operation.
        if(getDateDay(fncdAdmin.date) == 4){
            // Initiate the operations for the day.
            OpeningOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            WashingOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            RepairOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            SalesOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList, this.date);
            RaceOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            EndOfDayOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
        }
        // Check if the day is a Sunday to have the Racing operation.
        else if(getDateDay(fncdAdmin.date) == 1)
        {
            RaceOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            EndOfDayOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
        }
        else{
            // Initiate the operations for the day.
            OpeningOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            WashingOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            RepairOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
            SalesOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList, this.date);
            EndOfDayOperation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
        }
    }

    // Initiate the opening operation for the day.
    public void OpeningOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        Opening open = new Opening();
        open.ops(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
    }

    // Initiate the washing operation for the day.
    public void WashingOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        // reference:  https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
        final String[] proper_noun = {"Chemical", "Elbow", "Detailed"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        if(proper_noun[index]=="Chemical"){
            Washing wash = new Washing(new ChemicalWash());
            System.out.println("Washing with Chemical");
            wash.operation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
        }
        else if(proper_noun[index]=="Elbow"){
            Washing wash = new Washing(new ElbowGreaseWash());
            System.out.println("Washing with Elbow");
            wash.operation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
        }
        else if(proper_noun[index]=="Detailed"){
            Washing wash = new Washing(new DetailedWash());
            System.out.println("Washing with Detailed");
            wash.operation(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
        }
    }

    // Initiate the repair operation for the day.
    public void RepairOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        Repair rep = new Repair();
        rep.ops(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
    }

    // Initiate the sales operation for the day.
    public void SalesOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList, Date date){
        Selling sale = new Selling(date);
        sale.ops(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
    }   

    // Initiate the racing operation for the day.
    public void RaceOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        Racing race = new Racing();
        race.ops(staffAdmin, vehicleAdmin, fncdAdmin, publisherList);
    }

    // Initiate the end of day operation for the day.
    public void EndOfDayOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> observerList){
        EndOfDay end = new EndOfDay();
        end.ops(staffAdmin, vehicleAdmin, fncdAdmin, observerList);
    }

    // Initiate the printing of the report for the day.
    public void PrintReportOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Reporting report = new Reporting();
        report.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }

    // updateSubscribers method to update the observers with the latest information from the subject.
    public void updateSubscribers(SubscriberEventInfo subscriberEventInfo, ArrayList<Subscriber> subscribersList) {
        for (Subscriber subscriber : subscribersList) {
            if(subscriber instanceof Logger){
                ((Logger)subscriber).sendEventDataToSubsciber(subscriberEventInfo);
            }
            else if(subscriber instanceof Tracker){
                ((Tracker)subscriber).sendEventDataToSubsciber(subscriberEventInfo);
            }
        }
    }

    // pushEventInfoToSubscribers method to push the event information to the subscribers.
    public void pushEventInfoToSubscribers(Double staffRevenue, Double fncdRevenue, String logMessage, ArrayList<Subscriber> subscribersList){
        SubscriberEventInfo subscriberEventInfo = new SubscriberEventInfo(staffRevenue, fncdRevenue, logMessage);
        updateSubscribers(subscriberEventInfo, subscribersList);
    }

    // printCumulativeTrackData method to print the cumulative track data for the day.
    public void printCumulativeTrackData(ArrayList<Subscriber> subscribersList){
        for (Subscriber subscriber : subscribersList) {
            if(subscriber instanceof Tracker){
                ((Tracker) subscriber).printCumulativeData();
            }
        }
    }
}
