package Client.Visitor;

import Network.Command;

/**
 * Created by Ryan on 4/15/2017.
 */
public class Redo_Command implements Command{
    private Memento command;

    public Redo_Command() {
        this.command = UndoRedoCaretaker.getCaretaker().getUndoStack().pop();
    }

    public String execute() {
        this.command.getState().execute();
        return "redo,success";
    }

}
