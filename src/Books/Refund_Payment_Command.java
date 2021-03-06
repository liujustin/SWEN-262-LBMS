package Books;

import Client.Visitor.Visitor_Operations;
import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;

//FILE::Books.Refund_Payment_Command.java
//AUTHOR::Ryan Connors
//DATE::Apr.15.2017

public class Refund_Payment_Command implements Command {

    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
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
                Visitor_Operations.pFine = false;
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
                    (visitorKeeper.getVisitorRegistry().get(this.visitorID).getBalance() + this.amount);
            Visitor_Operations.pFine = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
