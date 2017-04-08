package Books;

import Client.Visitor.LBMS_VisitorKeeper;
import Network.Command;
import Network.Main;

//FILE::Books.Find_Borrowed_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017
public class Find_Borrowed_Command implements Command {

    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;

    /**
     *
     * @param visitorID
     */
    public Find_Borrowed_Command(Long visitorID){
        this.visitorID = visitorID;
    }

    @Override
    public String execute() {
        try {
            return visitorKeeper.borrowedBooks(visitorID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
