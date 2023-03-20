
// Tracker class implements Subscriber interface.
// Tracker class is a subscriber of the Publisher.
// Tracker class is notified of events by the Publisher.
public class Tracker implements Subscriber {
    Double staffTotalRevenue = 0.0;
    Double fncdTotalRevenue = 0.0;
    int simulationDay;

    // Singleton Pattern: eager implementation of singleton object.
    private static Tracker trackerInstance = null;

    public static Tracker getTrackerInstance() {
        if(trackerInstance == null)
            trackerInstance = new Tracker();
        return trackerInstance;
    }

    public void setSimulationDay(int simulationDay) {
        this.simulationDay = simulationDay;
    }

    public void clearTrackerData(){
        this.staffTotalRevenue = 0.0;
        this.fncdTotalRevenue = 0.0;
        this.simulationDay = 1;
    }

    // sendEventDataToSubsciber method is called by the Publisher.
    // It is called when the Publisher notifies the Tracker of an event.
    @Override
    public void sendEventDataToSubsciber(SubscriberEventInfo subscriberEventInfo) {
        this.staffTotalRevenue += subscriberEventInfo.staffRevenueAmount;
        this.fncdTotalRevenue += subscriberEventInfo.fncdRevenueAmount;
    }

    // This method is not used in this class.
    // This method is implemented to satisfy the Subscriber interface.
    public void printCumulativeData() {
        System.out.println("Tracker : Day"+ simulationDay);
        System.out.println("Total money earned by all Staff : $" + this.staffTotalRevenue);
        System.out.println("Total money earned by the FNCD : $" + this.fncdTotalRevenue);
    }
}
