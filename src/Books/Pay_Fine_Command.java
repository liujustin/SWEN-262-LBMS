package Books;//FILE::Books.Pay_Fine_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017

import Client.Visitor.LBMS_VisitorKeeper;
import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;
import Network.Main;

public class Pay_Fine_Command implements Command {

    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;
    private double amount;
    private boolean isUndo;

    /**
     *
     * @param visitorID
     * @param amount
     */
    public Pay_Fine_Command(Double amount,Long visitorID,boolean isUndo){
        this.visitorID = visitorID;
        this.amount = amount;
        this.isUndo = isUndo;
    }


    @Override
    public String execute() {
        try {
            if (this.isUndo) {
                Refund_Payment_Command r = new Refund_Payment_Command(this.amount,this.visitorID,false);
                Memento m = new Memento(r);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Refund_Payment_Command r = new Refund_Payment_Command(this.amount,this.visitorID,true);
                Memento m = new Memento(r);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            visitorKeeper.payFine(this.amount,this.visitorID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
