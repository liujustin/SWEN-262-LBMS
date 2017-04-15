package Books;

import Network.Command;

import java.util.ArrayList;

//FILE::Undo_Book_Purchase_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.15.2017
public class Undo_Book_Purchase_Command implements Command{
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private int quantity;
    private ArrayList<String> ISBNS;

    /**
     *
     * @param quantity
     * @param ISBNS
     */
    public Undo_Book_Purchase_Command(int quantity,ArrayList ISBNS) {
        this.quantity = quantity;
        this.ISBNS = ISBNS;
    }
    @Override
    public String execute() {
        try {
            bookKeeper.undoBuyBook(this.quantity,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
