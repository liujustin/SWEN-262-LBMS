package Client.Visitor;

import Network.Command;
//FILE::Memento.java
//AUTHOR::Ryan Connors
//DATE::Apr.12.2017
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
