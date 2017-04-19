package Books;
//FILE::Books.Book.java
//AUTHOR::Kevin.P.Barnett, Justin Liu
//DATE::Feb.25.2017


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Book
{
    private String bookIsbn;
    private String bookName;
    private String bookPublisher;
    private String publishDate;
    private boolean borrowingStatus;
    private String bookID;
    private ArrayList<String> authors = new ArrayList<String>();

    //Initial Constructor for each book object
    public Book(String bookIsbn, String bookName, String bookPublisher, String publishDate){
        if ((bookIsbn.length()) > 0 && (bookIsbn.matches("^[0-9]*$"))) {
            this.bookIsbn = bookIsbn;
        }
        if ((bookName.length() > 0)) {
            this.bookName = bookName;
        }
        if ((bookPublisher.length() > 0)) {
            this.bookPublisher = bookPublisher;
        }
        if ((publishDate.length() > 0)) {
            this.publishDate = publishDate;
        }
        this.borrowingStatus = true;
    }

    /**
     * Sets the author for each book
     * @param authors
     */
    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    /**
     *Sets the BookID for each book
     * @param bookID
     */
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    /**
     * Sets the borrowing status for the book
     * @param borrowingStatus
     */
    public void setBorrowing(boolean borrowingStatus) {
        if (borrowingStatus){
            this.borrowingStatus = false;
        }
        this.borrowingStatus = true;
    }

    /**
     * Gets the ISBN for the book object
     * @return bookIsbn
     */
    public String getBookIsbn() {
        return bookIsbn;
    }

    /**
     * Gets the book title for the book object
     * @return bookIsbn
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Gets the publisher for the book
     * @return bookPublisher
     */
    public String getBookPublisher() {
        return bookPublisher;
    }

    /**
     * Gets the date it was published
     * @return publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * Gets the borrowing status for the book
     * @return borrowingstatus
     */
    public boolean getBorrowingStatus() {
        return borrowingStatus;
    }

    /**
     * Gets the list of authors for the book
     * @return authors
     */
    public ArrayList<String> getAuthors() {
        return authors;
    }

    /**
     * Gets the book ID for the book object
     * @return bookID
     */
    public String getBookID() { return bookID; }

    /**
     * A toString method that returns an string of authors
     * @return
     */
    private String buildAuthorString()
    {
        String out = "";
        for(String author: this.authors)
            out += author+",";
        return out;
    }

    /**
     * A comparator used for sorting books by their title
     */
    public static Comparator<Book> BookTitleComparator = new Comparator<Book>() {

        public int compare(Book b1, Book b2) {
            String Book1 = b1.getBookName();
            String Book2 = b2.getBookName();
            return Book1.compareTo(Book2);
        }};


    /**
     * Comparator for sorting the book by publish date
     */
    public static Comparator<Book> BookDateComparator = new Comparator<Book>() {

        public int compare(Book b1, Book b2) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Book1 = b1.getPublishDate();
            String Book2 = b2.getPublishDate();
            Date Book1Date = null;
            Date Book2Date = null;
            try {
                Book1Date = sdf.parse(Book1);
                Book2Date = sdf.parse(Book2);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally{
                return Book2Date.compareTo(Book1Date);
            }
        }};

    /**
     * A toString method for the book Object
     * @return
     */
    public String toString()
    {
        return String.format("%s,%s,{%s},%s\n", this.bookIsbn, this.bookName, buildAuthorString(), this.publishDate);
    }


}