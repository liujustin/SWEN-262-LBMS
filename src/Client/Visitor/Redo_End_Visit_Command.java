package Client.Visitor;

import Network.Command;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Redo_End_Visit_Command implements Command{
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;

    /**
     *
     * @param visitorID
     */
    public Redo_End_Visit_Command(Long visitorID){
        this.visitorID = visitorID;
    }

    @Override
    public String execute() {
        try {
            Begin_Visit_Command b = new Begin_Visit_Command(this.visitorID);
            Memento m = new Memento(b);
            UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            visitorKeeper.endVisit(this.visitorID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
