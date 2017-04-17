package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.Begin_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017
public class Begin_Visit_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;
    private boolean isUndo;

    public Begin_Visit_Command(Long visitorID,boolean isUndo){
        this.visitorID = visitorID;
        this.isUndo = isUndo;
    }

    @Override
    public String execute() {
        try {
            if (this.isUndo) {
                End_Visit_Command e = new End_Visit_Command(this.visitorID,false);
                Memento m = new Memento(e);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                End_Visit_Command e = new End_Visit_Command(this.visitorID,true);
                Memento m = new Memento(e);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            visitorKeeper.beginVisit(this.visitorID);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

}
