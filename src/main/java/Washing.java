import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Washing extends FNCD{
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        System.out.println("Washing..."); 

        Integer numIntern = 0;
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
                String[] CleanlinessArray = {"sparkling","clean"};
                double[] Probabilities = {0.10, 0.80};

                Random rand = new Random();

                double randNum = rand.nextDouble();
                double Probsum = 0.0;
                int selectedIndex = 0;

                for (int j = 0; j < Probabilities.length; j++) {
                    Probsum += Probabilities[j];
                    if (randNum <= Probsum) {
                        selectedIndex = j;
                        break;
                    }
                }

                newCondition = CleanlinessArray[selectedIndex];
                tobeCleanedVehicleInventory.get(i).cleanliness = CleanlinessArray[selectedIndex];
            }
            // If the vehicle is clean, wash it.
            else if(tobeCleanedVehicleInventory.get(i).cleanliness == "clean"){
                oldCondition = tobeCleanedVehicleInventory.get(i).cleanliness;
                String[] CleanlinessArray = {"sparkling","dirty"};
                double[] Probabilities = {0.30, 0.05};

                Random rand = new Random();

                double randNum = rand.nextDouble();
                double Probsum = 0.0;
                int selectedIndex = 0;

                for (int j = 0; j < Probabilities.length; j++) {
                    Probsum += Probabilities[j];
                    if (randNum <= Probsum) {
                        selectedIndex = j;
                        break;
                    }
                }

                newCondition = CleanlinessArray[selectedIndex];
                tobeCleanedVehicleInventory.get(i).cleanliness = CleanlinessArray[selectedIndex];
            }
            else{
                oldCondition = tobeCleanedVehicleInventory.get(i).cleanliness;
                newCondition = tobeCleanedVehicleInventory.get(i).cleanliness;
                continue;
            }
            
            // If the vehicle is sparkling, assign wash Bonus to it.
            if(newCondition == "sparkling"){
                wBonus = tobeCleanedVehicleInventory.get(i).washBonus;
                intern.setBonus(wBonus);
                fncdAdmin.operatingBudget -= wBonus;
            }

            numIntern ++;

            System.out.println("Intern " + intern.getName() + " washed vehicle " + 
                                oldCondition + " " +
                                tobeCleanedVehicleInventory.get(i).modelType + " " + 
                                tobeCleanedVehicleInventory.get(i).getID() + " and made it "+ newCondition +
                                " (earned $" + wBonus + " bonus)");

            wBonus = 0.0;
            oldCondition = "";
            newCondition = "";
            
            // If two interns have washed a vehicle, break the loop.
            if(numIntern == 2){
                break;
            }
        }
    }
}