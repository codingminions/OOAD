
public class SkateBoards extends VehicleInventory {

    String BusID;
   
    public SkateBoards(String ID, ManageStaff staffAdmin)
    {
     // Call the constructor of the VehicleInventory class.
      super("SkateBoard", ID, staffAdmin);
      assignCarCost("SkateBoard");
      recaliberateCarCost();
      assignCarBonus();
    }
}