package Books;

import Network.Command;

//FILE::Books.Book_Purchase_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Book_Purchase_Command implements Command {
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private int quantity;
    private String books;

    /**
     *
     * @param quantity
     * @param books
     */
    public Book_Purchase_Command(int quantity,String books) {
        this.quantity = quantity;
        this.books = books;
    }
    @Override
    public String execute() {
//        bookKeeper.buyBook(this.quantity,this.books);
        return "";
    }
}
