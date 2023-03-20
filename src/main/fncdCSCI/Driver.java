// Driver class that extends the ManageStaff class and has a boolean variable to check if the driver is injured or not.
public class Driver extends ManageStaff{
    // Constructor for the Driver class that calls the constructor of the ManageStaff class.
    
    boolean isInjured = false;
    
    public Driver(String name){
        super(name, 100.0, 0.30, "Driver");
    }
    // Get the name of the driver.
    public String getName(){
        return this.name;
    }
}