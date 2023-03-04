import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// Logger class that implements the Subscriber interface and overrides the sendEventDataToSubsciber() method.
// This class is used to log the events that occur during the simulation.
// The log file is created in the same directory as the project.
public class Logger implements Subscriber {
    public String simulatedDayLogFile;

    public Logger(int day) throws FileNotFoundException {
        this.simulatedDayLogFile = "Logger-"+day+".txt";
        // reference: https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
        PrintStream out = new PrintStream(new FileOutputStream(simulatedDayLogFile));
        System.setOut(out);
    }

    // sendEventDataToSubsciber method to send the event data to the subscriber.
    public void sendEventDataToSubsciber(SubscriberEventInfo subscriberEventInfo) {
        if(subscriberEventInfo.logMessage != null) {
            System.out.println(subscriberEventInfo.logMessage);
        }
    }
}
