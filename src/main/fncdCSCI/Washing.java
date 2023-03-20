
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Washing extends FNCD{

    public WashInterface washmethod;

    public Washing(WashInterface washmethod)
    {
        this.washmethod = washmethod;
    }

    public String wash(String oldcondition)
    {
        return this.washmethod.ops(oldcondition);
    }

    public void operation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        Double wBonus = 0.0;
        String newCondition = "";
        String oldCondition= "";

        ArrayList<VehicleInventory> dirtyVehicles = vehicleAdmin.getDirtyVehicles();
        ArrayList<VehicleInventory> cleanVehicles = vehicleAdmin.getCleanVehicles();
        ArrayList<VehicleInventory> tobeCleanedVehicleInventory = new ArrayList<>();

    
        tobeCleanedVehicleInventory.addAll(dirtyVehicles);
        tobeCleanedVehicleInventory.addAll(cleanVehicles);

        Collections.shuffle(tobeCleanedVehicleInventory);

        // Wash vehicles with interns.
        for(int i=0;i<tobeCleanedVehicleInventory.size(); i++){
            Random randIntern = new Random();
            ManageStaff intern = staffAdmin.internTeam.get(randIntern.nextInt(staffAdmin.internTeam.size()-1));

            // If the vehicle is dirty, wash it.
            if(tobeCleanedVehicleInventory.get(i).cleanliness == "dirty"){
                oldCondition = tobeCleanedVehicleInventory.get(i).cleanliness;
                
                newCondition = this.wash("dirty");
                tobeCleanedVehicleInventory.get(i).cleanliness = newCondition;
            }
            // If the vehicle is clean, wash it.
            else if(tobeCleanedVehicleInventory.get(i).cleanliness == "clean"){
                oldCondition = tobeCleanedVehicleInventory.get(i).cleanliness;

                newCondition = this.wash("clean");
                tobeCleanedVehicleInventory.get(i).cleanliness = newCondition;
            }
            else{
                oldCondition = tobeCleanedVehicleInventory.get(i).cleanliness;
                newCondition = tobeCleanedVehicleInventory.get(i).cleanliness;
                continue;
            }
            
            // If the vehicle is sparkling, assign wash Bonus to it.
            if(newCondition == "sparkling" && oldCondition!= "sparkling"){
                wBonus = tobeCleanedVehicleInventory.get(i).washBonus;
                intern.setBonus(wBonus);
                fncdAdmin.operatingBudget -= wBonus;
                fncdAdmin.pushEventInfoToSubscribers(wBonus,0.0d, null, publisherList);
            }

            // Increment the number of vehicles washed by the intern.
            intern.numVehicleWashed++;

            String washingOutcome = ("Intern " + intern.getName() + " washed vehicle " + 
                                oldCondition + " " +
                                tobeCleanedVehicleInventory.get(i).modelType + " " + 
                                tobeCleanedVehicleInventory.get(i).getID() + " and made it "+ newCondition +
                                " (earned $" + wBonus + " bonus)");
            // Push the washing outcome to the subscribers.
            fncdAdmin.pushEventInfoToSubscribers(0.0d,0.0d, washingOutcome, publisherList);
            
            wBonus = 0.0;
            oldCondition = "";
            newCondition = "";
            
            // If two interns have washed a vehicle, break the loop.
            if(intern.numVehicleWashed == 2){
                intern.numVehicleWashed = 0;
                break;
            }
        }
    }
}

