// Pickup Class that extends VehicleInventory used for creating Pickup objects.
class Pickup extends VehicleInventory{
 
 String PickupID;

 public Pickup(String ID, ManageStaff staffAdmin)
 {
   super("pickup", ID, staffAdmin);
   assignCarCost("pickup");
   recaliberateCarCost();
   assignCarBonus();
   raceswon = false;
 }

}