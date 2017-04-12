package Books;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchForInfo
{
    private static ArrayList<Book> lastSearched;

    public static void initializeSearch()
    {
        lastSearched = new ArrayList<>();
    }

    public static void setLastSearched(ArrayList<Book> lastSearchedBooks)
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

    public static ArrayList<Book> search(String title, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: purchasedBooks.keySet())
            if(b.getBookName().equals(title) || title.equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, purchasedBooks);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(containsAllAuthors(authors, b))
                searchedBooks.add(b);

        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, purchasedBooks);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookIsbn().equals(isbn) || b.getBookIsbn().equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, purchasedBooks);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookPublisher().equals(publisher) || b.getBookPublisher().equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    /*
    public ArrayList<Book> searchBySortOrder(String title, ArrayList<String> authors, String isbn, String publisher, String, sortOrder, HashMap<Book, Integer> purchasedBooks)
    {
        ArrayList<Book> tempBookBuffer = searchByPublisher(title, authors, isbn, publisher, purchasedBooks);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        tempBookBuffer.sort(C);

        return searchedBooks;
    }
    */
}
