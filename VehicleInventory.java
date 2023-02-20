
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.ArrayList;

// VehicleInventory class that contains all the vehicles and their attributes.  
public class VehicleInventory
{
    public String ID;
    protected ArrayList<VehicleInventory> PerformanceCars = new ArrayList<>();
    protected ArrayList<VehicleInventory> Cars = new ArrayList<>();
    protected ArrayList<VehicleInventory> Pickup = new ArrayList<>();
    protected String condition;
    public String modelType; 
    protected String cleanliness; 
    public double costPrice; 
    protected double sellingPrice; 
    protected double saleBonus; 
    protected double repairBonus; 
    protected double washBonus;
    protected boolean soldStatus;

    // Assign the vehicle condition randomly.
    public VehicleInventory(){
        this.modelType = "performancecar";
        this.soldStatus = false;
        assignCarCondition();
        assignCarCleanliness();        
    }

    // Assign the vehicle condition randomly.
    public VehicleInventory(String modelType, String ID)
    {   
        this.ID = ID;
        this.modelType = modelType;
        this.soldStatus = false;
        assignCarCondition();
        assignCarCleanliness();
    }

    // Add the cars to the respective list based on the model type.
    protected void addCars(String modelType, VehicleInventory car, VehicleInventory pickupcar, VehicleInventory performanceCar)
    {
        if(modelType == "car")
        {
            this.Cars.add(car);
            this.modelType = "car";
        }
        if(modelType == "pickup")
        {
            this.Pickup.add(pickupcar);
            this.modelType = "pickup";
        }
        if(modelType == "performancecar")
        {
            this.PerformanceCars.add(performanceCar);
            this.modelType = "performancecar";
        }
    }

    // Remove the cars from the respective list based on the model type.
    protected void removeCars(String modelType, VehicleInventory car, VehicleInventory pickupcar, VehicleInventory performanceCar)
    {
        if(modelType == "car")
        {
            Cars.remove(car);
        }
        if(modelType == "pickup")
        {
            Pickup.remove(pickupcar);
        }
        if(modelType == "performancecar")
        {
            PerformanceCars.remove(performanceCar);
        }
    }

    public String getID(){
        return this.ID;
    }

    protected int getCarsCount()
    {
        return this.Cars.size();
    }

    protected int getPickupCount()
    {
        return this.Pickup.size();
    }

    protected int getPerformanceCarsCount()
    {
        return this.PerformanceCars.size();
    }

    // Assign the car cost randomly based on the model type.
    protected void assignCarCost(String modelType)
    {
        if(modelType == "car")
        {
            this.costPrice = ThreadLocalRandom.current().nextInt(10000,40000);
        }
        else if(modelType == "pickup")
        {
            this.costPrice = ThreadLocalRandom.current().nextInt(20000,40000);
        }
        else if(modelType == "performancecar")
        {
            this.costPrice = ThreadLocalRandom.current().nextInt(20000,40000);
        }
    }

    protected void assignCarBonus ()
    {
        this.saleBonus = (0.1)*this.sellingPrice;
        this.washBonus = (0.05)*this.sellingPrice;
        this.repairBonus = (0.15)*this.sellingPrice;
    }

    // Assign the car condition randomly.
    protected void assignCarCondition ()
    {
        String[] conditionArray = {"LikeNew", "Used", "Broken"};

        int idx = new Random().nextInt(conditionArray.length);

        switch(idx)
        {
            case 0 : this.condition = conditionArray[idx];
                    break;
            
            case 1 : this.condition = conditionArray[idx];
                    break;
            
            case 2 : this.condition = conditionArray[idx];
                    break;
            
            default : this.condition = conditionArray[1];
                    break;
        }  
    }

    // Assign the car cleanliness randomly.
    protected void assignCarCleanliness ()
    {
        String[] CleanlinessArray = {"sparkling","clean","dirty"};
        double[] Probabilities = {0.05, 0.35, 0.6};

        Random rand = new Random();

        double randNum = rand.nextDouble();
        double Probsum = 0.0;
        int selectedIndex = -1;

        for (int i = 0; i < Probabilities.length; i++) {
            Probsum += Probabilities[i];
            if (randNum <= Probsum) {
                selectedIndex = i;
                break;
                }
        }

        this.cleanliness = CleanlinessArray[selectedIndex];

    }
    // Calculate the selling price based on the condition of the car.
    protected void recaliberateCarCost ()
    {
        if(this.condition == "Used")
        {
            this.costPrice = (1-0.2)*this.costPrice;
        }
        else if(this.condition == "Broken")
        {
            this.costPrice = (1-0.5)*this.costPrice;
        }

        this.sellingPrice = 2 * this.costPrice;  
    }

    protected void sellCar ()
    {
    if(this.soldStatus == false)
        {
            this.soldStatus = true;
        }
    }
    
    protected void setSellingPrice(double sp)
    {
        this.sellingPrice = sp;
    }

    protected double getSellingPrice()
    {
        return this.sellingPrice;
    }

    protected void setCleanliness(String clean)
    {
        this.cleanliness = clean;
    }

    // Get the list of clean cars.
    protected ArrayList<VehicleInventory> getCleanVehicles(){
        ArrayList<VehicleInventory> cleanCars = new ArrayList<VehicleInventory>();

        for(VehicleInventory perf: PerformanceCars){
            if(perf.cleanliness == "clean"){
                cleanCars.add(perf);
            }
        }

        for(VehicleInventory pickupcar: Pickup){
            if(pickupcar.cleanliness == "clean"){
                cleanCars.add(pickupcar);
            }
        }

        for(VehicleInventory car: Cars){
            if(car.cleanliness == "clean"){
                cleanCars.add(car);
            }
        }
        return cleanCars;
    }

    // Get the list of dirty cars.
    protected ArrayList<VehicleInventory> getDirtyVehicles(){
        ArrayList<VehicleInventory> dirtyCars = new ArrayList<VehicleInventory>();

        for(VehicleInventory perf: PerformanceCars){
            if(perf.cleanliness == "dirty"){
                dirtyCars.add(perf);
            }
        }

        for(VehicleInventory pickupcar: Pickup){
            if(pickupcar.cleanliness == "dirty"){
                dirtyCars.add(pickupcar);
            }
        }

        for(VehicleInventory car: Cars){
            if(car.cleanliness == "dirty"){
                dirtyCars.add(car);
            }
        }
        return dirtyCars;
    }

    // Get the list of broken cars.
    protected ArrayList<VehicleInventory> getBrokenVehicles()
    {
        ArrayList<VehicleInventory> Brokencars = new ArrayList<VehicleInventory>();
        for(VehicleInventory perf: PerformanceCars){
            if(perf.condition == "Broken"){
                Brokencars.add(perf);
            }
        }

        for(VehicleInventory pickupcar: Pickup){
            if(pickupcar.condition == "Broken"){
                Brokencars.add(pickupcar);
            }
        }

        for(VehicleInventory car: Cars){
            if(car.condition == "Broken"){
                Brokencars.add(car);
            }
        }
        return Brokencars;
    }
    // Get the list of used cars.
    protected ArrayList<VehicleInventory> getUsedVehicles()
    {
        ArrayList<VehicleInventory> Usedvehicles = new ArrayList<VehicleInventory>();
        for(VehicleInventory perf: PerformanceCars){
            if(perf.condition == "Used"){
                Usedvehicles.add(perf);
            }
        }

        for(VehicleInventory pickupcar: Pickup){
            if(pickupcar.condition == "Used"){
                Usedvehicles.add(pickupcar);
            }
        }

        for(VehicleInventory car: Cars){
            if(car.condition == "Used"){
                Usedvehicles.add(car);
            }
        }
        return Usedvehicles;
    }

    // Get the list of all vehicles.
    protected ArrayList<VehicleInventory> getAllVehicles()
    {
        ArrayList<VehicleInventory> allVehicles = new ArrayList<VehicleInventory>();
        allVehicles.addAll(PerformanceCars);
        allVehicles.addAll(Cars);
        allVehicles.addAll(Pickup);
        return allVehicles;
    }
}