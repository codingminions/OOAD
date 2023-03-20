// staffFactory class is used to create staff objects based on the staff type.
public class staffFactory{

    public ManageStaff getStaff( String stafftype , String staffname )
    {
        if(stafftype == null)
        {
            return null;
        }
        else if(stafftype.equalsIgnoreCase("intern"))
        {
            return new Intern(staffname); 
        }
        else if(stafftype.equalsIgnoreCase("mechanic"))
        {
            return new Mechanics(staffname);
        }
        else if(stafftype.equalsIgnoreCase("salesperson"))
        {
            return new SalesPeople(staffname);
        }
        else if(stafftype.equalsIgnoreCase("driver"))
        {
            return new Driver(staffname);
        }

        return null;
    }
}