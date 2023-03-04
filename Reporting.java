import java.util.ArrayList;

public class Reporting {
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        // Print the report at the end of the month.
        toStringStaffReport(staffAdmin.salesTeam);
        System.out.print("\n\n\n");
        toStringStaffReport(staffAdmin.mechTeam);
        System.out.print("\n\n\n");
        toStringStaffReport(staffAdmin.internTeam);
        System.out.print("\n\n\n");
        toStringStaffReport(staffAdmin.driverTeam);
        System.out.print("\n\n\n");

        toStringVechileReport(vehicleAdmin.Cars);
        System.out.print("\n\n\n");
        toStringVechileReport(vehicleAdmin.PerformanceCars);
        System.out.print("\n\n\n");
        toStringVechileReport(vehicleAdmin.Pickup);
        System.out.print("\n\n\n");

        exStaffDetails(fncdAdmin.departedStaff);
        System.out.print("\n\n\n");
        soldVehicleReport(fncdAdmin.soldInventory);
        System.out.print("\n\n\n");

    }
    // Print the Staff report at the end of the month.
    public void toStringStaffReport(ArrayList<ManageStaff> staff)
    {   
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            "Staff Type", "Name", "Total Days Worked", "Total Salary", "Bonus");
        System.out.println();
        for (ManageStaff employee : staff)
        {
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            employee.staffType, employee.getName(),
                            employee.TotalDaysWorked, employee.TotalSalary, 
                            employee.bonus);
        }
    }

    // Print the vehicle Report at the end of the month.
    public void toStringVechileReport(ArrayList<VehicleInventory> vehicles)
    {   
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            "Model Type", "ID", "Cost Price", "Selling Price", "Condition", "Cleanliness", "Status");
        System.out.println();
        for(VehicleInventory vehicle: vehicles)
        {
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            vehicle.modelType, vehicle.getID(),
                            vehicle.costPrice, vehicle.sellingPrice, vehicle.condition,
                            vehicle.cleanliness, "Stock Available");
        }
    }

    // Print the staff report for the staff who left the company.
    public void exStaffDetails(ArrayList<ManageStaff> departedStaff)
    {
        if(departedStaff.size() == 0)
        {
            System.out.println("No staff have left the company.");
        }

        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            "Staff Type", "Name", "Total Days Worked", "Total Salary", "Bonus", "Status");
        System.out.println();
        for (ManageStaff employee : departedStaff)
        {
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            employee.staffType, employee.getName(),
                            employee.TotalDaysWorked, employee.TotalSalary, 
                            employee.bonus, "Working Status: Quit");
        }
    }

    // Print the vehicle report for the vehicles that were sold.
    public void soldVehicleReport(ArrayList<VehicleInventory> soldVehicles)
    {   
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            "Model Type", "ID", "Cost Price", "Selling Price", "Condition", "Cleanliness", "Status");
        System.out.println();
        for(VehicleInventory vehicle: soldVehicles)
        {
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", 
                            vehicle.modelType, vehicle.getID(),
                            vehicle.costPrice, vehicle.sellingPrice, vehicle.condition,
                            vehicle.cleanliness, "Sold");
        }
    }
}
