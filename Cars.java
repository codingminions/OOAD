class Cars extends VehicleInventory{

 String carID;

 // Constructor for the Cars class that calls the constructor of the VehicleInventory class.
 public Cars(String ID)
 {
   super("car", ID);
   // Set the car ID.
   assignCarCost("car");
   // Set the car cost.
   recaliberateCarCost();
   assignCarBonus();
 }
}