public class Undercoating extends VehicleAddonDecorator {
    
    public boolean isAddonRequired;
    public Undercoating (AddOnInterface vehicle,boolean option ){
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