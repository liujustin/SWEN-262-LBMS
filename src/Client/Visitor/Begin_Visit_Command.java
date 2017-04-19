package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.Begin_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

public class Begin_Visit_Command implements Command {
    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
    private Long visitorID;
    private boolean isUndo;

    /**
     *
     * @param visitorID
     * @param isUndo
     */
    public Begin_Visit_Command(Long visitorID,boolean isUndo)
    {
        this.visitorID = visitorID;
        this.isUndo = isUndo;
    }
    @Override
    public String execute()
    {
        try
        {
            if (this.isUndo)
            { // if user undo's function it would add an end command to a redo stack
                Visitor_Operations.bVisit = false;
                End_Visit_Command e = new End_Visit_Command(this.visitorID,false);
                Memento m = new Memento(e);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else
            { // if user undo's function it would add an end command to a undo stack
                End_Visit_Command e = new End_Visit_Command(this.visitorID,true);
                Memento m = new Memento(e);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            Visitor_Operations.bVisit = true;
            return visitorKeeper.beginVisit(this.visitorID);
        } catch (Exception e)
        {
            return e.getMessage();
        }
    }

}
