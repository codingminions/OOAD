import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Buyer class
// Buyer has a base chance of buying a vehicle.
// Buyer has a name.
// Buyer has a sales person.
// Buyer has a vehicle desired.
// Buyer has a type (just looking, wants one, needs one).
public class Buyer {
    Integer baseChance;
    String name;
    ManageStaff salesPerson;
    VehicleInventory vehicleDesired;
    String [] buyerType = {"justlooking", "wantsone", "needsone"};

    // Constructor for the Buyer class.
    public Buyer(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Random rand = new Random();
        if(buyerType[rand.nextInt(buyerType.length)] == "justlooking") {
            this.baseChance = 10;
        }
        else if(buyerType[rand.nextInt(buyerType.length)] == "wantsone"){
            this.baseChance = 40;
        }
        else{
            this.baseChance = 70;
        }
        
        int randnum = rand.nextInt(10000000);
        this.name = "buyer" + randnum;

        // Get a random sales person from the sales team.
        ArrayList<ManageStaff> salesPersonList = staffAdmin.salesTeam;
        Collections.shuffle(salesPersonList);
        this.salesPerson = salesPersonList.get(0);

        // Get a random vehicle from the inventory.
        ArrayList<VehicleInventory> allVehicles = vehicleAdmin.getAllVehicles();
        Collections.shuffle(allVehicles);
        this.vehicleDesired = allVehicles.get(0);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSalesPerson(SalesPeople salesPerson){
        this.salesPerson = salesPerson;
    }

    public String getName(){
        return this.name;
    }

    public ManageStaff getSalesPerson(){
        return this.salesPerson;
    }
}
