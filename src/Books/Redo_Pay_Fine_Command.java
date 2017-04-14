package Books;

import Client.Visitor.LBMS_VisitorKeeper;
import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;
/**
 * Created by Ryan on 4/14/2017.
 */
public class Redo_Pay_Fine_Command implements Command{
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;
    private double amount;

    /**
     *
     * @param visitorID
     * @param amount
     */
    public Redo_Pay_Fine_Command(Long visitorID, Double amount){
        this.visitorID = visitorID;
        this.amount = amount;
    }

    @Override
    public String execute() {
        try {
            Refund_Payment_Command r = new Refund_Payment_Command(this.visitorID,this.amount);
            Memento m = new Memento(r);
            UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            
            visitorKeeper.payFine(this.visitorID,this.amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
