package Client.Visitor;

import Network.Command;

/**
 * Created by Ryan on 4/12/2017.
 */
public class Undo_Command implements Command{
    private Memento command;

    public Undo_Command() {
        this.command = UndoRedoCaretaker.getCaretaker().getUndoStack().pop();
    }

    public String execute() {
        this.command.getState().execute();
        return "";
    }

}
