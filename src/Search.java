import java.util.ArrayList;
import java.util.HashMap;

public class Search
{
    public ArrayList<Book> searchByAuthor(String title, ArrayList<String> authors, HashMap<Book, Integer> booksForPurchase)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();
        for(Book b:booksForPurchase.keySet())
        {
            if((b.getBookName().equals(title) || title.equals("*")))
            {
                Boolean allIn = true;
                for(String author : authors)
                    allIn &= b.getAuthors().contains(author);

                if(allIn)
                    searchedBooks.add(b);
            }
        }
        return searchedBooks;
    }

    public ArrayList<Book> searchByISBN(String isbn, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();

        searchedBooks.add(booksForPurchase.get(isbn));

        return searchedBooks;
    }

    public ArrayList<Book> searchByPublisher(String title, String publisher, HashMap<Book, Integer> booksForPurchase)
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();
        for(Book b:booksForPurchase.keySet())
        {
            if((b.getBookName().equals(title) || title.equals("*")) && b.getBookPublisher().contains(publisher))
                searchedBooks.add(b);
        }
        return searchedBooks;
    }
}
