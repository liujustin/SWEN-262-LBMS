package Shutdown;

import Books.Book;
import Books.Book_Loan;
import Books.LBMS_BookKeeper;
import Network.Command;
import Client.Visitor.LBMS_VisitorKeeper;

//FILE::Shutdown.Shut_Down_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Shut_Down_Command implements Command {
    private LBMS_VisitorKeeper vk = LBMS_VisitorKeeper.getInstance();
    private LBMS_BookKeeper bk = LBMS_BookKeeper.getInstance();

    /**
     *
     * @param
     */
    public Shut_Down_Command(){}

    @Override
    public String execute()
    {
        //Book_Loan.saveBookLoans(vk.getVisitorRegistry());
        bk.shutdown();
        vk.shutdown();

        return "";
    }
}
