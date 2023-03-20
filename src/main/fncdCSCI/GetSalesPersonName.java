
public class GetSalesPersonName implements Command {

    public FNCD fncd;
    public VehicleInventory vehicleAdmin;
    public ManageStaff staffAdmin;
    
    public GetSalesPersonName(FNCD fncd, VehicleInventory vehicleAdmin, ManageStaff staffAdmin)
    {
        this.fncd = fncd;
        this.vehicleAdmin = vehicleAdmin;
        this.staffAdmin = staffAdmin;
    }
    
    public void execute()
    {
        fncd.querySalesPersonName(this.staffAdmin);
    }
   
}