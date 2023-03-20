// Invoker class for Command Pattern implementation with take and execute methods.
public class Invoker {

    private Command command;

    public void take(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
