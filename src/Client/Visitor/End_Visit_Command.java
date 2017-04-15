package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.End_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017
public class End_Visit_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}