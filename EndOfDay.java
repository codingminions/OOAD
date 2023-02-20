import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// EndOfDay class.
public class EndOfDay extends FNCD{
    // Constructor for the EndOfDay class that calls the constructor of the FNCD class.
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        incrementTotalNumberOfDaysWorkedForStaff(staffAdmin);
        paySalaryToStaff(staffAdmin);

        ManageStaff salesperson = possibleQuittingStaffDetails(staffAdmin.salesTeam);
        ManageStaff mech = possibleQuittingStaffDetails(staffAdmin.mechTeam);
        ManageStaff intern = possibleQuittingStaffDetails(staffAdmin.internTeam);

        // Track quitting staff.
        trackQuittingStaff(salesperson, staffAdmin, fncdAdmin);
        trackQuittingStaff(mech, staffAdmin, fncdAdmin);
        trackQuittingStaff(intern, staffAdmin, fncdAdmin);
    }

    // Increment the total number of days worked for each staff member.
    public void incrementTotalNumberOfDaysWorkedForStaff(ManageStaff staffAdmin){
        for(int i=0;i<staffAdmin.salesTeam.size();i++){
            staffAdmin.salesTeam.get(i).TotalDaysWorked += 1;
        }

        for(int i=0;i<staffAdmin.mechTeam.size();i++){
            staffAdmin.mechTeam.get(i).TotalDaysWorked += 1;
        }

        for(int i=0;i<staffAdmin.internTeam.size();i++){
            staffAdmin.internTeam.get(i).TotalDaysWorked += 1;
        }
    }

    // Pay salary to staff.
    public void paySalaryToStaff(ManageStaff staffAdmin){
        for(int i=0;i<staffAdmin.salesTeam.size();i++){
            staffAdmin.salesTeam.get(i).setTotalSalary();
        }
        for(int i=0;i<staffAdmin.mechTeam.size();i++){
            staffAdmin.mechTeam.get(i).setTotalSalary();
        }
        for(int i=0;i<staffAdmin.internTeam.size();i++){
            staffAdmin.internTeam.get(i).setTotalSalary();
        }
    }

    // Possible quitting staff details.
    public ManageStaff possibleQuittingStaffDetails(ArrayList<ManageStaff> staff){
        int quitChance = new Random().nextInt(10);
        if(quitChance == 0){
            Collections.shuffle(staff);
            return staff.get(0);
        }
        return null;
    }

    // Track quitting staff.
    public void trackQuittingStaff(ManageStaff staff, ManageStaff staffAdmin, FNCD fncdAdmin){
        if(staff != null)
        {   
            // Add the quitting staff to the departed staff list.
            System.out.println(staff.staffType + " " + staff.name + " has quit the FNCD");
            fncdAdmin.departedStaff.add(staff);
    
            if(staff.staffType == "Intern"){
                staffAdmin.internTeam.remove(staff);
            }
            else {
                // Get a random intern from the intern team.
                Collections.shuffle(staffAdmin.internTeam);
                ManageStaff intern = staffAdmin.internTeam.get(0);
                staffAdmin.internTeam.remove(intern);
                
                System.out.println("Intern " + intern.name + " has been hired to replace " + staff.staffType + " " + staff.name);
                
                // Replace the quitting staff with the intern.
                if(staff.staffType == "Mechanics"){
                    staffAdmin.mechTeam.remove(staff);
                    ManageStaff mech = new Mechanics(intern.getName());
                    staffAdmin.mechTeam.add(mech);
                }
                // Replace the quitting salesperson with the intern.
                if(staff.staffType == "SalesPeople"){
                    staffAdmin.salesTeam.remove(staff);
                    ManageStaff saleperson = new SalesPeople(intern.getName());
                    staffAdmin.salesTeam.add(saleperson);
                }
            }
        }
    }
}