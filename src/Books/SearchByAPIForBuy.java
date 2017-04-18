package Books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SearchByAPIForBuy
{
    /*
    intitle: Returns results where the text following this keyword is found in the title.
    inauthor: Returns results where the text following this keyword is found in the author.
    inpublisher: Returns results where the text following this keyword is found in the publisher.
    isbn: Returns results where the text following this keyword is the ISBN number.
     */

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
            if(b.getBookIsbn().equals(isbn) || isbn.equals("*"))
                searchedBooks.add(b);
        return searchedBooks;
    }

    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, booksForPurchase);
        ArrayList<Book> searchedBooks = new ArrayList<>();

        for(Book b: tempBookBuffer)
            if(b.getBookPublisher().equals(publisher) || publisher.equals("*"))
                searchedBooks.add(b);
        return searchedBooks;
    }


    public static ArrayList<Book> search(String title, ArrayList<String> authors, String isbn, String publisher, String sortOrder, HashMap<String, Book> booksForPurchase)
    {
        ArrayList<Book> tempBookBuffer = search(title, authors, isbn, publisher,booksForPurchase);
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
