package Books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//FILE::Books.SearchToBuy.java
//AUTHOR::Kevin Barnett,Justin Liu
//DATE::Apr.10.2017
public class SearchToBuy
{
    private static ArrayList<Book> lastSearched;

    /**
     * Creates a new arraylist for search
     */
    public static void initializeSearch()
    {
        lastSearched = new ArrayList<>();
    }

    /**
     * Set the new arraylist to the books that were last searched
     * @param lastSearchedBooks
     */
    public static void setLastSearched(ArrayList<Book> lastSearchedBooks)
    {
        lastSearched = lastSearchedBooks;
    }

    /**
     * Get the arraylist of the books that were last searched
     * @return
     */
    public static ArrayList<Book> getLastSearched()
    {
        return lastSearched;
    }

    /**
     * returns a boolean of whether or not the parameter has all the authors when searching for author
     * @param authors
     * @param b
     * @return
     */
    private static boolean containsAllAuthors(ArrayList<String> authors, Book b)
    {
        if(authors.get(0).equals("*"))
            return true;

        Boolean allIn = true;
        for(String author : authors)
            allIn &= b.getAuthors().contains(author);

        return allIn;
    }

    /**
     * Search method that accounts for only the title parameter being passed in.
     * @param title
     * @param booksForPurchase
     * @return
     */
    public static ArrayList<Book> search(String title, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: booksForPurchase.values())
            if(b.getBookName().equals(title) || title.equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    /**
     * Search method that accounts for only the title and author parameter being passed in.
     * @param title
     * @param authors
     * @param booksForPurchase
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(containsAllAuthors(authors, b))
                searchedBooks.add(b);

        return searchedBooks;
    }

    /**
     * Search method that accounts for only the title, author and isbn parameter being passed in.
     * @param title
     * @param authors
     * @param isbn
     * @param booksForPurchase
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookIsbn().equals(isbn) || isbn.equals("*"))
                searchedBooks.add(b);
        return searchedBooks;
    }

    /**
     * Search method that accounts for only the title, author and isbn and publisher parameter being passed in.
     * @param title
     * @param authors
     * @param isbn
     * @param publisher
     * @param booksForPurchase
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookPublisher().equals(publisher) || publisher.equals("*"))
                searchedBooks.add(b);
        return searchedBooks;
    }

    /**
     * Search method that accounts for all parameters being passed in and sorts the arraylist of books sorted.
     * @param title
     * @param authors
     * @param isbn
     * @param publisher
     * @param sortOrder
     * @param booksForPurchase
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, publisher, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer) {
            if (b.getBookPublisher().equals(publisher) || publisher.equals("*"))
                searchedBooks.add(b);
        }
        if (sortOrder.equals("title")) {
            Collections.sort(searchedBooks, Book.BookTitleComparator);
        }
        else if(sortOrder.equals("publish-date")){
            Collections.sort(searchedBooks,Book.BookDateComparator);
        }
        else{

        }
        return searchedBooks;
    }

}
