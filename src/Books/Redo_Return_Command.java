package Books;

import Client.Visitor.LBMS_VisitorKeeper;
import Network.Command;

import java.util.ArrayList;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Redo_Return_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitor_ID;
    private ArrayList<String> ISBNS;

    public Redo_Return_Command(Long visitor_ID,ArrayList<String> ISBNS){
        this.visitor_ID = visitor_ID;
        this.ISBNS = ISBNS;

    }

    @Override
    public String execute() {
        try {
            visitorKeeper.returnBook(this.visitor_ID,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
