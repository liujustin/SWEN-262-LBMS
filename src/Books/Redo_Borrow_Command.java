package Books;

import Client.Visitor.Visitor;
import Network.Command;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Redo_Borrow_Command implements Command {
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private Visitor borrower;
    private String books;

    /**
     *
     * @param visitor
     * @param book
     */
    public Redo_Borrow_Command(Visitor visitor, String book){
        this.borrower = visitor;
        this.books = book;
    }

    @Override
    public String execute(){
        try {
            bookKeeper.borrowBook(this.borrower,this.books);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
