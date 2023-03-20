import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

class FNCD {
    Double operatingBudget;
    Date date;
    // Location of the FNCD branch.
    String location;
    VehicleInventory  vehicle;
    ManageStaff salesperson;

    // ArrayLists to track staff, buyers, and vehicles.
    ArrayList<Buyer> buyerList = new ArrayList<>();
    ArrayList<ManageStaff> departedStaff = new ArrayList<>();
    ArrayList<VehicleInventory> soldInventory = new ArrayList<>();
    Boolean Addons[] = new Boolean[4];

    // Constructor for the FNCD class that takes in the location of the branch.
    public FNCD(String location) {
        this.location = location;
        this.operatingBudget = 500000.00;
    }

    public FNCD(){
        this.operatingBudget = 500000.00;
    }

    // Display the menu options for the FNCD branch to the user for the interactive interface.
    public void displayMenu()
    {
        System.out.println("Select one of the Menu options below:");
        System.out.println("1. Enquire about the Sales Person's name.");
        System.out.println("2. Ask Sales Person the time.");
        System.out.println("3. Get a new Sales Person assigned.");
        System.out.println("4. Get the current available inventory.");
        System.out.println("5. Get details of the desired vehicle.");
        System.out.println("6. Buy the desired vehicle.");
        System.out.println("7. End interaction.");
        System.out.println("0. Main Menu.");

    }

    // Initiate the opening operation for the day.
    public void OpeningOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, ArrayList<Subscriber> publisherList){
        Opening open = new Opening();
        open.ops(staffAdmin, vehicleAdmin, this, publisherList);
    }

    // Initiate the washing operation for the day.
    public void WashingOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, ArrayList<Subscriber> publisherList){
        // reference:  https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
        final String[] proper_noun = {"Chemical", "Elbow", "Detailed"};

        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        if(proper_noun[index]=="Chemical"){
            Washing wash = new Washing(new ChemicalWash());
            System.out.println("Washing with Chemical");
            wash.operation(staffAdmin, vehicleAdmin, this, publisherList);
        }
        else if(proper_noun[index]=="Elbow"){
            Washing wash = new Washing(new ElbowGreaseWash());
            System.out.println("Washing with Elbow");
            wash.operation(staffAdmin, vehicleAdmin, this, publisherList);
        }
        else if(proper_noun[index]=="Detailed"){
            Washing wash = new Washing(new DetailedWash());
            System.out.println("Washing with Detailed");
            wash.operation(staffAdmin, vehicleAdmin, this, publisherList);
        }
    }

    // Initiate the repair operation for the day.
    public void RepairOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, ArrayList<Subscriber> publisherList){
        Repair rep = new Repair();
        rep.ops(staffAdmin, vehicleAdmin, this, publisherList);
    }

    // Initiate the sales operation for the day.
    public void SalesOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin,  ArrayList<Subscriber> publisherList, Date date){
        Selling sale = new Selling(date);
        sale.ops(staffAdmin, vehicleAdmin, this, publisherList);
    }   

    // Initiate the racing operation for the day.
    public void RaceOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, ArrayList<Subscriber> publisherList){
        Racing race = new Racing();
        race.ops(staffAdmin, vehicleAdmin, this, publisherList);
    }

    // Initiate the end of day operation for the day.
    public void EndOfDayOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, ArrayList<Subscriber> observerList){
        EndOfDay end = new EndOfDay();
        end.ops(staffAdmin, vehicleAdmin, this, observerList);
    }

    // Initiate the printing of the report for the day.
    public void PrintReportOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin){
        Reporting report = new Reporting();
        report.ops(staffAdmin, vehicleAdmin, this);
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

    // querySalesPersonName method to query the sales person's name for the user.
    // It also prints the name of the sales person to the console.
    public void querySalesPersonName(ManageStaff staffAdmin)
    {
        // Get a random sales person from the sales team.
        ArrayList<ManageStaff> salesPersonList = staffAdmin.salesTeam;
        Collections.shuffle(salesPersonList);
        this.salesperson = salesPersonList.get(0);
        System.out.println(this.salesperson.getName());
    }

    // querySalesPersonTime method to query the sales person's time for the user.
    public void querySalesPersonTime() {
        // Reference: https://stackoverflow.com/questions/20326619/time-in-hhmm-format
        LocalTime time = LocalTime.now();
        String currentTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println("The time is " + currentTime);
    }

    // queryForDifferentSalesPerson method to query the user for a different sales person.
    // It also prints the name of the new sales person to the console.
    public void queryForDifferentSalesPerson(ManageStaff staffAdmin)
    {
        // Get a random sales person from the sales team.
        ArrayList<ManageStaff> salesPersonList = staffAdmin.salesTeam;
        salesPersonList.remove(this.salesperson);
        Collections.shuffle(salesPersonList);
        this.salesperson = salesPersonList.get(0);
        System.out.println("New Sales Person is " + this.salesperson.getName());
    }

    // queryForStoreInventory method to query the user for the store inventory.
    // It also prints the store inventory to the console.
    // Finally, it asks the user to select the desired vehicle from the list.
    public void queryForStoreInventory(VehicleInventory vehicleAdmin)
    {
        ArrayList<VehicleInventory> allVehicles = vehicleAdmin.getAllVehicles();
        Scanner scan = new Scanner(System.in);

        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |%n", 
                            "Sl.No", "Vehicle Type", "Vehicle ID", "Condition", "Cleanliness","Selling Price" );
        
        for(int i=0; i<allVehicles.size(); i++)
        {
            VehicleInventory tmp_vehicle = allVehicles.get(i);
            System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |%n", 
                i, tmp_vehicle.modelType, tmp_vehicle.getID(), tmp_vehicle.condition, tmp_vehicle.cleanliness, tmp_vehicle.getSellingPrice());
        }
        
        System.out.println("Choose the desired vehicle from the above inventory");
        int vehicleIndex = scan.nextInt();
        vehicle = allVehicles.get(vehicleIndex);
    }

    // queryForParticularInventory method to query the user for a particular inventory.
    // It also prints the particular inventory to the console.
    public void queryForParticularInventory(VehicleInventory vehicleAdmin)
    {
        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s |%n", 
                            "Vehicle Type", "Vehicle ID", "Condition", "Cleanliness","Selling Price" );
        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s |%n", 
                            vehicle.modelType, vehicle.getID(), vehicle.condition, vehicle.cleanliness, vehicle.getSellingPrice());
    }

    // queryForAddons method to query the user for the addons.
    // sellVehicle method to sell the vehicle to the customer.
    // It also publishes the event information to the subscribers.
    public void sellVehicle(VehicleInventory vehicleAdmin, ArrayList<Subscriber> publisherList)
    {
        // Generate the add-ons for the vehicle.
        ExtendedWarranty vehicle_E;
        Undercoating vehicle_U;
        RoadRescueCoverage vehicle_RRC;
        SatelliteRadio vehicle_SR;
        Scanner scan = new Scanner(System.in);

        AddOnInterface expensiveVehicle_tmp = vehicle;

        System.out.println("Select the necessary Addons for the vehicles by giving 1 for yes and 0 for no");
        System.out.println("Please choose if you want Extended Warranty");
        int tmp_choice = scan.nextInt();
        if(tmp_choice == 0)
        {
            Addons[0] = true;
        }
        else{
            Addons[0] = false;
        }
        System.out.println("Please choose if you want Undercoating");
        tmp_choice = scan.nextInt();
        if(tmp_choice == 0)
        {
            Addons[1] = true;
        }
        else{
            Addons[1] = false;
        }
        System.out.println("Please choose if you want Road Rescue coverage");
        tmp_choice = scan.nextInt();
        if(tmp_choice == 0)
        {
            Addons[2] = true;
        }
        else{
            Addons[2] = false;
        }
        System.out.println("Please choose if you want Satellite Radio");
        tmp_choice = scan.nextInt();
        if(tmp_choice == 0)
        {
            Addons[3] = true;
        }
        else{
            Addons[3] = false;
        }


        // Recaliberate Selling Price according to the add-ons.
        if(Addons[0] == true)
        {
            vehicle_E = new ExtendedWarranty(expensiveVehicle_tmp,true);
        }
        // If the buyer does not want the ExtendedWarranty add-on, then add the Undercoating add-on to the vehicle.
        else
        {
            vehicle_E = new ExtendedWarranty(expensiveVehicle_tmp,false);   
        }
        if(Addons[1] == true)
        {
            vehicle_U = new Undercoating(vehicle_E, true);
    
        }
        else 
        {
            vehicle_U = new Undercoating(vehicle_E,false);
        }
        // If the buyer does not want the Undercoating add-on, then add the RoadRescueCoverage add-on to the vehicle.
        if(Addons[2] == true)
        {
            vehicle_RRC = new RoadRescueCoverage(vehicle_U, true);
        }
        else
        {
            vehicle_RRC = new RoadRescueCoverage(vehicle_U, false);
        }
        // If the buyer does not want the RoadRescueCoverage add-on, then add the SatelliteRadio add-on to the vehicle.
        if(Addons[3] == true)
        {
            vehicle_SR = new SatelliteRadio(vehicle_RRC, true);
        }
        else
        {
            vehicle_SR = new SatelliteRadio(vehicle_RRC, false);
        }

        this.vehicle.setSellingPrice(vehicle_SR.getSellingPrice());
        // Get the selling price of the vehicle with the final addition of the add-ons.
        this.operatingBudget += vehicle_SR.getSellingPrice();
        // Push the selling price to the subscribers.
        this.pushEventInfoToSubscribers(0.0d,vehicle_SR.getSellingPrice(), null, publisherList);

        // Calculate the salesperson's bonus.
        Double salesPersonBonus = (vehicle.saleBonus)*(salesperson.bonusRate);
        this.operatingBudget -= salesPersonBonus;
        salesperson.bonus = salesPersonBonus;
        // Push the salesperson's bonus to the subscribers.
        this.pushEventInfoToSubscribers(salesPersonBonus, 0.0d, null, publisherList);
        
        this.soldInventory.add(vehicle);
        String sellingOutcome = ("SalesPerson " + salesperson.getName() + " sold " + 
                            vehicle.cleanliness + " "+
                            vehicle.condition + " "+ 
                            vehicle.modelType + " " + 
                            vehicle.getID() + " to Buyer for $" +
                            vehicle.getSellingPrice() + " (earned $" + salesPersonBonus + " bonus)");
        // Push the selling outcome to the subscribers.
        this.pushEventInfoToSubscribers(0.0d,0.0d, sellingOutcome, publisherList);
        removeCarfromList(vehicle, vehicleAdmin);
    }

    // Remove car from list after selling.
    public void removeCarfromList(VehicleInventory expensiveVehicle, VehicleInventory vehicleAdmin){
        if(expensiveVehicle.modelType == "performancecar"){
            vehicleAdmin.removeCars("performancecar", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "car"){
            vehicleAdmin.removeCars("car", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "pickup"){
            vehicleAdmin.removeCars("pickup",expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "electriccar"){
            vehicleAdmin.removeCars("Electric", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "MotorCycle"){
            vehicleAdmin.removeCars("MotorCycle", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "MonsterTruck"){
            vehicleAdmin.removeCars("MonsterTruck", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "Ambulance"){
            vehicleAdmin.removeCars("Ambulance", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "Bus"){
            vehicleAdmin.removeCars("Bus", expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "SkateBoard"){
            vehicleAdmin.removeCars("SkateBoard", expensiveVehicle);
        }
    }

    // endInteraction() method is used to end the interaction with the user.
    public void endInteraction(){}

    // InteractiveInterface() method is used to interact with the user.
    public void InteractiveInterface(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, ArrayList<Subscriber> publisherList)
    {
        
        Invoker invoker = new Invoker();
        System.out.println("\n\nWelcome to FNCD! Please check the below options");
        this.displayMenu();


        Scanner scan = new Scanner(System.in);       
        int option = scan.nextInt();

        // Loop until the user chooses to exit the application.
        while(option!=7)
        {
            switch(option)
            {   
                // Get the name of the salesperson.
                case 1 : invoker.take(new GetSalesPersonName(this, vehicleAdmin, staffAdmin));
                         invoker.execute();
                         break;

                // Get the time of the day.
                case 2 : invoker.take(new GetTime(this, vehicleAdmin, staffAdmin));
                         invoker.execute();
                         break;

                // Get the name of the new salesperson.
                case 3 : invoker.take(new GetAnotherSalesPerson(this, vehicleAdmin, staffAdmin));
                         invoker.execute();
                         break;
                
                // Get the inventory of the vehicles at the selected dealership.
                case 4 : invoker.take(new GetInventory(this, vehicleAdmin, staffAdmin));
                         invoker.execute();
                         break;
                
                // Get the details of the desired vehicle.
                case 5 : invoker.take(new GetDetailsofVehicle(this, vehicleAdmin, staffAdmin));
                         invoker.execute();
                         break;

                // Buy the desired vehicle, assign the bonus to the salesperson and push the information to the subscribers.
                case 6 : invoker.take(new BuyInventoryItems(this, vehicleAdmin, staffAdmin, publisherList));
                         invoker.execute();
                         break;
                
                case 7 : invoker.take(new EndInteraction(this, vehicleAdmin, staffAdmin));
                         invoker.execute();
                         break; 
                
            }

            this.displayMenu();
            option = scan.nextInt();
        }

        scan.close();
    }
}
