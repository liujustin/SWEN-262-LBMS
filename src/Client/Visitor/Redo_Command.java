package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.Redo_Command.java
//AUTHOR::Ryan Connors
//DATE::Apr.15.2017
public class Redo_Command implements Command
{
    private Memento command;

    // This command executes redo commands which got its state in the memento class

    public Redo_Command() {
        this.command = UndoRedoCaretaker.getCaretaker().getRedoStack().pop();
    }

    @Override
    public String execute()
    {
        this.command.getState().execute();
        return "redo,success";
    }

}
