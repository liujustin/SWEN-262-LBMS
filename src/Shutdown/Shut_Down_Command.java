package Shutdown;

import Books.Book_Loan;
import Books.Book_Operations;
import Client.Visitor.Visitor;
import Network.Command;
import Client.Visitor.Visitor_Operations;

//FILE::Shutdown.Shut_Down_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Shut_Down_Command implements Command {
    private Visitor_Operations vk = Visitor_Operations.getInstance();
    private Book_Operations bk = Book_Operations.getInstance();

    /**
     *
     * @param
     */
    public Shut_Down_Command(){}

    @Override
    public String execute()
    {
        Book_Loan.saveBookLoans(vk.getVisitorRegistry());
        bk.shutdown();
        vk.shutdown();

        return "";
    }
}
