

public class GetTime implements Command {

    public FNCD fncd;
    public VehicleInventory vehicleAdmin;
    public ManageStaff staffAdmin;
    
    public GetTime(FNCD fncd, VehicleInventory vehicleAdmin, ManageStaff staffAdmin)
    {
        this.fncd = fncd;
        this.vehicleAdmin = vehicleAdmin;
        this.staffAdmin = staffAdmin;
    }
    
    public void execute()
    {
        fncd.querySalesPersonTime();
    }
}