package Books;

import Client.Visitor.LBMS_VisitorKeeper;
import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Refund_Payment_Command implements Command {

    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;
    private double amount;
    private boolean isUndo;

    /**
     *
     * @param visitorID
     * @param amount
     */
    public Refund_Payment_Command(Double amount,Long visitorID,boolean isUndo){
        this.visitorID = visitorID;
        this.amount = amount;
        this.isUndo = isUndo;
    }

    @Override
    public String execute() {
        try {
            if (this.isUndo) {
                Pay_Fine_Command p = new Pay_Fine_Command(this.amount,this.visitorID,false);
                Memento m = new Memento(p);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Pay_Fine_Command p = new Pay_Fine_Command(this.amount,this.visitorID,true);
                Memento m = new Memento(p);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            visitorKeeper.getVisitorRegistry().get(this.visitorID).setBalance
                    (visitorKeeper.getVisitorRegistry().get(this.visitorID).getBalance()- this.amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
