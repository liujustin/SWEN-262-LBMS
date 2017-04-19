package Books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//FILE::Books.SearchForInfo.java
//AUTHOR::Kevin Barnett,Justin Liu
//DATE::Apr.10.2017

public class SearchForInfo
{
    private static ArrayList<Book> lastSearched;

    public static void initializeSearch()
    {
        lastSearched = new ArrayList<>();
    }

    public static void setLastInfoSearch(ArrayList<Book> lastSearchedBooks)
    {
        lastSearched = lastSearchedBooks;
    }

    public static ArrayList<Book> getLastSearched()
    {
        return lastSearched;
    }

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
     * @param purchasedBooks
     * @return
     */
    public static ArrayList<Book> search(String title, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: purchasedBooks.keySet())
            if(b.getBookName().equals(title) || title.equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    /**
     * Search method that accounts for only the title and author parameter being passed in.
     * @param title
     * @param authors
     * @param purchasedBooks
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, purchasedBooks);
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
     * @param purchasedBooks
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, purchasedBooks);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookIsbn().equals(isbn) || isbn.equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    /**
     * Search method that accounts for only the title,author,isbn and publisher parameter being passed in.
     * @param title
     * @param authors
     * @param isbn
     * @param publisher
     * @param purchasedBooks
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, purchasedBooks);
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
     * @param purchasedBooks
     * @return
     */
    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, publisher, purchasedBooks);
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
