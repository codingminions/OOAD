
import java.util.*;

// Selling class to handle the sales operation for the day.
public class Selling {
    public Date date;
    Integer buyers = 0;
    boolean isBuying=false;
    VehicleInventory expensiveVehicle;
    Double totalSales = 0.0;
    ArrayList<VehicleInventory> potentialVehicleSellingList = new ArrayList<>();

    public Selling(Date date){
        this.date = date;
    }

    // assignProbabiltiy() method to assign a probability to each option.
    public String assignProbabiltiy(String[] options, double[] Probabilities)
    {
        Random rand = new Random();

        double randNum = rand.nextDouble();
        double Probsum = 0.0;
        int selectedIndex = 0;

            for (int j = 0; j < Probabilities.length; j++) {
                Probsum += Probabilities[j];
                if (randNum <= Probsum) {
                    selectedIndex = j;
                    break;
                }
            }
        return options[selectedIndex];
    }

    // getDateDay() method to get the day of the week in integer format.
    public Integer getDateDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        // Generate the number of buyers for the day based on the day of the week.
        boolean continueOps = false;      
        if(getDateDay(fncdAdmin.date) == 6 || getDateDay(fncdAdmin.date) == 7){
            buyers = new Random().nextInt(8)+2;
            continueOps = true;
        }
        else if(getDateDay(fncdAdmin.date) >=2 && getDateDay(fncdAdmin.date)<6) {
            buyers = new Random().nextInt(5)+0;
            continueOps = true;
        }
        else{

            System.out.println("HOLIDAAYY!");
            continueOps = false;
        }
        
        if(continueOps){
            System.out.println("Selling..."); 
            // Generate the buyers for the day.
            for(int i=0;i<buyers;i++){
                fncdAdmin.buyerList.add(new Buyer(staffAdmin, vehicleAdmin, fncdAdmin));
            }

            for(int i=0;i<fncdAdmin.buyerList.size();i++){
                Buyer buyer = fncdAdmin.buyerList.get(i);
                
                // Generate the add-ons for the vehicle.
                ExtendedWarranty vehicle_E;
                Undercoating vehicle_U;
                RoadRescueCoverage vehicle_RRC;
                SatelliteRadio vehicle_SR = new SatelliteRadio(vehicleAdmin, false);
                
                // Calculate the list of vehicles that the buyer is interested in buying based on the model type.
                if(buyer.vehicleDesired.modelType == "performancecar"){
                    potentialVehicleSellingList = vehicleAdmin.PerformanceCars;
                }
                if(buyer.vehicleDesired.modelType == "car"){
                    potentialVehicleSellingList = vehicleAdmin.Cars;
                }
                if(buyer.vehicleDesired.modelType == "pickupcar"){
                    potentialVehicleSellingList = vehicleAdmin.Pickup;
                }

                // Caculate the probability of the buyer buying the vehicle with the add-onss.
                if(potentialVehicleSellingList.size()!=0){
                    expensiveVehicle = getMostExpensiveVehicle(potentialVehicleSellingList);
                    AddOnInterface expensiveVehicle_tmp = expensiveVehicle;
                    String[] options = {"yes","no"};
                    double[] Probabilities_ExtendedWarranty = {0.25,0.75};
                    double[] Probabilities_Undercoating = {0.1, 0.9};
                    double[] Probabilities_RoadRescueCoverage = {0.05, 0.95};
                    double[] Probabilities_SatelliteRadio = {0.4, 0.6};


                    String option_EW = assignProbabiltiy(options, Probabilities_ExtendedWarranty);
                    String option_U = assignProbabiltiy(options, Probabilities_Undercoating);
                    String option_RRC = assignProbabiltiy(options, Probabilities_RoadRescueCoverage);
                    String option_SR = assignProbabiltiy(options, Probabilities_SatelliteRadio);

                    // Add the ExtendedWarranty add-on to the vehicle.
                    if(option_EW == "yes")
                    {
                        vehicle_E = new ExtendedWarranty(expensiveVehicle_tmp,true);
                    }
                    // If the buyer does not want the ExtendedWarranty add-on, then add the Undercoating add-on to the vehicle.
                    else
                    {
                        vehicle_E = new ExtendedWarranty(expensiveVehicle_tmp,false);   
                    }
                    if(option_U == "yes")
                    {
                        vehicle_U = new Undercoating(vehicle_E, true);
                
                    }
                    else 
                    {
                        vehicle_U = new Undercoating(vehicle_E,false);
                    }
                    // If the buyer does not want the Undercoating add-on, then add the RoadRescueCoverage add-on to the vehicle.
                    if(option_RRC == "yes")
                    {
                        vehicle_RRC = new RoadRescueCoverage(vehicle_U, true);
                    }
                    else
                    {
                        vehicle_RRC = new RoadRescueCoverage(vehicle_U, false);
                    }
                    // If the buyer does not want the RoadRescueCoverage add-on, then add the SatelliteRadio add-on to the vehicle.
                    if(option_SR == "yes")
                    {
                        vehicle_SR = new SatelliteRadio(vehicle_RRC, true);
                    }
                    else
                    {
                        vehicle_SR = new SatelliteRadio(vehicle_RRC, false);
                    }
                    // Calculate the probability of the buyer buying the vehicle with the add-ons.
                    isBuying = calculateBuyScenario(expensiveVehicle, buyer.baseChance, 0);
                }

                // If the buyer is buying the vehicle, add the vehicle to the soldInventory list and remove it from the vehicleAdmin list.
                if(potentialVehicleSellingList.size()==0){
                    ArrayList<VehicleInventory> allVehicles = vehicleAdmin.getAllVehicles();
                    expensiveVehicle = getMostExpensiveVehicle(allVehicles);
                    AddOnInterface expensiveVehicle_tmp = expensiveVehicle;
                    String[] options = {"yes","no"};
                    // Calculate the probability of the buyer buying the vehicle with the add-ons.
                    double[] Probabilities_ExtendedWarranty = {0.25,0.75};
                    double[] Probabilities_Undercoating = {0.1, 0.9};
                    double[] Probabilities_RoadRescueCoverage = {0.05, 0.95};
                    double[] Probabilities_SatelliteRadio = {0.4, 0.6};

                    String option_EW = assignProbabiltiy(options, Probabilities_ExtendedWarranty);
                    String option_U = assignProbabiltiy(options, Probabilities_Undercoating);
                    String option_RRC = assignProbabiltiy(options, Probabilities_RoadRescueCoverage);
                    String option_SR = assignProbabiltiy(options, Probabilities_SatelliteRadio);

                    // Add the ExtendedWarranty add-on to the vehicle.
                    if(option_EW == "yes")
                    {
                        vehicle_E = new ExtendedWarranty(expensiveVehicle_tmp,true);
                    }
                    else
                    {
                        vehicle_E = new ExtendedWarranty(expensiveVehicle_tmp,false);   
                    }
                    // If the buyer does not want the ExtendedWarranty add-on, then add the Undercoating add-on to the vehicle.
                    if(option_U == "yes")
                    {
                        vehicle_U = new Undercoating(vehicle_E, true);
                
                    }
                    //  If the buyer does not want the Undercoating add-on, then add the RoadRescueCoverage add-on to the vehicle.
                    else 
                    {
                        vehicle_U = new Undercoating(vehicle_E,false);
                    }
                    if(option_RRC == "yes")
                    {
                        vehicle_RRC = new RoadRescueCoverage(vehicle_U, true);
                    }
                    // If the buyer does not want the RoadRescueCoverage add-on, then add the SatelliteRadio add-on to the vehicle.
                    else
                    {
                        vehicle_RRC = new RoadRescueCoverage(vehicle_U, false);
                    }
                    // If the buyer does not want the SatelliteRadio add-on, then add the SatelliteRadio add-on to the vehicle.
                    if(option_SR == "yes")
                    {
                        vehicle_SR = new SatelliteRadio(vehicle_RRC, true);
                    }// If the buyer does not want the SatelliteRadio add-on, then add the SatelliteRadio add-on to the vehicle.
                    else
                    {
                        vehicle_SR = new SatelliteRadio(vehicle_RRC, false);
                    }

                    isBuying = calculateBuyScenario(expensiveVehicle, buyer.baseChance, 20);
                }

                // If the buyer is buying the vehicle, add the vehicle to the soldInventory list and remove it from the vehicleAdmin list.
                if(isBuying){
                    // Get the selling price of the vehicle with the final addition of the add-ons.
                    fncdAdmin.operatingBudget += vehicle_SR.getSellingPrice();
                    totalSales += vehicle_SR.getSellingPrice();
                    // Push the selling price to the subscribers.
                    fncdAdmin.pushEventInfoToSubscribers(0.0d,vehicle_SR.getSellingPrice(), null, publisherList);

                    // Calculate the salesperson's bonus.
                    Double salesPersonBonus = (expensiveVehicle.saleBonus)*(buyer.salesPerson.bonusRate);
                    fncdAdmin.operatingBudget -= salesPersonBonus;
                    buyer.salesPerson.bonus = salesPersonBonus;
                    // Push the salesperson's bonus to the subscribers.
                    fncdAdmin.pushEventInfoToSubscribers(salesPersonBonus, 0.0d, null, publisherList);
                    
                    (buyer.salesPerson.numVehicleSold)++;
                    fncdAdmin.soldInventory.add(expensiveVehicle);
                    String sellingOutcome = ("SalesPerson " + buyer.salesPerson.getName() + " sold " + 
                                        expensiveVehicle.cleanliness + " "+
                                        expensiveVehicle.condition + " "+ 
                                        expensiveVehicle.modelType + " " + 
                                        expensiveVehicle.getID() + " to Buyer for $" +
                                        expensiveVehicle.getSellingPrice() + " (earned $" + salesPersonBonus + " bonus)");
                    // Push the selling outcome to the subscribers.
                    fncdAdmin.pushEventInfoToSubscribers(0.0d,0.0d, sellingOutcome, publisherList);
                    removeCarfromList(expensiveVehicle, vehicleAdmin);
                }
                // Reset numVehicleSold to 0 if 2 cars are sold by the salesPerson.
                if(buyer.salesPerson.numVehicleSold==2){
                    buyer.salesPerson.numVehicleSold = 0;
                    break;
                }
            }
        }
    }

    // Calculate if buyer will buy car.
    public boolean calculateBuyScenario(VehicleInventory vehicle, Integer baseChance, Integer negativeChanceofSelling){
        Random rnd = new Random();
        boolean buy=false;

        if(negativeChanceofSelling>0){
            baseChance -= negativeChanceofSelling;
            if(baseChance<0){
                baseChance = 0;
            }
        }

        if(vehicle.condition == "New"){
            baseChance += 10;
        }
        if(vehicle.cleanliness == "Sparkling"){
            baseChance += 10;
        }

        Integer randGen = rnd.nextInt(100);

        if(baseChance>0 &&  randGen < baseChance){
            buy = true;
        }

        return buy;
    }

    // Get most expensive car from list.
    public VehicleInventory getMostExpensiveVehicle(ArrayList<VehicleInventory> VehicleSellingList){
        VehicleInventory tobeSoldVehicle = VehicleSellingList.get(0);
        Double maxCost = VehicleSellingList.get(0).sellingPrice;

        for(int i=1;i<VehicleSellingList.size();i++){
            if(maxCost < VehicleSellingList.get(i).sellingPrice){
                maxCost = VehicleSellingList.get(i).sellingPrice;
                tobeSoldVehicle = VehicleSellingList.get(i);
            }
        }

        return tobeSoldVehicle;
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
        
    }
}
