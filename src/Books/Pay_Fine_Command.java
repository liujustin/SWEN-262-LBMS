package Books;//FILE::Books.Pay_Fine_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017

import Client.Visitor.LBMS_VisitorKeeper;
import Network.Command;
import Network.Main;

public class Pay_Fine_Command implements Command {

    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;
    private double amount;

    /**
     *
     * @param visitorID
     * @param amount
     */
    public Pay_Fine_Command(Long visitorID, Double amount){
        this.visitorID = visitorID;
        this.amount = amount;
    }

    @Override
    public String execute() {
        try {
            visitorKeeper.payFine(this.visitorID,this.amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
