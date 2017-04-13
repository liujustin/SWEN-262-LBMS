package Client.Visitor;

import Network.Command;

/**
 * Created by Ryan on 4/12/2017.
 */
public class Redo_Command {
    private Memento command;

    public Redo_Command() {
        this.command = UndoRedoCaretaker.getCaretaker().getRedoStack().pop();
    }

    public String execute() {
        this.command.getState().execute();
        return "";
    }
}
