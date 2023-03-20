
// MonsterTrucks class extends VehicleInventory class used for creating MonsterTrucks objects.
class MonsterTrucks extends VehicleInventory {

    String PerformanceCarsID;
   
    public MonsterTrucks(String ID, ManageStaff staffAdmin)
    {
     // Call the constructor of the VehicleInventory class.
      super("MonsterTruck", ID, staffAdmin);
      assignCarCost("MonsterTruck");
      recaliberateCarCost();
      assignCarBonus();
      raceswon = false;
    }

   }