// Pickup Class that extends VehicleInventory used for creating Pickup objects.
class Pickup extends VehicleInventory{
 
 String PickupID;

 public Pickup(String ID)
 {
   super("pickup", ID);
   assignCarCost("pickup");
   recaliberateCarCost();
   assignCarBonus();
 }
}