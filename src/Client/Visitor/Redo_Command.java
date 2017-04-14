package Client.Visitor;

import Books.*;
import Network.Command;

/**
 * Created by Ryan on 4/12/2017.
 */
public class Redo_Command implements Command {
    private Memento command;

    public Redo_Command() {
        this.command = UndoRedoCaretaker.getCaretaker().getRedoStack().pop();
    }

    public String execute() {
        if (this.command.getState() instanceof Redo_Borrow_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof Return_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof Redo_Begin_Visit_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof  Redo_End_Visit_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof Book_Purchase_Command) {
            //undo book purchase
        }
        else {
            this.command.getState().execute();
        }
        return "redo,success";
    }
}
