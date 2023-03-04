import java.util.Random;

// Motorcycles class extends VehicleInventory class used for creating Motorcycles objects.
class Motorcycles extends VehicleInventory {

    String PerformanceCarsID;
    double EngineSize;
   
    public Motorcycles(String ID, ManageStaff staffAdmin)
    {
     // Call the constructor of the VehicleInventory class.
      super("MotorCycle", ID, staffAdmin);
      assignCarCost("MotorCycle");
      recaliberateCarCost();
      assignCarBonus();
      assignEngineSize();
      raceswon = false;
    }

    private void assignEngineSize()
    {
        Random rand = new Random();
        
        double randnum = rand.nextGaussian()*300 + 700;
        this.EngineSize = randnum;
    }

    
   }