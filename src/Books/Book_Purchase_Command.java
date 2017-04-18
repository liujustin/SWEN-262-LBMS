package Books;

import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;

import java.util.ArrayList;

//FILE::Books.Book_Purchase_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Book_Purchase_Command implements Command {
    Book_Operations bookKeeper = Book_Operations.getInstance();
    private int quantity;
    private ArrayList<String> ISBNS;
    private boolean isUndo;

    /**
     *
     * @param quantity
     * @param ISBNS
     */
    public Book_Purchase_Command(int quantity,ArrayList ISBNS,boolean isUndo) {
        this.quantity = quantity;
        this.ISBNS = ISBNS;
        this.isUndo = isUndo;
    }
    @Override
    public String execute() {
        System.out.println("quantity" + this.ISBNS);
        try {
            if (this.isUndo) {
                Undo_Book_Purchase_Command u = new Undo_Book_Purchase_Command(this.quantity,this.ISBNS,false);
                Memento m = new Memento(u);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Undo_Book_Purchase_Command u = new Undo_Book_Purchase_Command(this.quantity,this.ISBNS,true);
                Memento m = new Memento(u);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }
            bookKeeper.buyBook(this.quantity,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
