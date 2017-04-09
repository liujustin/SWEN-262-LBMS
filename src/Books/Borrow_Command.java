package Books;

import Network.Command;
import Network.Main;
import Client.Visitor.Visitor;

//FILE::Books.Borrow_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Borrow_Command implements Command {

    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private Visitor borrower;
    private String books;

    /**
     *
     * @param visitor
     * @param book
     */
    public Borrow_Command(Visitor visitor, String book){
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
