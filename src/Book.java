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
<<<<<<< HEAD
    private boolean borrowingStatus;
=======
    private Boolean borrowingStatus;
>>>>>>> f000b8dce4fade0f7748fbced57459125e9e8538
    private String bookID;
    private List<String> authors = new ArrayList<String>();

    //Initial Constructor for each book
<<<<<<< HEAD
    public Book(String bookIsbn, String bookName, String bookPublisher, String publishDate){
=======
    public Book(String bookIsbn, String bookName, String bookPublisher, String publishDate, Boolean borrowingStatus){
>>>>>>> f000b8dce4fade0f7748fbced57459125e9e8538
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
<<<<<<< HEAD
        this.borrowingStatus = true;
=======
        this.borrowingStatus = borrowingStatus;
>>>>>>> f000b8dce4fade0f7748fbced57459125e9e8538
    }

    public void setAuthors(ArrayList<String> authors) {
        for(String name : authors){
            authors.add(name);
        }
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setBorrowing(boolean borrowingStatus) {
        if (borrowingStatus){
            this.borrowingStatus = false;
        }
        this.borrowingStatus = true;
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

<<<<<<< HEAD
    public boolean getBorrowingStatus() {
=======
    public Boolean getBorrowingStatus() {
>>>>>>> f000b8dce4fade0f7748fbced57459125e9e8538
        return borrowingStatus;
    }

    public List getAuthors() {
        return authors;
    }

    public String getBookID() { return bookID; }



}