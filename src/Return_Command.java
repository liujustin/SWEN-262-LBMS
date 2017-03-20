/**
 * Created by adamn on 3/2/2017.
 */
public class Return_Command implements Command {
    private Visitor v;
    private String bookID;
    private String books;

    public Return_Command(Visitor v, String bookID, String books){
        this.v = v;
        this.bookID = bookID;
        this.books = books;

    }

    @Override
    public void execute() {
       // Main.bk.Return_Book(v.getVisitor_ID(),this.bookID,this.books);
    }
}
