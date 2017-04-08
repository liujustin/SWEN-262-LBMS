package Books;//FILE::Books.Book.java
//AUTHOR::Kevin.P.Barnett, Justin Liu
//DATE::Feb.25.2017


import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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

    public String toString()
    {
        return String.format("%s,%s,{%s},%s\n", this.bookIsbn, this.bookName, buildAuthorString(), this.publishDate);
    }
}