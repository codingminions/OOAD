
public class SelectFNCDLocation implements Command{
    FNCD fncd;

    public SelectFNCDLocation(FNCD fncd) {
        this.fncd = fncd;
    }

    @Override
    public void execute() {
        System.out.println("Select FNCD Location");
    }

    public FNCD getFNCD() {
        return fncd;
    }

    public void setFNCD(FNCD fncd) {
        this.fncd = fncd;
    }
}
