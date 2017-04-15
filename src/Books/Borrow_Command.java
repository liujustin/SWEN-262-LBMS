package Books;

import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;
import Network.Main;
import Client.Visitor.Visitor;

import java.util.ArrayList;

//FILE::Books.Borrow_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Borrow_Command implements Command {

     LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
     private Long visitorID;
     private ArrayList<String> bookISBNS;

     /**
     *
     * @param visitorID
     * @param bookISBNS
     */
    public Borrow_Command(Long visitorID, ArrayList<String> bookISBNS){
        this.visitorID = visitorID;
        this.bookISBNS = bookISBNS;
    }

    @Override
    public String execute(){
        try {
            Return_Command r = new Return_Command(this.visitorID,this.bookISBNS);
            Memento m = new Memento(r);
            UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            bookKeeper.borrowBook(this.visitorID,this.bookISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
