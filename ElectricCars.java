import java.util.concurrent.ThreadLocalRandom;

// ElectricCars class extends VehicleInventory class used for creating ElectricCars objects.
class ElectricCars extends VehicleInventory {

    String PerformanceCarsID;
    double range;
   
    public ElectricCars(String ID, ManageStaff staffAdmin)
    {
     // Call the constructor of the VehicleInventory class.
      super("Electric", ID, staffAdmin);
      assignCarCost("Electric");
      assignRange();
      recaliberateCarCost();
      assignCarBonus();
    }

    // Assign the range of the car based on the cost price of the car.
    private void assignRange()
    {
        this.range = this.costPrice = ThreadLocalRandom.current().nextInt(60,400);
    }

   }