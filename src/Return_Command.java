/**
 * Created by adamn on 3/2/2017.
 */
public class Return_Command implements Command {
    private Visitor v;
    private Book_Loan b;
    private LBMS_BookKeeper sys;

    public Return_Command(Visitor v, Book_Loan b,LBMS_BookKeeper sys){
        this.v = v;
        this.b = b;
        this.sys = sys;
    }

    @Override
    public void execute() {
        //this.sys.Return_Book(this.b,this.v);
    }
}
