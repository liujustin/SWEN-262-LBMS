//FILE::Book_Purchase_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Book_Purchase_Command implements Command {

    private int quantity;
    private String bookID;
    private String books;

    /**
     *
     * @param quantity
     * @param bookID
     * @param books
     */
    public Book_Purchase_Command(int quantity, String bookID, String books) {
        this.quantity = quantity;
        this.bookID = bookID;
        this.books = books;
    }
    @Override
    public void execute() {
        //Main.bk.buyBook(this.quantity,this.bookID,this.books);
    }
}
