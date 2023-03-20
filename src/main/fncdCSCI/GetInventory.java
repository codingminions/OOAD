public class GetInventory implements Command {

    public FNCD fncd;
    public VehicleInventory vehicleAdmin;
    public ManageStaff staffAdmin;
    
    public GetInventory(FNCD fncd, VehicleInventory vehicleAdmin, ManageStaff staffAdmin)
    {
        this.fncd = fncd;
        this.vehicleAdmin = vehicleAdmin;
        this.staffAdmin = staffAdmin;
    }
    
    public void execute()
    {
        // Call the queryForStoreInventory method of the FNCD class.
        fncd.queryForStoreInventory(this.vehicleAdmin);
    }
   
}