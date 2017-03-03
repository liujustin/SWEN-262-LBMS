//FILE::Book.java
//AUTHOR::Kevin.P.Barnett, Justin Liu
//DATE::Feb.25.2017

public class Book
{
	 private String bookIsbn;
	 private String bookName;
	 private String bookPublisher;
	 private String publishDate;
	 private String borrowingStatus;
	 private List<String> authors = new ArrayList<String>();

	 //Initial Constructor for each book
	 public Book(String bookIsbn, String bookName, String bookPublisher, String publishDate, String borrowingStatus){
        int iterations = 5;
        switch(iterations){
        	case ((bookIsbn.length()) > 0 && (bookIsbn.matches("^[0-9]*$"))):
        		this.bookIsbn = bookIsbn;
        	case ((bookName.length() > 0)):
        		this.bookName = bookName;
        	case ((bookPublisher.length() > 0)):
        		this.bookPublisher = bookPublisher;
        	case ((publishDate.length() > 0)):
        		this.publishDate = publishDate;
        	case ((borrowingStatus.length() > 0)):
        		this.borrowingStatus = borrowingStatus;
        }

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

    public String getBorrowingStatus() {
        return borrowingStatus;
    }

   	public List getAuthors() {
   		return authors;
   	}

   	public String getBookID() {
        return bookID;
    }

}