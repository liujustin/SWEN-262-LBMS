/**
 * Created by adamn on 3/2/2017.
 */
public class Book_Search_Command implements Command {
    private Book book;

    public Book_Search_Command(Book b){
        this.book = b;
    }

    public void execute() {
        this.book.getBookID();
    }
}
