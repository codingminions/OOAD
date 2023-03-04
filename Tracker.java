// Tracker class implements Subscriber interface.
// Tracker class is a subscriber of the Publisher.
// Tracker class is notified of events by the Publisher.
public class Tracker implements Subscriber {
    Double staffTotalRevenue = 0.0;
    Double fncdTotalRevenue = 0.0;
    int simulationDay;

    public Tracker(int simulationDay) {
        this.simulationDay = simulationDay;
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
