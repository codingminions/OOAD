
public class Ambulances extends VehicleInventory {

    String AmbulanceID;
   
    public Ambulances(String ID, ManageStaff staffAdmin)
    {
     // Call the constructor of the VehicleInventory class.
      super("Ambulance", ID, staffAdmin);
      assignCarCost("Ambulance");
      recaliberateCarCost();
      assignCarBonus();
    }
}
