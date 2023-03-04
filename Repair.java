import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Repair extends FNCD {
    // Constructor for the Repair class that calls the constructor of the FNCD class and calls the ops method.
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList){
        ArrayList<VehicleInventory> brokenVehicles = vehicleAdmin.getBrokenVehicles();
        ArrayList<VehicleInventory> usedVehicles = vehicleAdmin.getUsedVehicles();

        ArrayList<VehicleInventory> tobeRepairedVehicleInventory = new ArrayList<>();
        tobeRepairedVehicleInventory.addAll(brokenVehicles);
        tobeRepairedVehicleInventory.addAll(usedVehicles);

        Collections.shuffle(tobeRepairedVehicleInventory);

        Double wBonus = 0.0;
        String oldCondition = " ";
        String newCondition = " ";

        System.out.println("Repairing..."); 
        // Repair broken vehicles and used vehicles with a probability of 0.20 and 0.80 respectively.
        for(int i=0;i<tobeRepairedVehicleInventory.size(); i++){
                Random randMech = new Random();
                ManageStaff mech = staffAdmin.mechTeam.get(randMech.nextInt(staffAdmin.mechTeam.size()-1));

                // If the vehicle is broken, repair it with a probability of 0.80.
                if(tobeRepairedVehicleInventory.get(i).condition == "Broken")
                {
                    oldCondition = tobeRepairedVehicleInventory.get(i).condition;
                    String[] RepairArray = {"Used"};
                    double[] Probabilities = {0.80};
                    Random rand = new Random();

                    double randNum = rand.nextDouble();
                    double Probsum = 0.0;
                    int selectedIndex = -1;

                    for (int j = 0; j < Probabilities.length; j++) {
                        Probsum += Probabilities[j];
                        if (randNum <= Probsum) {
                            selectedIndex = j;
                            break;
                        }
                    }

                    if(selectedIndex == -1){
                        newCondition = "Broken";
                    }
                    else {
                        wBonus = tobeRepairedVehicleInventory.get(i).repairBonus;
                        tobeRepairedVehicleInventory.get(i).sellingPrice = 1.5 * tobeRepairedVehicleInventory.get(i).sellingPrice;
                        // Decrease the operating budget by the repair bonus.
                        fncdAdmin.operatingBudget -= wBonus;
                        newCondition = RepairArray[selectedIndex];
                        tobeRepairedVehicleInventory.get(i).condition = RepairArray[selectedIndex];
                    }

                    if(tobeRepairedVehicleInventory.get(i).cleanliness == "clean"){
                        tobeRepairedVehicleInventory.get(i).cleanliness = "dirty";
                    }
                    else if(tobeRepairedVehicleInventory.get(i).cleanliness == "sparkling"){
                        tobeRepairedVehicleInventory.get(i).cleanliness = "clean";
                    }
                }
                // If the vehicle is used, repair it with the probability of 80%.
                else if(tobeRepairedVehicleInventory.get(i).condition == "Used")
                {
                    oldCondition = tobeRepairedVehicleInventory.get(i).condition;
                    String[] RepairArray = {"LikeNew"};
                    double[] Probabilities = {0.80};
                    Random rand = new Random();

                    double randNum = rand.nextDouble();
                    double Probsum = 0.0;
                    int selectedIndex = -1;

                    for (int j = 0; j < Probabilities.length; j++) {
                        Probsum += Probabilities[j];
                        if (randNum <= Probsum) {
                            selectedIndex = j;
                            break;
                        }
                    }

                    if(selectedIndex == -1){
                        newCondition = "Used";
                    }
                    else {
                        wBonus = tobeRepairedVehicleInventory.get(i).repairBonus;
                        fncdAdmin.pushEventInfoToSubscribers(wBonus,0.0d, null, publisherList);
                        tobeRepairedVehicleInventory.get(i).sellingPrice = 1.25 * tobeRepairedVehicleInventory.get(i).sellingPrice;
                        // Decrease the operating budget by the repair bonus.
                        fncdAdmin.operatingBudget -= wBonus;
                        newCondition = RepairArray[selectedIndex];
                        tobeRepairedVehicleInventory.get(i).condition = RepairArray[selectedIndex];
                    }

                    if(tobeRepairedVehicleInventory.get(i).cleanliness == "clean"){
                        tobeRepairedVehicleInventory.get(i).cleanliness = "dirty";
                    }
                    else if(tobeRepairedVehicleInventory.get(i).cleanliness == "sparkling"){
                        tobeRepairedVehicleInventory.get(i).cleanliness = "clean";
                    }

                }               
            mech.numVehicleServiced++;
            String repairOutcome = ("Mechanic " + mech.getName() + " repaired vehicle " +
                                oldCondition + " "+ 
                                tobeRepairedVehicleInventory.get(i).modelType + " " + 
                                tobeRepairedVehicleInventory.get(i).getID() + " and made it "+ newCondition +
                                " (earned $" + wBonus + " bonus)");
            fncdAdmin.pushEventInfoToSubscribers(0.0d,0.0d, repairOutcome, publisherList);
            wBonus = 0.0;
            oldCondition = " ";
            newCondition = " ";
            // If two vehicles have been repaired by each mechanic, break the loop.
            if(mech.numVehicleServiced == 2){
                mech.numVehicleServiced = 0;
                break;
            }
        }
    }
}