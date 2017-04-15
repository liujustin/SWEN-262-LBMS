package Books;

import Client.Visitor.Visitor;
import Network.Command;

import java.util.ArrayList;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Redo_Borrow_Command implements Command {
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private Long borrower;
    private ArrayList<String> books;

    /**
     *
     * @param visitorID
     * @param bookISBNS
     */
    public Redo_Borrow_Command(Long visitorID, ArrayList<String> bookISBNS){
        this.borrower = visitorID;
        this.books = bookISBNS;
    }

    @Override
    public String execute(){
        try {
            //Return_Command r = new Return_Command();
            bookKeeper.borrowBook(this.borrower,this.books);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
