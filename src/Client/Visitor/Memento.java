package Client.Visitor;

/**
 * Created by Ryan on 4/12/2017.
 */
public class Memento {
    private Object state;

    public Memento(Object state) {
        this.state = state;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }
}
