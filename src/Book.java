//FILE::Book.java
//AUTHOR::Kevin.P.Barnett, Justin Liu
//DATE::Feb.25.2017


import java.util.ArrayList;
import java.util.List;

public class Book
{
    private String bookIsbn;
    private String bookName;
    private String bookPublisher;
    private String publishDate;
    private Boolean borrowingStatus;
    private String bookID;
    private List<String> authors = new ArrayList<String>();

    //Initial Constructor for each book
    public Book(String bookIsbn, String bookName, String bookPublisher, String publishDate, Boolean borrowingStatus){
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
        this.borrowingStatus = borrowingStatus;
    }

    public void setAuthors(ArrayList<String> authors) {
        for(String name : authors){
            authors.add(name);
        }
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Boolean getBorrowingStatus() {
        return borrowingStatus;
    }

    public List getAuthors() {
        return authors;
    }

    public String getBookID() {
        return bookID;
    }

}