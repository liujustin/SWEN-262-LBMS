package Books;

import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Client.Visitor.Visitor_Operations;
import Network.Command;

import java.util.ArrayList;

//FILE::Books.Borrow_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Borrow_Command implements Command {

     Book_Operations bookKeeper = Book_Operations.getInstance();
     private Long visitorID;
     private ArrayList<String> bookISBNS;
     private boolean isUndo;

     /**
     *
     * @param visitorID
     * @param bookISBNS
     */
    public Borrow_Command(Long visitorID, ArrayList<String> bookISBNS, boolean isUndo){
        this.visitorID = visitorID;
        this.bookISBNS = bookISBNS;
        this.isUndo = isUndo;
    }

    @Override
    public String execute(){
        try {
            if (this.isUndo) {
                Visitor_Operations.bBook = false;
                Return_Command r = new Return_Command(this.visitorID,this.bookISBNS,false);
                Memento m = new Memento(r);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Return_Command r = new Return_Command(this.visitorID,this.bookISBNS,true);
                Memento m = new Memento(r);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            bookKeeper.borrowBook(this.visitorID,this.bookISBNS);
            Visitor_Operations.bBook = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
