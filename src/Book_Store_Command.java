/**
 * Created by adamn on 3/2/2017.
 */
public class Book_Store_Command implements Command {

    private String title;
    private String authors;
    private String isbn;
    private String publisher;
    private String sort_order;

    public Book_Store_Command(String title, String authors, String isbn, String publisher, String sort_order) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sort_order = sort_order;
    }

    public void execute() {
        //Main.bk.search(this.title,this.authors,this.isbn,this.publisher,this.sort_order);
    }
}
