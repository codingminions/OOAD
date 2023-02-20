import java.util.Random;

// Example of Inheritance: Opening class inherits from the FNCD class.
public class Opening extends FNCD{
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        System.out.println("Opening... (current budddget $" + fncdAdmin.operatingBudget + ")");

        Integer internTeamSize = staffAdmin.internTeam.size();
        Integer salesTeamSize = staffAdmin.salesTeam.size();
        Integer mechTeamSize = staffAdmin.mechTeam.size();
        
        
        Integer performanceCarsSize = vehicleAdmin.PerformanceCars.size();
        Integer pickupCarsSize = vehicleAdmin.Pickup.size();
        Integer carsSize = vehicleAdmin.Cars.size();

        // Hire intern staff if it's less than 3 at the start of the day.
        while(internTeamSize < 3){
            Random rand = new Random();
            String nameIntern = "John"+rand.nextInt(10000);
            ManageStaff newIntern = new Intern(nameIntern);
            staffAdmin.addStaff("Intern", newIntern, null, null);
            System.out.println("Hired Inten " + nameIntern);
            internTeamSize = staffAdmin.internTeam.size();
        }
        
        // Hire mech staff if it's less than 3 at the start of the day.
        while(mechTeamSize < 3){
            Random rand = new Random();
            String nameMechanic = "Jack"+rand.nextInt(10000);
            ManageStaff newMechanic = new Mechanics(nameMechanic);
            staffAdmin.addStaff("Mechanics", null, newMechanic, null);
            System.out.println("Hired Mechanic "+ nameMechanic);
            mechTeamSize = staffAdmin.mechTeam.size();
        }

        // Hire sales staff if it's less than 3 at the start of the day.
        while(salesTeamSize < 3){
            Random rand = new Random();
            String nameSalePerson = "Michael"+rand.nextInt(10000);
            ManageStaff newSalesPerson = new SalesPeople(nameSalePerson);
            System.out.println("Hired SalesPerson "+ nameSalePerson);
            staffAdmin.addStaff("SalesPeople", null, null, newSalesPerson);
            salesTeamSize = staffAdmin.salesTeam.size();
        }
        
        // Purchase performance cars if it's less than 4 at the start of the day.
        while(performanceCarsSize < 4){
            Random rand = new Random();
            int randnum = rand.nextInt(10000000);
            String ID = "PE" + randnum;

            VehicleInventory perfCar = new PerformanceCars(ID);
            vehicleAdmin.addCars("performancecar", null, null, perfCar);
            // Subtract the cost of the car from the operating budget.
            fncdAdmin.operatingBudget -= perfCar.costPrice;
            System.out.println("Purchased Used, "+ perfCar.cleanliness +" "+ 
                                perfCar.modelType + " for " + perfCar.costPrice + " cost");
            // Update the size of the performance cars list.
            performanceCarsSize = vehicleAdmin.PerformanceCars.size();
        }

        // Purchase pickup cars if it's less than 4 at the start of the day.
        while(pickupCarsSize < 4){
            Random rand = new Random();
            int randnum = rand.nextInt(10000000);
            String ID = "PU" + randnum;
            VehicleInventory pickupCar = new Pickup(ID);
            vehicleAdmin.addCars("pickup", null, pickupCar, null);
            // Subtract the cost of the car from the operating budget.
            fncdAdmin.operatingBudget -= pickupCar.costPrice;
            System.out.println("Purchased Used, "+ pickupCar.cleanliness +" "+ 
                                pickupCar.modelType + " for " + pickupCar.costPrice + " cost");
            // Update the size of the pickup cars list.
            pickupCarsSize = vehicleAdmin.Pickup.size();
        }

        // Purchase cars if it's less than 4 at the start of the day.
        while(carsSize < 4){
            Random rand = new Random();
            int randnum = rand.nextInt(10000000);
            String ID = "CA" + randnum;
            VehicleInventory car = new Cars(ID);
            vehicleAdmin.addCars("car", car, null, null);
            // Subtract the cost of the car from the operating budget.  
            fncdAdmin.operatingBudget -= car.costPrice;
            System.out.println("Purchased Used, "+ car.cleanliness +" "+ 
                                car.modelType + " for " + car.costPrice + " cost");
            // Update the size of the cars list.
            carsSize = vehicleAdmin.Cars.size();
        }
    }
}
