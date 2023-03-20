
// SatelliteRadion is a decorator class that extends VehicleAddonDecorator.
public class SatelliteRadio extends VehicleAddonDecorator {
     
    boolean isAddonRequired;
    // Constructor for the SatelliteRadio class that calls the constructor of the VehicleAddonDecorator class.
    public SatelliteRadio (AddOnInterface vehicle, boolean option){
       super(vehicle);
       isAddonRequired = option;
    }
 
    public double getSellingPriceIfRequired(){
        return (this.vehicle.getSellingPrice()*1.05);
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