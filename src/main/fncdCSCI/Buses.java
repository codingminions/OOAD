
public class Buses extends VehicleInventory {

    String BusID;
   
    public Buses(String ID, ManageStaff staffAdmin)
    {
     // Call the constructor of the VehicleInventory class.
      super("Bus", ID, staffAdmin);
      assignCarCost("Bus");
      recaliberateCarCost();
      assignCarBonus();
    }
}