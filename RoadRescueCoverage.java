// RoadRescueCoverage is a decorator class that extends VehicleAddonDecorator.
public class RoadRescueCoverage extends VehicleAddonDecorator {
     
    public boolean isAddonRequired;
    // Constructor for the RoadRescueCoverage class that calls the constructor of the VehicleAddonDecorator class.
    public RoadRescueCoverage (AddOnInterface vehicle, boolean option){
       super(vehicle);
       isAddonRequired = option;
    }
 
    public double getSellingPriceIfRequired(){
        return (this.vehicle.getSellingPrice()*1.02);
    }

    public double getSellingPrice()
    {
        if(isAddonRequired == true)
        {  
            return getSellingPriceIfRequired();
        }
        else
        {
            return super.getSellingPrice();
        }
    }
}