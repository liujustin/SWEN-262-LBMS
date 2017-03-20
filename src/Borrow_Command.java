import java.util.ArrayList;

/**
 * Created by adamn on 3/2/2017.
 */
public class Borrow_Command implements Command {

    private Visitor borrower;
    private String books;

    public Borrow_Command(Visitor v, String b){
        this.borrower = v;
        this.books = b;
    }

    public void execute(){
        try {
            Main.bk.borrowBook(this.borrower,this.books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
