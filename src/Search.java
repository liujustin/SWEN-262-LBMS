import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class Search
{
    private static boolean containsAllAuthors(ArrayList<String> authors, Book b)
    {
        if(authors.get(0).equals("*"))
            return true;

        Boolean allIn = true;
        for(String author : authors)
            allIn &= b.getAuthors().contains(author);

        return allIn;
    }

    public static ArrayList<Book> search(String title, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: booksForPurchase.values())
            if(b.getBookName().equals(title) || title.equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(containsAllAuthors(authors, b))
                searchedBooks.add(b);

        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookIsbn().equals(isbn) || b.getBookIsbn().equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookPublisher().equals(publisher) || b.getBookPublisher().equals("*"))
                searchedBooks.add(b);

        return searchedBooks;
    }

    /*
    public ArrayList<Book> searchBySortOrder(String title, ArrayList<String> authors, String isbn, String publisher, String, sortOrder, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = searchByPublisher(title, authors, isbn, publisher, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        tempBookBuffer.sort(C);

        return searchedBooks;
    }
    */
}
