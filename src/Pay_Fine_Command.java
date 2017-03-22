//FILE::Book_Loan.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017

public class Pay_Fine_Command implements Command {

    private Visitor v;
    private Double amount;

    /**
     *
     * @param v
     * @param amount
     */
    public Pay_Fine_Command(Visitor v, Double amount){
        this.v = v;
        this.amount = amount;
    }

    @Override
    public void execute() {
        //pay fine goes here
    }
}
