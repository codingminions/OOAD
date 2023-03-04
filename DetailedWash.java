import java.util.Random;

// DetailedWash class that implements the WashInterface interface and overrides the ops() method.
public class DetailedWash implements WashInterface{

    // Constructor for the DetailedWash class.
    public String ops(String oldcondition)
    {
        String newCondition = oldcondition;
        // If the vehicle is dirty, it has a 20% chance of becoming sparkling and a 60% chance of becoming clean.
        if(oldcondition == "dirty")
        {
            String[] CleanlinessArray = {"sparkling","clean"};
            double[] Probabilities = {0.20, 0.60};

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
            newCondition = CleanlinessArray[selectedIndex];
        }
        // If the vehicle is clean, it has a 5% chance of becoming sparkling and a 40% chance of becoming dirty.
        else if (oldcondition == "clean")
        {
            String[] CleanlinessArray = {"sparkling","dirty"};
            double[] Probabilities = {0.05, 0.40};

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
                newCondition = CleanlinessArray[selectedIndex];
        }
        return newCondition;
    }
}