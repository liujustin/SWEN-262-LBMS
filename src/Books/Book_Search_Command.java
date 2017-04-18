package Books;

import Books.Book;
import Network.Command;
import Network.Main;

import java.util.ArrayList;

//FILE::Book_Search_Command.java
//AUTHOR::Ryan Connors, Kevin.P.Barnett
//DATE::Feb.25.2017
public class Book_Search_Command implements Command
{
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    private String sort_order;
    private int paramCount;

    public Book_Search_Command(ArrayList params)
    {
        this.authors = new ArrayList<>();
        try
        {
            this.title = (String) params.get(2);

            for(Object s: (ArrayList)params.get(3))
                this.authors.add((String) s);

            this.isbn = (String) params.get(4);
            this.publisher = (String) params.get(5);
            this.sort_order = (String) params.get(6);
        }
        catch(Exception e){this.paramCount = params.size();}

        SearchForInfo.initializeSearch();
    }

    private String generateBookString(ArrayList<Book> searchedBooks)
    {
        String out = "";
        for(Book b: searchedBooks)
            out += b.toString();

        return out;
    }

    @Override
    public String execute()
    {
        ArrayList<Book> searchedBooks = new ArrayList<>();
        switch(paramCount-2)
        {
            case 1: searchedBooks = SearchForInfo.search(this.title, bookKeeper.getPurchasedBooks());
                break;
            case 2: searchedBooks = SearchForInfo.search(this.title, this.authors, bookKeeper.getPurchasedBooks());
                break;
            case 3: searchedBooks = SearchForInfo.search(this.title, this.authors, this.isbn, bookKeeper.getPurchasedBooks());
                break;
            case 4: searchedBooks = SearchForInfo.search(this.title, this.authors, this.isbn, this.publisher, bookKeeper.getPurchasedBooks());
                break;
            case 5: searchedBooks = SearchForInfo.search(this.title, this.authors, this.isbn, this.publisher, this.sort_order, bookKeeper.getPurchasedBooks());
                break;
        }

        SearchForInfo.setLastInfoSearch(searchedBooks);

        return String.format("info,%d,\n%s", searchedBooks.size(), generateBookString(searchedBooks));
    }
}
