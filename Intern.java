public class Intern extends ManageStaff{
    // Constructor for the Intern class that calls the constructor of the ManageStaff class.
    public Intern(String name){
        super(name, 100.0, 0.30, "Intern");
    }
    // Get the name of the intern.
    public String getName(){
        return this.name;
    }
}
