package Books;

import Network.Command;

import java.util.ArrayList;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Redo_Book_Purchase_Command implements Command {

    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private int quantity;
    private ArrayList<String> ISBNS;

    /**
     *
     * @param quantity
     * @param ISBNS
     */
    public Redo_Book_Purchase_Command(int quantity,ArrayList ISBNS) {
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
