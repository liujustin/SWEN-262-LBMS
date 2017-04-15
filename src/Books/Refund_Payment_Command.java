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

    /**
     *
     * @param visitorID
     * @param amount
     */
    public Refund_Payment_Command(Long visitorID, Double amount){
        this.visitorID = visitorID;
        this.amount = amount;
    }

    @Override
    public String execute() {
        try {
            visitorKeeper.getVisitorRegistry().get(this.visitorID).setBalance
                    (visitorKeeper.getVisitorRegistry().get(this.visitorID).getBalance()- this.amount);

            Pay_Fine_Command p = new Pay_Fine_Command(this.visitorID,this.amount);
            Memento m = new Memento(p);

            UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
