
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// Racing class that extends the FNCD class and implements the RaceInterface interface.
public class Racing extends FNCD{
    
    // RaceInterface interface that has the simulateRace() method.
    public String SelectRaceVehicleType()
    {
        String[] RaceVehicle = {"pickup","performancecar","motorcycle","monstertruck"};
        double[] Probabilities = {0.25, 0.25, 0.25, 0.25};

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
        return RaceVehicle[selectedIndex];
    }

    // simulateRace sends 3 FNCD vehicle to the Racing event and returns the winner or the injured driver and broken vehicles.
    // otherwise declares the position achieved by the vehicle.
    public void simulateRace(ArrayList<VehicleInventory> RaceCars, ManageStaff staffAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList)
    {
        
        for(int i =0; i<3; i++)
        {   
            ArrayList<ManageStaff> driversList = staffAdmin.driverTeam;
            Integer size = driversList.size();
            RaceCars.get(i).driver = driversList.get(i%size);

            String vehicleParticipantMessage = "FNCD Vehicle " + RaceCars.get(i).getID() + " is participating in the race" +
                                                " with driver " + RaceCars.get(i).driver.name ;
            // Pushing the event information about the race participation to the subscribers.
            fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, vehicleParticipantMessage, publisherList);

            int temporary_pos = ThreadLocalRandom.current().nextInt(1,20);
            
            if(temporary_pos < 4 && temporary_pos > 0)
            {
                // Increment the races won by the particular vehicle.
                RaceCars.get(i).setRacesWon();
                
                if(RaceCars.get(i).raceswon == false){
                    // Increment the sellingPrice of the vehicle by 10%.
                    RaceCars.get(i).setSellingPrice(RaceCars.get(i).getSellingPrice()*1.1);
                }
                
                RaceCars.get(i).driver.numRacesWon++;
                // Calculate the bonus of the driver through a combination of bonusRate and winningBonus associated with the car.
                Double wBonus = (RaceCars.get(i).winningBonus)*(RaceCars.get(i).driver.bonusRate);
                fncdAdmin.operatingBudget -= wBonus;

                String driverLogMessage = "Driver "+ RaceCars.get(i).driver.name + " won their " +
                                          RaceCars.get(i).driver.numRacesWon + " race";  
                // Pusinh the Driver infomation to the subscribers.              
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, driverLogMessage, publisherList);
                // Pushing the winning bonus achieved by the Driver to the subscribers.
                fncdAdmin.pushEventInfoToSubscribers(wBonus, 0.0d, null, publisherList);

                String logMessage = "FNCD Vehicle "+ RaceCars.get(i).getID() + " won its "+
                                     RaceCars.get(i).raceswonCount + " race.";
                // Pushing the winning vehicle information to the subscribers.
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, logMessage, publisherList);

            }
            else if (temporary_pos >= 15 && temporary_pos < 20)
            {
                // Calculate the chances of the driver getting injured.
                boolean isInjured = calculateDriverInjuryChances(RaceCars.get(i).driver);
                if(isInjured){
                    RaceCars.get(i).driver.isInjured = isInjured;
                    String driverInjuryMessage = "Driver " + RaceCars.get(i).driver.name + " is injured";
                    // Adding the injured driver to the departed staff list.
                    // Removing the injured driver from the driver team.
                    fncdAdmin.departedStaff.add(RaceCars.get(i).driver);
                    staffAdmin.driverTeam.remove(RaceCars.get(i).driver);
                    RaceCars.get(i).driver = null;
                    // Pushing the driver injury information to the subscribers.
                    fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, driverInjuryMessage, publisherList);
                }

                String vehicleResultMessage = "FNCD Vehicle " + RaceCars.get(i).getID() + " broke down in the race event";
                // Pushing the vehicle breakdown information to the subscribers.
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, vehicleResultMessage, publisherList);
                RaceCars.get(i).condition = "Broken";
            }
            else
            {
                String raceOutcome = "FNCD Vehicle " + RaceCars.get(i).getID() +  " came in " + temporary_pos + " place";
                // Pushing the vehicle position information to the subscribers.
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, raceOutcome, publisherList);
            }
        }
    }

    // simulateRace2 sends 2 FNCD vehicle to the Racing event and returns the winner or the injured driver and broken vehicles.
    public void simulateRace2(ArrayList<VehicleInventory> RaceCars, ManageStaff staffAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList)
    {
        
        for(int i =0; i<2; i++)
        {
            ArrayList<ManageStaff> driversList = staffAdmin.driverTeam;
            Integer size = driversList.size();
            RaceCars.get(i).driver = driversList.get(i%size);

            String vehicleParticipantMessage = "FNCD Vehicle " + RaceCars.get(i).getID() + " is participating in the race" +
                                                " with driver " + RaceCars.get(i).driver.name ;
            fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, vehicleParticipantMessage, publisherList);
            

            int temporary_pos = ThreadLocalRandom.current().nextInt(1,20);
            
            if(temporary_pos < 4 && temporary_pos > 0)
            {
                RaceCars.get(i).setRacesWon();
                
                if(RaceCars.get(i).raceswon == false){
                    RaceCars.get(i).setSellingPrice(RaceCars.get(i).getSellingPrice()*1.1);
                }
                
                RaceCars.get(i).driver.numRacesWon++;
                Double wBonus = (RaceCars.get(i).winningBonus)*(RaceCars.get(i).driver.bonusRate);
                fncdAdmin.operatingBudget -= wBonus;

                String driverLogMessage = "Driver "+ RaceCars.get(i).driver.name + " won their " +
                                          RaceCars.get(i).driver.numRacesWon + " race";                
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, driverLogMessage, publisherList);
                fncdAdmin.pushEventInfoToSubscribers(wBonus, 0.0d, null, publisherList);

                String logMessage = "FNCD Vehicle "+ RaceCars.get(i).getID() + " won its "+
                                     RaceCars.get(i).raceswonCount + " race.";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, logMessage, publisherList);

            }
            else if (temporary_pos >= 15 && temporary_pos < 20)
            {
                boolean isInjured = calculateDriverInjuryChances(RaceCars.get(i).driver);
                if(isInjured){
                    RaceCars.get(i).driver.isInjured = isInjured;
                    String driverInjuryMessage = "Driver " + RaceCars.get(i).driver.name + " is injured";
                    fncdAdmin.departedStaff.add(RaceCars.get(i).driver);
                    staffAdmin.driverTeam.remove(RaceCars.get(i).driver);
                    RaceCars.get(i).driver = null;
                    fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, driverInjuryMessage, publisherList);
                }
                String vehicleResultMessage = "FNCD Vehicle " + RaceCars.get(i).getID() + " broke down in the race event";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, vehicleResultMessage, publisherList);
                RaceCars.get(i).condition = "Broken";
            }
            else
            {
                String raceOutcome = "FNCD Vehicle " + RaceCars.get(i).getID() +  " came in " + temporary_pos + " place";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, raceOutcome, publisherList);
            }
        }
    }

    // simulateRace1 sends 1 FNCD vehicle to the Racing event and returns the winner or the injured driver and broken vehicles.
    public void simulateRace1(ArrayList<VehicleInventory> RaceCars, ManageStaff staffAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList)
    {
        
        for(int i =0; i<1; i++)
        {   
            ArrayList<ManageStaff> driversList = staffAdmin.driverTeam;
            RaceCars.get(i).driver = driversList.get(0);

            String vehicleParticipantMessage = "FNCD Vehicle " + RaceCars.get(i).getID() + " is participating in the race" +
                                                " with driver " + RaceCars.get(i).driver.name ;

            fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, vehicleParticipantMessage, publisherList);
            

            int temporary_pos = ThreadLocalRandom.current().nextInt(1,20);
            
            if(temporary_pos < 4 && temporary_pos > 0)
            {
                RaceCars.get(i).setRacesWon();
                
                if(RaceCars.get(i).raceswon == false){
                    RaceCars.get(i).setSellingPrice(RaceCars.get(i).getSellingPrice()*1.1);
                }
                
                RaceCars.get(i).driver.numRacesWon++;
                Double wBonus = (RaceCars.get(i).winningBonus)*(RaceCars.get(i).driver.bonusRate);
                fncdAdmin.operatingBudget -= wBonus;

                String driverLogMessage = "Driver "+ RaceCars.get(i).driver.name + " won their " +
                                          RaceCars.get(i).driver.numRacesWon + " race";                
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, driverLogMessage, publisherList);
                fncdAdmin.pushEventInfoToSubscribers(wBonus, 0.0d, null, publisherList);

                String logMessage = "FNCD Vehicle "+ RaceCars.get(i).getID() + " won its "+
                                     RaceCars.get(i).raceswonCount + " race.";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, logMessage, publisherList);

            }
            else if (temporary_pos >= 15 && temporary_pos < 20)
            {  
                boolean isInjured = calculateDriverInjuryChances(RaceCars.get(i).driver);
                if(isInjured){
                    RaceCars.get(i).driver.isInjured = isInjured;
                    String driverInjuryMessage = "Driver " + RaceCars.get(i).driver.name + " is injured";
                    fncdAdmin.departedStaff.add(RaceCars.get(i).driver);
                    staffAdmin.driverTeam.remove(RaceCars.get(i).driver);
                    RaceCars.get(i).driver = null;
                    fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, driverInjuryMessage, publisherList);
                }
                String vehicleResultMessage = "FNCD Vehicle " + RaceCars.get(i).getID() + " broke down in the race event";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, vehicleResultMessage, publisherList);
                RaceCars.get(i).condition = "Broken";
            }
            else
            {
                String raceOutcome = "FNCD Vehicle " + RaceCars.get(i).getID() +  " came in " + temporary_pos + " place";
                fncdAdmin.pushEventInfoToSubscribers(0.0d, 0.0d, raceOutcome, publisherList);
            }
        }
    }


    public boolean calculateDriverInjuryChances(ManageStaff driver)
    {   
        // 30% chance of injury.
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100);
        if(randomNum <= 30)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // ops method is the main method that calls the other racing simulation methods in the class based on the number of vehicles and drivers available.
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin, ArrayList<Subscriber> publisherList)
    {   
        System.out.println("Racing...");

        String RaceVehicleType = SelectRaceVehicleType();

        if(RaceVehicleType == "pickup")
        {
            if(vehicleAdmin.Pickup.size()>=3 && staffAdmin.driverTeam.size()>=3)
            {
                simulateRace(vehicleAdmin.Pickup, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.Pickup.size()==2 && staffAdmin.driverTeam.size()>=2)
            {
                simulateRace2(vehicleAdmin.Pickup, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.Pickup.size()==1 && staffAdmin.driverTeam.size()>=1)
            {
                simulateRace1(vehicleAdmin.Pickup, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.Pickup.size()==0)
            {
                return;
            }
        }

        if(RaceVehicleType == "performancecar")
        {
            if(vehicleAdmin.PerformanceCars.size()>=3 && staffAdmin.driverTeam.size()>=3)
            {
                simulateRace(vehicleAdmin.PerformanceCars, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.PerformanceCars.size()==2 && staffAdmin.driverTeam.size()>=2)
            {
                simulateRace2(vehicleAdmin.PerformanceCars, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.PerformanceCars.size()==1 && staffAdmin.driverTeam.size()>=1)
            {
                simulateRace1(vehicleAdmin.PerformanceCars, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.PerformanceCars.size()==0)
            {
                return;
            }
        }

        if(RaceVehicleType == "motorcycle")
        {
            if(vehicleAdmin.MotorCycles.size()>=3 && staffAdmin.driverTeam.size()>=3)
            {
                simulateRace(vehicleAdmin.MotorCycles, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.MotorCycles.size()==2 && staffAdmin.driverTeam.size()>=2)
            {
                simulateRace2(vehicleAdmin.MotorCycles, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.MotorCycles.size()==1 && staffAdmin.driverTeam.size()>=1)
            {
                simulateRace1(vehicleAdmin.MotorCycles, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.MotorCycles.size()==0)
            {
                return;
            }
        }

        if(RaceVehicleType == "monstertruck")
        {
            if(vehicleAdmin.MonsterTrucks.size()>=3 && staffAdmin.driverTeam.size()>=3)
            {
                simulateRace(vehicleAdmin.MonsterTrucks, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.MonsterTrucks.size()==2 && staffAdmin.driverTeam.size()>=2)
            {
                simulateRace2(vehicleAdmin.MonsterTrucks, staffAdmin, fncdAdmin, publisherList);   
            }
            else if(vehicleAdmin.MonsterTrucks.size()==1 && staffAdmin.driverTeam.size()>=1)
            {
                simulateRace1(vehicleAdmin.MonsterTrucks, staffAdmin, fncdAdmin, publisherList);
            }
            else if(vehicleAdmin.MonsterTrucks.size()==0)
            {
                return;
            }
        }
    }
}