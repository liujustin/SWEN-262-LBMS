/**
 * Created by adamn on 3/2/2017.
 */
public class Borrow_Command implements Command {

    private Visitor borrower;
    private Book_Loan book;

    public Borrow_Command(Visitor v, Book_Loan b){
        this.borrower = v;
        this.book = b;
    }

    public void execute(){
        this.borrower.add_book(this.borrower,this.book);
    }
}
