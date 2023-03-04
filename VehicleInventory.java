import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.ArrayList;

// VehicleInventory class that contains all the vehicles and their attributes.  
public class VehicleInventory implements AddOnInterface
{
    public String ID;
    protected ArrayList<VehicleInventory> PerformanceCars = new ArrayList<>();
    protected ArrayList<VehicleInventory> Cars = new ArrayList<>();
    protected ArrayList<VehicleInventory> Pickup = new ArrayList<>();
    protected ArrayList<VehicleInventory> ElectricCars = new ArrayList<>();
    protected ArrayList<VehicleInventory> MotorCycles = new ArrayList<>();
    protected ArrayList<VehicleInventory> MonsterTrucks = new ArrayList<>();
    protected String condition;
    public String modelType; 
    protected String cleanliness; 
    public double costPrice; 
    protected double sellingPrice; 
    protected double saleBonus; 
    protected double repairBonus; 
    protected double washBonus;
    protected double winningBonus;
    protected boolean soldStatus;
    public boolean raceswon;
    long raceswonCount = 0;
    ManageStaff driver = null;


    // Assign the vehicle condition randomly.
    public VehicleInventory(ManageStaff staffAdmin){
        this.modelType = "performancecar";
        this.soldStatus = false;
        assignCarCondition();
        assignCarCleanliness();  
        this.raceswon = false;
    }

    // Assign the vehicle condition randomly.
    public VehicleInventory(String modelType, String ID, ManageStaff staffAdmin)
    {   
        this.ID = ID;
        this.modelType = modelType;
        this.soldStatus = false;
        assignCarCondition();
        assignCarCleanliness();
        this.raceswon = false;
    }

    // Set the race status to true and increment the race count for the winning vehicle.
    public void setRacesWon()
    {
        this.raceswon = true;
        this.raceswonCount++;
    }

    // Add the cars to the respective list based on the model type.
    protected void addCars(String modelType, VehicleInventory vehicle)
    {
        if(modelType == "car")
        {
            this.Cars.add(vehicle);
            this.modelType = "car";
        }
        else if(modelType == "pickup")
        {
            this.Pickup.add(vehicle);
            this.modelType = "pickup";
        }
        else if(modelType == "performancecar")
        {
            this.PerformanceCars.add(vehicle);
            this.modelType = "performancecar";
        }
        else if(modelType == "Electric")
        {
            this.ElectricCars.add(vehicle);
            this.modelType = "Electric";    
        }
        else if(modelType == "MotorCycle")
        {
            this.MotorCycles.add(vehicle);
            this.modelType = "MotorCycle";
        }
        else if(modelType == "MonsterTruck")
        {
            this.MonsterTrucks.add(vehicle);
            this.modelType = "MonsterTruck";
        }
    }

    // Remove the cars from the respective list based on the model type.
    protected void removeCars(String modelType, VehicleInventory vehicle)
    {
        if(modelType == "car")
        {
            Cars.remove(vehicle);
        }
        if(modelType == "pickup")
        {
            Pickup.remove(vehicle);
        }
        if(modelType == "performancecar")
        {
            PerformanceCars.remove(vehicle);
        }
        if(modelType == "Electric")
        {
            ElectricCars.remove(vehicle);
        }
        if(modelType == "MotorCycle")
        {
            MotorCycles.remove(vehicle);
        }
        if(modelType == "MonsterTruck")
        {
            MonsterTrucks.remove(vehicle);
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

    protected int getElectricCarsCount()
    {
        return this.ElectricCars.size();
    }

    protected int getMotorCyclesCount()
    {
        return this.MotorCycles.size();
    }

    protected int getMonsterTrucksCount()
    {
        return this.MonsterTrucks.size();
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
        else if(modelType == "Electric")
        {
            this.costPrice = ThreadLocalRandom.current().nextInt(10000,50000);
        }
        else if(modelType == "MotorCycle")
        {
            this.costPrice = ThreadLocalRandom.current().nextInt(10000,30000);
        }
        else if(modelType == "MonsterTruck")
        {
            this.costPrice = ThreadLocalRandom.current().nextInt(30000,50000);
        }
        
    }

    protected void assignCarBonus ()
    {
        this.saleBonus = (0.1)*this.sellingPrice;
        this.washBonus = (0.05)*this.sellingPrice;
        this.repairBonus = (0.15)*this.sellingPrice;
        this.winningBonus = (0.02)*this.sellingPrice;
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

    public double getSellingPrice()
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

        for(VehicleInventory electric: ElectricCars){
            if(electric.cleanliness == "clean"){
                cleanCars.add(electric);
            }
        }

        for(VehicleInventory truck: MonsterTrucks){
            if(truck.cleanliness == "clean"){
                cleanCars.add(truck);
            }
        }

        for(VehicleInventory cycle: MotorCycles){
            if(cycle.cleanliness == "clean"){
                cleanCars.add(cycle);
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

        for(VehicleInventory electric: ElectricCars){
            if(electric.cleanliness == "dirty"){
                dirtyCars.add(electric);
            }
        }

        for(VehicleInventory truck: MonsterTrucks){
            if(truck.cleanliness == "dirty"){
                dirtyCars.add(truck);
            }
        }

        for(VehicleInventory cycle: MotorCycles){
            if(cycle.cleanliness == "dirty"){
                dirtyCars.add(cycle);
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

        for(VehicleInventory electric: ElectricCars){
            if(electric.condition == "Broken"){
                Brokencars.add(electric);
            }
        }

        for(VehicleInventory truck: MonsterTrucks){
            if(truck.condition == "Broken"){
                Brokencars.add(truck);
            }
        }

        for(VehicleInventory cycle: MotorCycles){
            if(cycle.condition == "Broken"){
                Brokencars.add(cycle);
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

        for(VehicleInventory electric: ElectricCars){
            if(electric.condition == "Used"){
                Usedvehicles.add(electric);
            } 
        }

        for(VehicleInventory truck: MonsterTrucks){
            if(truck.condition == "Used"){
                Usedvehicles.add(truck);
            } 
        }

        for(VehicleInventory cycle: MotorCycles){
            if(cycle.condition == "Used"){
                Usedvehicles.add(cycle);
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
        allVehicles.addAll(ElectricCars);
        allVehicles.addAll(MonsterTrucks);
        allVehicles.addAll(MotorCycles);
        return allVehicles;
    }
}