import java.util.ArrayList;

//FILE::Book_Search_Command.java
//AUTHOR::Ryan Connors, Kevin.P.Barnett
//DATE::Feb.25.2017
public class Book_Search_Command implements Command {
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
            this.title = (String) params.get(1);

            for(Object s: (ArrayList)params.get(2))
                this.authors.add((String) s);

            this.isbn = (String) params.get(3);
            this.publisher = (String) params.get(4);
            this.sort_order = (String) params.get(5);
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
        switch(paramCount-1)
        {
            case 1: searchedBooks = SearchToBuy.search(this.title, Main.bk.getBooksForPurchase());
                break;
            case 2: searchedBooks = SearchToBuy.search(this.title, this.authors, Main.bk.getBooksForPurchase());
                break;
            case 3: searchedBooks = SearchToBuy.search(this.title, this.authors, this.isbn, Main.bk.getBooksForPurchase());
                break;
            case 4: searchedBooks = SearchToBuy.search(this.title, this.authors, this.isbn, this.publisher, Main.bk.getBooksForPurchase());
                break;
            //case 5: searchedBooks = SearchToBuy.search(this.title, this.authors, this.isbn, this.publisher, this.sort_order,  Main.bk.getBooksForPurchase());
            //    break;
        }

        SearchToBuy.setLastSearched(searchedBooks);

        return String.format("search,%d,\n%s", searchedBooks.size(), generateBookString(searchedBooks));
    }
}
