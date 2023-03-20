
// VehicleAddonDecorator is an abstract class that implements the AddOnInterface.
public abstract class VehicleAddonDecorator implements AddOnInterface {
    
    protected AddOnInterface vehicle;
 
    public VehicleAddonDecorator(AddOnInterface vehicle){
       this.vehicle = vehicle;
    }
 
    public double getSellingPrice(){
        return this.vehicle.getSellingPrice();
    }	
 }