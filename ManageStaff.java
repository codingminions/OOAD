
import java.util.ArrayList;

// ManageStaff class is used to manage staff details such as name, salary, bonus, etc.
public class ManageStaff{
    String name;
    Integer TotalDaysWorked =0;
    Double dailySalaryRate;
    Double TotalSalary=0.0;
    Boolean TerminationStatus=false;
    Double bonusRate;
    Double bonus=0.0;
    String staffType;
    Integer numVehicleSold = 0;

    public ArrayList<ManageStaff> internTeam = new ArrayList<>();
    public ArrayList<ManageStaff> mechTeam = new ArrayList<>();
    public ArrayList<ManageStaff> salesTeam = new ArrayList<>();

    // Constructor for the ManageStaff class that sets the name, daily salary rate, bonus rate, and staff type.
    public ManageStaff(){
        this.name = "random";
        this.dailySalaryRate = 0.0;
        this.bonusRate = 0.0;
        this.staffType = "Intern";
    }

    // Constructor for the ManageStaff class that sets the name, daily salary rate, bonus rate, and staff type.
    public ManageStaff(String name, Double dailySalaryRate, Double bonusRate, String staffType){
        this.name = name;
        this.dailySalaryRate = dailySalaryRate;
        this.bonusRate = bonusRate;
        this.staffType = staffType;
    }

    // Add staff to the staff list based on the staff type.
    public void addStaff(String staffType, ManageStaff intern, ManageStaff mech, ManageStaff saleperson){
        if (staffType == "") {
            System.out.println("Please enter a staff type");
            System.exit(0);
        }

        if (staffType == "Intern"){
            this.staffType = "Intern";
            internTeam.add(intern);
        }
        else if (staffType == "Mechanics"){
            this.staffType = "Mechanics";
            mechTeam.add(mech);
        }
        else if (staffType == "SalesPeople"){
            this.staffType = "SalesPeople";
            salesTeam.add(saleperson);
        }
    }

    // Remove staff from the staff list based on the staff type.
    public void removeStaff(String staffType, String id){
        if (staffType == "Intern"){
            for (ManageStaff intern : internTeam){
                if (intern.name == id){
                    internTeam.remove(intern);
                }
            }
        }
        else if (staffType == "Mechanics"){
            for (ManageStaff mech : mechTeam){
                if (mech.name == id){
                    mechTeam.remove(mech);
                }
            }
        }
        else if (staffType == "SalesPeople"){
            for (ManageStaff saleperson : salesTeam){
                if (saleperson.name == id){
                    salesTeam.remove(saleperson);
                }
            }
        }
    }

    // Set the total days worked for the staff.
    public String getName(){
        return this.name;
    }

    // Set the Total bonus earned by the staff.
    public void setBonus(Double bonus){
        this.bonus = bonus;
    }

    // Set the Total Salary earned by the staff.
    public void setTotalSalary(){
        Double bonus = this.bonus;
        this.TotalSalary = this.TotalDaysWorked * this.dailySalaryRate + bonus;
    }

    public void printStaff(){
        System.out.println("Interns: " + internTeam.toString());
        System.out.println("Mechanics: " + mechTeam.toString());
        System.out.println("SalesPeople: " + salesTeam.toString());
    }

    // Print the staff list based on the staff type.
    public void printStaff(String staffType){
        if (staffType == "Intern"){
            System.out.println("Interns: " + internTeam.toString());
        }
        else if (staffType == "Mechanics"){
            System.out.println("Mechanics: " + mechTeam.toString());
        }
        else if (staffType == "SalesPeople"){
            System.out.println("SalesPeople: " + salesTeam.toString());
        }
    }

    // Print the total salary budget for the staff.
    public Double getTotalSalaryBudget(){
        Double totalSalaryBudget = 0.0;
        for (ManageStaff intern : internTeam){
            totalSalaryBudget += intern.TotalSalary;
        }
        for (ManageStaff mech : mechTeam){
            totalSalaryBudget += mech.TotalSalary;
        }
        for (ManageStaff saleperson : salesTeam){
            totalSalaryBudget += saleperson.TotalSalary;
        }
        return totalSalaryBudget;
    }
}