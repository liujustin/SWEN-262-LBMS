package Books;

import Client.Visitor.LBMS_VisitorKeeper;
import Client.Visitor.Memento;
import Client.Visitor.UndoRedoCaretaker;
import Network.Command;
import Network.Main;

import java.util.ArrayList;

//FILE::Books.Return_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017
public class Return_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitor_ID;
    private ArrayList<String> ISBNS;
    private boolean isUndo;

    public Return_Command(Long visitor_ID,ArrayList<String> ISBNS,boolean isUndo){
        this.visitor_ID = visitor_ID;
        this.ISBNS = ISBNS;
        this.isUndo = isUndo;

    }

    @Override
    public String execute() {
        try {
            if (this.isUndo) {
                Borrow_Command b = new Borrow_Command(this.visitor_ID,this.ISBNS,false);
                Memento m = new Memento(b);
                UndoRedoCaretaker.getCaretaker().getRedoStack().add(m);
            }
            else {
                Borrow_Command b = new Borrow_Command(this.visitor_ID,this.ISBNS,true);
                Memento m = new Memento(b);
                UndoRedoCaretaker.getCaretaker().getUndoStack().add(m);
            }

            visitorKeeper.returnBook(this.visitor_ID,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
