package Books;//FILE::Books.Book_Operations.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

import Client.Visitor.Visitor_Operations;
import Time.Time_Operations;
import Client.Visitor.Visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Book_Operations
{
    private static boolean searchState;
    private static final Book_Operations bookKeeper = new Book_Operations();
    private Visitor_Operations visitorOperations = Visitor_Operations.getInstance();
    private final String bookListURI = "books.txt";
    private HashMap<Book, Integer> purchasedBooks;
    private HashMap<String, Book> bookRegistry;
    private static int bookTotal;


    public Book_Operations()
    {
        this.purchasedBooks = new HashMap<>();
        this.bookRegistry = new HashMap<>();
        getBookList();
    }

    public Integer getBookTotal(){
        return bookTotal;
    }

    public static Book_Operations getInstance(){
        return bookKeeper;
    }
    public HashMap<String,Book> getBookRegistry(){ return this.bookRegistry; }
    /**
     *
     * gets the available list of books for the library to purchase
     */
    private void getBookList()
    {
        Scanner bookListReader;
        try
        {
            bookListReader = new Scanner(new File(this.bookListURI));

            while(bookListReader.hasNextLine())
            {
                String[] tempLine = bookListReader.nextLine().split(",\"|\",\\{|},\"|\",");

                Book tempBook = new Book(tempLine[0],
                                         tempLine[1],
                                         tempLine[3],
                                         tempLine[4]);

                ArrayList<String> authors = new ArrayList<>();
                String[] tempAuthors = tempLine[2].split(",");

                for(int i=0; i<tempAuthors.length; ++i)
                    authors.add(tempAuthors[i]);

                tempBook.setAuthors(authors);

                this.bookRegistry.put(tempLine[0], tempBook);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try{
            bookListReader = new Scanner(new File("purchasedBooks.log"));

            bookTotal = Integer.parseInt(bookListReader.nextLine());

            while(bookListReader.hasNextLine()){
                String templine = bookListReader.nextLine();
                String quantity = bookListReader.nextLine().split("=")[1];
                String bookObject = templine.split(",")[0];
                this.getPurchasedBooks().put(this.bookRegistry.get(bookObject),Integer.parseInt(quantity));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void undoBuyBook(Integer quantity, ArrayList<String> ISBNS)throws Exception
    {
        ArrayList<Book> removebooks = new ArrayList<>();
        // loop through all purchased books and remove those books with corresponding quantity
        for (Book bookToRemove : SearchToBuy.getLastSearched()) { //last searched books
            for(Book purchasedbook : getPurchasedBooks().keySet()) { //books inside library
                if (bookToRemove.equals(purchasedbook)) { // if books matches
                    int bookvalue = this.purchasedBooks.get(purchasedbook);
                    bookvalue -= quantity; // subtract number of copies
                    this.purchasedBooks.put(bookToRemove, bookvalue);
                   if (this.purchasedBooks.get(bookToRemove) == 0) { // if number of copies reaches 0 delete
                        removebooks.add(purchasedbook);
                    }
                }
            }
        }
        for(Book books : removebooks){
            this.purchasedBooks.remove(books);
        }
    }

    /**
     *
     * @param quantity
     * @param ISBNS
     */
    public void buyBook(Integer quantity, ArrayList<String> ISBNS) throws Exception {

        String out = "";
        int amount = 0;

        for (String isbn : ISBNS) {
            for (int i = 0; i < SearchToBuy.getLastSearched().size(); i++) {
                if(SearchToBuy.getLastSearched().get(i).equals(this.bookRegistry.get(isbn))){
                    out += SearchToBuy.getLastSearched().get(i).toString();
                    for (int j = 0; j < quantity; j++) {
                        if (this.purchasedBooks.containsKey(this.bookRegistry.get(isbn))){
                            this.purchasedBooks.put(this.bookRegistry.get(isbn), this.purchasedBooks.get(this.bookRegistry.get(isbn)) + 1);
                            amount++;
                            bookTotal++;
                        }else {
                            this.purchasedBooks.put(this.bookRegistry.get(isbn), 1);
                            amount++;
                            bookTotal++;
                        }
                    }
                }
            }
        }
        System.out.println(String.format("buy,success,%d,\n%s", amount , out));
    }
    /**
     *
     * @param visitorID
     * @param bookISBNS
     * @throws Exception
     *
     * allows the visitor to borrow a book
     */
    public void borrowBook(Long visitorID, ArrayList<String> bookISBNS) throws Exception
    {
        ArrayList<Book> listofbooks = new ArrayList<>();
        String time = Time_Operations.Get_Time();
        Visitor_Operations visitKeeper = Visitor_Operations.getInstance();
        HashMap<Long,Visitor> visitorlist = visitKeeper.getVisitorRegistry();
        Visitor visitor = visitorOperations.getVisitorRegistry().get(visitorID);
        String errorString = "borrow,invalid-book-id,{";

        int index = 0;
        if(!visitorlist.containsKey(visitorID)) {
            throw new Exception("borrow,invalid-visitor-id;");
        }
        if(!Time_Operations.getIsopen(time)) {
            throw new Exception("Library is currently closed.");
        }
        if(bookISBNS.size() > 5 - visitor.getBorrowed_books().size()) {
            throw new Exception("borrow,book-limit-exceeded;");
        }
        if(!Visitor_Operations.pFine) {
            if (visitor.getBalance() > 0) {
                throw new Exception("borrow,outstanding-fine," + visitor.getBalance() + ';');
            }
        }
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
        calendar.setTime(dateFormat.parse(time));
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date futureDate = calendar.getTime();
        String futDate = dateFormat.format(futureDate);
        for(String isbn : bookISBNS){
            if(bookRegistry.containsKey(isbn))
            {
                listofbooks.add(bookRegistry.get(isbn));
                if(!getPurchasedBooks().containsKey(listofbooks.get(index))){
                    listofbooks.remove(index);
                    errorString += isbn + ",";
                }
            }else{
                errorString += isbn + ",";
            }
        }
        if (!errorString.endsWith("borrow,invalid-book-id,{"))
        {
            errorString = errorString.substring(0, errorString.length() - 1);
            errorString += "};";
            throw new Exception(errorString);
        }
        for(int i = 0; i < listofbooks.size(); i++)
        {
            for (int j = 0; j < SearchForInfo.getLastSearched().size(); j++)
            {
                visitor.add_book(new Book_Loan(visitor, listofbooks.get(i), 0.0, true, Time_Operations.Get_Time(), futDate));
                break;
            }
        }
        if(Visitor_Operations.bBook) {
            System.out.println("borrow," + futDate.substring(0, 10));
        }
    }

    public HashMap<String, Book> getBooksForPurchase()
    {
        return this.bookRegistry;
    }

    public HashMap<Book, Integer> getPurchasedBooks(){return this.purchasedBooks;}

    public void shutdown()
    {
        //Store Purchased Books
        try
        {
            PrintStream saveState = new PrintStream(new FileOutputStream(new File("purchasedBooks.log")));
            saveState.flush();

            saveState.println(bookTotal);

            for(Map.Entry<Book, Integer> entry:this.purchasedBooks.entrySet()) {
                saveState.println(entry.getKey().toString() + "=" + entry.getValue());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setSearchState(boolean searchState) {
        Book_Operations.searchState = searchState;
    }

    public static boolean isSearchState() {
        return searchState;
    }

    /**
     *
     * @param args
     *
     * main used for testing purposes
     */
    public static void main(String[] args)
    {
        new Book_Operations().getBookList();
    }
}