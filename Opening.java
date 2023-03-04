import java.util.Random;
import java.util.*;

public class Opening extends FNCD{
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> observerList){
        String openingLog = ("Opening... (current budddget $" + fncdAdmin.operatingBudget + ")");
        fncdAdmin.pushEventInfoToSubscribers(0.0d,0.0d,openingLog, observerList);

        Integer internTeamSize = staffAdmin.internTeam.size();
        Integer salesTeamSize = staffAdmin.salesTeam.size();
        Integer mechTeamSize = staffAdmin.mechTeam.size();
        Integer driverTeamSize = staffAdmin.driverTeam.size();
        
        
        Integer performanceCarsSize = vehicleAdmin.PerformanceCars.size();
        Integer pickupCarsSize = vehicleAdmin.Pickup.size();
        Integer carsSize = vehicleAdmin.Cars.size();
        Integer electricCarsSize = vehicleAdmin.ElectricCars.size();
        Integer monsterTrucksSize = vehicleAdmin.MonsterTrucks.size();
        Integer motorCyclesSize = vehicleAdmin.MotorCycles.size();

        Integer numbering = 1;

        // Hire intern staff if it's less than 3 at the start of the day.
        while(internTeamSize < 3){
            Random rand = new Random();
            String nameIntern = "John"+rand.nextInt(10000);
            ManageStaff newIntern = new Intern(nameIntern);
            staffAdmin.addStaff("Intern", newIntern);
            System.out.println("Hired Inten " + nameIntern);

            internTeamSize = staffAdmin.internTeam.size();
        }
        
        // Hire mech staff if it's less than 3 at the start of the day.
        while(mechTeamSize < 3){
            Random rand = new Random();
            String nameMechanic = "Jack"+rand.nextInt(10000);
            ManageStaff newMechanic = new Mechanics(nameMechanic);
            staffAdmin.addStaff("Mechanics", newMechanic);
            System.out.println("Hired Mechanic "+ nameMechanic);
            mechTeamSize = staffAdmin.mechTeam.size();
        }

        // Hire sales staff if it's less than 3 at the start of the day.
        while(salesTeamSize < 3){
            Random rand = new Random();
            String nameSalePerson = "Michael"+rand.nextInt(10000);
            ManageStaff newSalesPerson = new SalesPeople(nameSalePerson);
            System.out.println("Hired SalesPerson "+ nameSalePerson);
            staffAdmin.addStaff("SalesPeople", newSalesPerson);
            salesTeamSize = staffAdmin.salesTeam.size();
        }
        
        // Hire Driver staff if it's less than 3 at the start of the day.
        while(driverTeamSize < 3){
            Random rand = new Random();
            String driverName = "Max"+rand.nextInt(10000);
            ManageStaff newDriverPerson = new Driver(driverName);
            System.out.println("Hired Driver "+ driverName);
            staffAdmin.addStaff("Driver", newDriverPerson);
            driverTeamSize = staffAdmin.driverTeam.size();
        }

        // Purchase performance cars if it's less than 4 at the start of the day.
        while(performanceCarsSize < 4){
            Random rand = new Random();
            int randnum = rand.nextInt(10000000);
            String ID = "PE" + randnum;

            VehicleInventory perfCar = new PerformanceCars(ID, staffAdmin);
            vehicleAdmin.addCars("performancecar", perfCar);
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
            VehicleInventory pickupCar = new Pickup(ID,staffAdmin);
            vehicleAdmin.addCars("pickup", pickupCar);
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
            VehicleInventory car = new Cars(ID, staffAdmin);
            vehicleAdmin.addCars("car", car);
            // Subtract the cost of the car from the operating budget.  
            fncdAdmin.operatingBudget -= car.costPrice;
            System.out.println("Purchased Used, "+ car.cleanliness +" "+ 
                                car.modelType + " for " + car.costPrice + " cost");
            // Update the size of the cars list.
            carsSize = vehicleAdmin.Cars.size();
        }

        // Purchase cars if it's less than 4 at the start of the day.
        while(electricCarsSize < 4){
            Random rand = new Random();
            int randnum = rand.nextInt(10000000);
            String ID = "TSLA" + randnum;
            VehicleInventory electricCar = new ElectricCars(ID, staffAdmin);
            vehicleAdmin.addCars("Electric", electricCar);
            // Subtract the cost of the car from the operating budget.  
            fncdAdmin.operatingBudget -= electricCar.costPrice;
            System.out.println("Purchased Used, "+ electricCar.cleanliness +" "+ 
            electricCar.modelType + " for " + electricCar.costPrice + " cost");
            // Update the size of the cars list.
            electricCarsSize = vehicleAdmin.ElectricCars.size();
        }

        // Purchase cars if it's less than 4 at the start of the day.
        while(motorCyclesSize< 4){
            Random rand = new Random();
            int randnum = rand.nextInt(10000000);
            String ID = "MC" + randnum;
            VehicleInventory motorCycle = new Motorcycles(ID, staffAdmin);
            vehicleAdmin.addCars("MotorCycle", motorCycle);
            // Subtract the cost of the motor cycle from the operating budget.  
            fncdAdmin.operatingBudget -= motorCycle.costPrice;
            System.out.println("Purchased Used, "+ motorCycle.cleanliness +" "+ 
                                motorCycle.modelType + " for " + motorCycle.costPrice + " cost");
            // Update the size of the cars list.
            motorCyclesSize = vehicleAdmin.MotorCycles.size();
        }

        // Purchase cars if it's less than 4 at the start of the day.
        while(monsterTrucksSize < 4){
            String ID = "BigFoot " + String.valueOf(numbering);
            VehicleInventory monsterTruck = new MonsterTrucks(ID, staffAdmin);
            vehicleAdmin.addCars("MonsterTruck", monsterTruck);
            // Subtract the cost of the car from the operating budget.  
            fncdAdmin.operatingBudget -= monsterTruck.costPrice;
            System.out.println("Purchased Used, "+ monsterTruck.cleanliness +" "+ 
                                monsterTruck.modelType + " for " + monsterTruck.costPrice + " cost");
            // Update the size of the cars list.
            monsterTrucksSize = vehicleAdmin.MonsterTrucks.size();
            numbering++;
        }
    }
}
