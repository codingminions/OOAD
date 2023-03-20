
// ExtendedWarranty class is a decorator class that extends VehicleAddonDecorator class.
public class ExtendedWarranty extends VehicleAddonDecorator {
    
    public boolean isAddonRequired;
    // Constructor for the ExtendedWarranty class that calls the constructor of the VehicleAddonDecorator class.
    public ExtendedWarranty (AddOnInterface vehicle, boolean option){
       super(vehicle);
       isAddonRequired = option;
    }
 
    public double getSellingPriceIfRequired(){
        return this.vehicle.getSellingPrice()*1.2;
    }

    // Get the selling price of the car.
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