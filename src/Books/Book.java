package Books;//FILE::Books.Book.java
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

    //Initial Constructor for each book
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

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    /**
     *
     * @param bookID
     */
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    /**
     *
     * @param borrowingStatus
     */
    public void setBorrowing(boolean borrowingStatus) {
        if (borrowingStatus){
            this.borrowingStatus = false;
        }
        this.borrowingStatus = true;
    }

    /**
     *
     * @return bookIsbn
     */
    public String getBookIsbn() {
        return bookIsbn;
    }

    /**
     *
     * @return bookIsbn
     */
    public String getBookName() {
        return bookName;
    }

    /**
     *
     * @return bookPublisher
     */
    public String getBookPublisher() {
        return bookPublisher;
    }

    /**
     *
     * @return publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     *
     * @return borrowingstatus
     */
    public boolean getBorrowingStatus() {
        return borrowingStatus;
    }

    /**
     *
     * @return authors
     */
    public ArrayList<String> getAuthors() {
        return authors;
    }

    /**
     *
     * @return bookID
     */
    public String getBookID() { return bookID; }


    private String buildAuthorString()
    {
        String out = "";
        for(String author: this.authors)
            out += author+",";
        return out;
    }

    public static Comparator<Book> BookTitleComparator = new Comparator<Book>() {

        public int compare(Book b1, Book b2) {
            String Book1 = b1.getBookName();
            String Book2 = b2.getBookName();
            return Book1.compareTo(Book2);
        }};

    /*Comparator for sorting the list by roll no*/
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

    public String toString()
    {
        return String.format("%s,%s,{%s},%s\n", this.bookIsbn, this.bookName, buildAuthorString(), this.publishDate);
    }


}