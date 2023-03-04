// PerformanceCars class extends VehicleInventory class used for creating PerformanceCars objects.
class PerformanceCars extends VehicleInventory {

 String PerformanceCarsID;

 public PerformanceCars(String ID, ManageStaff staffAdmin)
 {
  // Call the constructor of the VehicleInventory class.
   super("performancecar", ID, staffAdmin);
   assignCarCost("performancecar");
   recaliberateCarCost();
   assignCarBonus();
   raceswon = false;
 }

}