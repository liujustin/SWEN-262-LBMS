package Client.Visitor;

import Network.Command;
//FILE::Memento.java
//AUTHOR::Ryan Connors
//DATE::Apr.12.2017
public class Memento {
    private Command state;

    //This class stores the state of an undo/redo command.


    //Getters and setters

    /**
     *
     * @param state
     */
    public Memento(Command state) {
        this.state = state;
    }

    /**
     *
     * @return state
     */
    public Command getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(Command state) {
        this.state = state;
    }
}
