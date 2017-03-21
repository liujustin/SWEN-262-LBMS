//FILE::Book_Loan.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Book_Search_Command implements Command {
    private String title;
    private String authors;
    private String isbn;
    private String publisher;
    private String sort_order;

    /**
     *
     * @param title
     * @param authors
     * @param isbn
     * @param publisher
     * @param sort_order
     */
    public Book_Search_Command(String title, String authors, String isbn, String publisher, String sort_order) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publisher = publisher;
        this.sort_order = sort_order;
    }

    @Override
    public void execute() {
        //Main.bk.search(this.title,this.authors,this.isbn,this.publisher,this.sort_order);
    }
}
