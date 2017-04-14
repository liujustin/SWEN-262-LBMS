package Client.Visitor;

import Network.Command;
/**
 * Created by Ryan on 4/12/2017.
 */
public class Memento {
    private Command state;

    public Memento(Command state) {
        this.state = state;
    }

    public Command getState() {
        return state;
    }

    public void setState(Command state) {
        this.state = state;
    }
}
