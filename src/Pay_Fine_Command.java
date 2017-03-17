/**
 * Created by adamn on 3/2/2017.
 */
public class Pay_Fine_Command implements Command {
    private Book_Loan b;

    public Pay_Fine_Command(Book_Loan b){
        this.b = b;
    }

    public void execute() {
        //this.b.Pay_Fine();
    }
}
