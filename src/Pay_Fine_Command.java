//FILE::Pay_Fine_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017

public class Pay_Fine_Command implements Command {

    private long visitorID;
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
    public void execute() {
        try {
            Main.vk.payFine(this.visitorID,this.amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
