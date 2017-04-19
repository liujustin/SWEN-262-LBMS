package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.End_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017
public class End_Visit_Command implements Command {
    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
    private Long visitorID;
    private boolean isUndo;

    /**
     *
     * @param visitorID
     */
    public End_Visit_Command(Long visitorID,boolean isUndo){
        this.visitorID = visitorID;
        this.isUndo = isUndo;
    }

    @Override
    public String execute() {
        try {
            if (this.isUndo) {
                Visitor_Operations.eVisit = false;
                Begin_Visit_Command b = new Begin_Visit_Command(this.visitorID,false);
                Memento m = new Memento(b);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Begin_Visit_Command b = new Begin_Visit_Command(this.visitorID,true);
                Memento m = new Memento(b);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            visitorKeeper.endVisit(this.visitorID);
            Visitor_Operations.eVisit = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}