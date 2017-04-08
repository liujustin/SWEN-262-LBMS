package Books;//FILE::Books.Pay_Fine_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017

import Network.Command;
import Network.Main;

public class Pay_Fine_Command implements Command {

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
            Main.vk.payFine(this.visitorID,this.amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
