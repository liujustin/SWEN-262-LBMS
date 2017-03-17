/**
 * Created by adamn on 3/2/2017.
 */
public class Book_Purchase_Command implements Command {

    private Book boughtBook;

    public Book_Purchase_Command(Book book){
        this.boughtBook = book;
    }

    public void execute() {
        //this.boughtBook.buyBook();
    }
}
