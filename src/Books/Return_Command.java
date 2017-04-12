package Books;

import Client.Visitor.LBMS_VisitorKeeper;
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

    public Return_Command(Long visitor_ID,ArrayList<String> ISBNS){
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