package Books;

import Network.Command;
import Network.Main;

//FILE::Books.Find_Borrowed_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017
public class Find_Borrowed_Command implements Command {

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
            return Main.vk.borrowedBooks(visitorID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
