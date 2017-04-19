package Client.Visitor;

import Books.*;
import Network.Command;

import java.util.ArrayList;

//FILE::Undo_Command.java
//AUTHOR::Ryan Connors
//DATE::Apr.12.2017
public class Undo_Command implements Command{
    private Memento command;


    // This command executes redo commands which got its state in the memento class

    public Undo_Command() {
        this.command = UndoRedoCaretaker.getCaretaker().getUndoStack().pop();
    }
    @Override
    public String execute()
    {
        this.command.getState().execute();
        return "undo,success;";
    }

}
