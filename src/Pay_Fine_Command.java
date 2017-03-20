/**
 * Created by adamn on 3/2/2017.
 */
public class Pay_Fine_Command implements Command {
    private Visitor v;
    private Double amount;

    public Pay_Fine_Command(Visitor v, Double amount){
        this.v = v;
        this.amount = amount;
    }

    public void execute() {
        //pay fine goes here
    }
}
