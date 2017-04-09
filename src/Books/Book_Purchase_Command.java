package Books;

import Network.Command;

import java.util.ArrayList;

//FILE::Books.Book_Purchase_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Book_Purchase_Command implements Command {
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private int quantity;
    private ArrayList<String> ISBNS;

    /**
     *
     * @param quantity
     * @param ISBNS
     */
    public Book_Purchase_Command(int quantity,ArrayList ISBNS) {
        this.quantity = quantity;
        this.ISBNS = ISBNS;
    }
    @Override
    public String execute() {
        try {
            bookKeeper.buyBook(this.quantity,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
