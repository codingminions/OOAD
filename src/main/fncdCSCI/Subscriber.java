
// Subscribers are notified of events by the Publisher.
// The Publisher calls the send method of the Subscriber.
// The SubscriberEventInfo object contains information about the event.
public interface Subscriber {
    public void sendEventDataToSubsciber(SubscriberEventInfo subscriberEventInfo);
}
