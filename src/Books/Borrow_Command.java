package Books;

import Network.Command;
import Network.Main;
import Client.Visitor.Visitor;

//FILE::Books.Borrow_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Borrow_Command implements Command {

    private Visitor borrower;
    private String books;

    /**
     *
     * @param v
     * @param b
     */
    public Borrow_Command(Visitor v, String b){
        this.borrower = v;
        this.books = b;
    }

    @Override
    public String execute(){
        try {
            Main.bk.borrowBook(this.borrower,this.books);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
