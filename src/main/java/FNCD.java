import java.util.*;

class FNCD {
    Double operatingBudget;
    Date date;
    // ArrayLists to track staff, buyers, and vehicles.
    ArrayList<Buyer> buyerList = new ArrayList<>();
    ArrayList<ManageStaff> departedStaff = new ArrayList<>();
    ArrayList<VehicleInventory> soldInventory = new ArrayList<>();

    public FNCD() {
        this.operatingBudget = 500000.00;
    }

    // Initiate the operations for the day
    public void ops(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        this.date = new Date();
        if(this.getDateDay(date) !=0){
            // Initiate the operations for the day.
            OpeningOperation(staffAdmin, vehicleAdmin, fncdAdmin);
            WashingOperation(staffAdmin, vehicleAdmin, fncdAdmin);
            RepairOperation(staffAdmin, vehicleAdmin, fncdAdmin);
            SalesOperation(staffAdmin, vehicleAdmin, fncdAdmin);
            EndOfDayOperation(staffAdmin, vehicleAdmin, fncdAdmin);
        }
        else
        {
            System.out.println("Closed on Sunday");
            System.exit(0);
        }
    }

    // Get the day of the week.
    public Integer getDateDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    // Initiate the opening operation for the day.
    public void OpeningOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Opening open = new Opening();
        open.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }

    // Initiate the washing operation for the day.
    public void WashingOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Washing wash = new Washing();
        wash.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }

    // Initiate the repair operation for the day.
    public void RepairOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Repair rep = new Repair();
        rep.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }

    // Initiate the sales operation for the day.
    public void SalesOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Selling sale = new Selling();
        sale.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }

    // Initiate the end of day operation for the day.
    public void EndOfDayOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        EndOfDay end = new EndOfDay();
        end.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }

    // Initiate the printing of the report for the day.
    public void PrintReportOperation(ManageStaff staffAdmin, VehicleInventory vehicleAdmin, FNCD fncdAdmin){
        Reporting report = new Reporting();
        report.ops(staffAdmin, vehicleAdmin, fncdAdmin);
    }
}
