
public class vehicleFactory{

    public VehicleInventory getVehicle( String vehcileType , String vehicleID, ManageStaff staffAdmin )
    {
        if(vehcileType == null)
        {
            return null;
        }
        else if(vehcileType.equalsIgnoreCase("performancecar"))
        {
            return new PerformanceCars(vehicleID, staffAdmin); 
        }
        else if(vehcileType.equalsIgnoreCase("car"))
        {
            return new Cars(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("pickup"))
        {
            return new Pickup(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("electriccar"))
        {
            return new ElectricCars(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("monstertruck"))
        {
            return new MonsterTrucks(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("motorcycle"))
        {
            return new Motorcycles(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("ambulance"))
        {
            return new Ambulances(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("bus"))
        {
            return new Buses(vehicleID, staffAdmin);
        }
        else if(vehcileType.equalsIgnoreCase("skateboard"))
        {
            return new SkateBoards(vehicleID, staffAdmin);
        }

        return null;
    }
}