package Books;

import Network.Command;

import java.util.ArrayList;

//FILE::Book_Store_Command.java
//AUTHOR::Ryan Connors, Kevin.P.Barnett
//DATE::Feb.25.2017
public class Book_Store_Command implements Command
{
    Book_Operations bookKeeper = Book_Operations.getInstance();
    private String title;
    private ArrayList<String> authors;
    private String isbn;
    private String publisher;
    private String sort_order;
    private int paramCount;

    public Book_Store_Command(ArrayList params)
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
            this.paramCount = params.size();
        }
        catch(Exception e){this.paramCount = params.size();}

        SearchToBuy.initializeSearch();
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
            case 1: searchedBooks = SearchToBuy.search(this.title, bookKeeper.getBooksForPurchase());
                break;
            case 2: searchedBooks = SearchToBuy.search(this.title, this.authors, bookKeeper.getBooksForPurchase());
                break;
            case 3: searchedBooks = SearchToBuy.search(this.title, this.authors, this.isbn, bookKeeper.getBooksForPurchase());
                break;
            case 4: searchedBooks = SearchToBuy.search(this.title, this.authors, this.isbn, this.publisher, bookKeeper.getBooksForPurchase());
                break;
            case 5: searchedBooks = SearchToBuy.search(this.title, this.authors, this.isbn, this.publisher, this.sort_order, bookKeeper.getBooksForPurchase());
                break;
        }

        SearchToBuy.setLastSearched(searchedBooks);

        return String.format("search,%d,\n%s", searchedBooks.size(), generateBookString(searchedBooks));
    }
}
