import java.util.*;

// Selling class to handle the sales operation for the day.
public class Selling {
    public Date date;
    Integer buyers = 0;
    boolean isBuying=false;
    VehicleInventory expensiveVehicle;
    Double totalSales = 0.0;
    ArrayList<VehicleInventory> potentialVehicleSellingList = new ArrayList<>();

    public Selling(){
        this.date = new Date();
    }
    public Integer getDateDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){

        // Generate the number of buyers for the day based on the day of the week.
        if(getDateDay(date) == 5 || getDateDay(date) == 6){
            buyers = new Random().nextInt(8)+2;
        }
        else if(getDateDay(date) >=1 && getDateDay(date)<5) {
            buyers = new Random().nextInt(5)+0;
        }
        else{
            System.out.println("HOLIDAAYY!");
            System.exit(0);
        }
        
        // Generate the buyers for the day.
        for(int i=0;i<buyers;i++){
            fncdAdmin.buyerList.add(new Buyer(staffAdmin, vehicleAdmin, fncdAdmin));
        }

        for(int i=0;i<fncdAdmin.buyerList.size();i++){
            Buyer buyer = fncdAdmin.buyerList.get(i);
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

            // If the buyer is buying the vehicle, add the vehicle to the soldInventory list and remove it from the vehicleAdmin list.
            if(potentialVehicleSellingList.size()!=0){
                expensiveVehicle = getMostExpensiveVehicle(potentialVehicleSellingList);
                isBuying = calculateBuyScenario(expensiveVehicle, buyer.baseChance, 0);
            }

            // If the buyer is buying the vehicle, add the vehicle to the soldInventory list and remove it from the vehicleAdmin list.
            if(potentialVehicleSellingList.size()==0){
                ArrayList<VehicleInventory> allVehicles = vehicleAdmin.getAllVehicles();
                expensiveVehicle = getMostExpensiveVehicle(allVehicles);
                isBuying = calculateBuyScenario(expensiveVehicle, buyer.baseChance, 20);
            }

            // If the buyer is buying the vehicle, add the vehicle to the soldInventory list and remove it from the vehicleAdmin list.
            if(isBuying){
                fncdAdmin.operatingBudget += expensiveVehicle.sellingPrice;
                totalSales += expensiveVehicle.sellingPrice;
                Double salesPersonBonus = (expensiveVehicle.saleBonus)*(buyer.salesPerson.bonusRate);
                fncdAdmin.operatingBudget -= salesPersonBonus;
                buyer.salesPerson.bonus = salesPersonBonus;
                (buyer.salesPerson.numVehicleSold)++;
                fncdAdmin.soldInventory.add(expensiveVehicle);
                System.out.println("SalesPerson " + buyer.salesPerson.getName() + " sold " + 
                                    expensiveVehicle.cleanliness + " "+
                                    expensiveVehicle.condition + " "+ 
                                    expensiveVehicle.modelType + " " + 
                                    expensiveVehicle.getID() + " to Buyer for $" +
                                    expensiveVehicle.getSellingPrice() + " (earned $" + salesPersonBonus + " bonus)");
                removeCarfromList(expensiveVehicle, vehicleAdmin);
            }
            // Reset numVehicleSold to 0 if 2 cars are sold by the salesPerson.
            if(buyer.salesPerson.numVehicleSold==2){
                buyer.salesPerson.numVehicleSold = 0;
                break;
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
            vehicleAdmin.removeCars("performancecar", null, null, expensiveVehicle);
        }
        else if(expensiveVehicle.modelType == "car"){
            vehicleAdmin.removeCars("car", expensiveVehicle, null, null);
        }
        else{
            vehicleAdmin.removeCars("pickup", null, expensiveVehicle, null);
        }
    }
}
