
// ObserverEventInfo is a class that contains the data that is passed to the observer.
// The observer can then use this data to update its internal state.
// Implement the Observer interface in the Observer class.
public class SubscriberEventInfo {
    Double staffRevenueAmount;
    Double fncdRevenueAmount;
    String logMessage;

    public SubscriberEventInfo(Double staffRevenue, Double fncdRevenue, String logMessage) {
        this.staffRevenueAmount = staffRevenue;
        this.fncdRevenueAmount = fncdRevenue;
        this.logMessage = logMessage;
    }
}
