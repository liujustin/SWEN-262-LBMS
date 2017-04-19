package Books;

import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Client.Visitor.Visitor_Operations;
import Network.Command;

import java.util.ArrayList;

//FILE::Undo_Book_Purchase_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.15.2017
public class Undo_Book_Purchase_Command implements Command{
    Book_Operations bookKeeper = Book_Operations.getInstance();
    private int quantity;
    private ArrayList<String> ISBNS;
    private boolean isUndo;

    /**
     *
     * @param quantity
     * @param ISBNS
     */
    public Undo_Book_Purchase_Command(int quantity,ArrayList ISBNS,boolean isUndo) {
        this.quantity = quantity;
        this.ISBNS = ISBNS;
        this.isUndo = isUndo;
    }
    @Override
    public String execute() {
        try {
            if (this.isUndo) {
                Book_Purchase_Command p = new Book_Purchase_Command(this.quantity,this.ISBNS,false);
                Memento m = new Memento(p);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Book_Purchase_Command p = new Book_Purchase_Command(this.quantity,this.ISBNS,true);
                Memento m = new Memento(p);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            bookKeeper.undoBuyBook(this.quantity,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
