package Client.Visitor;

import Books.*;
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
        if (this.command.getState() instanceof Borrow_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof Return_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof Begin_Visit_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof  End_Visit_Command) {
            this.command.getState().execute();
        }
        else if (this.command.getState() instanceof Book_Purchase_Command) {
            //undo book purchase
        }
        else {
            this.command.getState().execute();
        }
        return "undo,success";
    }

}
