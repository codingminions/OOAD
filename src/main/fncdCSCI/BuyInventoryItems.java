
import java.util.ArrayList;

public class BuyInventoryItems implements Command {

    public FNCD fncd;
    public VehicleInventory vehicleAdmin;
    public ManageStaff staffAdmin;
    public ArrayList<Subscriber> publisherList;
    
    public BuyInventoryItems(FNCD fncd, VehicleInventory vehicleAdmin, ManageStaff staffAdmin, ArrayList<Subscriber> publisherList)
    {
        this.fncd = fncd;
        this.vehicleAdmin = vehicleAdmin;
        this.staffAdmin = staffAdmin;
        this.publisherList = publisherList;
    }
    
    public void execute()
    {
        fncd.sellVehicle(this.vehicleAdmin, this.publisherList);
    }
}