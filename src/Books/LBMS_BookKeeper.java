package Books;//FILE::Books.LBMS_BookKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

import Client.Visitor.LBMS_VisitorKeeper;
import Time.LBMS_StatisticsKeeper;
import Client.Visitor.Visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LBMS_BookKeeper
{
    private static final LBMS_BookKeeper bookKeeper = new LBMS_BookKeeper();
    private final String bookListURI = "books.txt";
    private HashMap<Book, Integer> purchasedBooks;
    private HashMap<String, Book> bookRegistry;


    public LBMS_BookKeeper()
    {
        this.purchasedBooks = new HashMap<>();
        this.bookRegistry = new HashMap<>();
        getBookList();
    }

    public static LBMS_BookKeeper getInstance(){
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

        PrintStream purchasedBooksReader;
        try
        {
            purchasedBooksReader = new PrintStream(new FileOutputStream(new File("purchasedBooks.log")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
                        }else {
                            this.purchasedBooks.put(this.bookRegistry.get(isbn), 1);
                            amount++;
                        }
                    }
                }
            }
        }
        System.out.println(String.format("buy,success,%d,\n%s", amount , out));
        System.out.println(this.purchasedBooks);
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
        String time = LBMS_StatisticsKeeper.Get_Time();
        LBMS_VisitorKeeper visitKeeper = LBMS_VisitorKeeper.getInstance();
        HashMap<Long,Visitor> visitorlist = visitKeeper.getVisitorRegistry();
        Visitor visitor = LBMS_VisitorKeeper.getVisitorRegistry().get(visitorID);
        System.out.println(visitorlist.keySet());
        if(!visitorlist.containsKey(visitorID)) {
            throw new Exception("borrow,invalid-visitor-id;");
        }
        //Book listofbooks = bookRegistry.get(bookISBNS);
        //if(!LBMS_StatisticsKeeper.getIsopen(time)) {
          //  throw new Exception("Library is currently closed.");
        //}
        if(bookISBNS.size() > 5 - visitor.getBorrowed_books().size()) {
            throw new Exception("borrow,book-limit-exceeded;");
        }
        if(visitor.getBalance() > 0) {
            throw new Exception("borrow,outstanding-fine," + visitor.getBalance() + ';');
        }
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");
        calendar.setTime(dateFormat.parse(time));
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date futureDate = calendar.getTime();
        String futDate = dateFormat.format(futureDate);
        ArrayList<String> invalidBookIDs = new ArrayList<>();
        for(String isbn : bookISBNS){
            if(bookRegistry.containsKey(isbn)){
                listofbooks.add(bookRegistry.get(isbn));
            }
        }
        for(int i = 0; i < listofbooks.size(); i++) {
            for (int j = 0; j < SearchForInfo.getLastSearched().size(); j++) {
                if (!this.purchasedBooks.containsKey(listofbooks.get(i))) {
                    System.out.println(this.purchasedBooks.keySet());
                    invalidBookIDs.add(bookISBNS.get(j));
                } else {
                    visitor.add_book(new Book_Loan(visitor, listofbooks.get(i), 0.0, true, LBMS_StatisticsKeeper.Get_Time(), futDate));
                }
            }
        }
        if(invalidBookIDs.size() > 0)
        {
            String errorString = "borrow,invalid-book-id,{";
            for(String isbn: invalidBookIDs)
                errorString += isbn+", ";
            errorString = errorString.subSequence(0, errorString.length()-1).toString()+"};";

            throw new Exception(errorString);
        }
        System.out.println("borrow," + futDate.substring(0,10));
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

            for(Map.Entry<Book, Integer> entry:this.purchasedBooks.entrySet())
                saveState.println(entry.getKey().toString()+":"+entry.getValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param args
     *
     * main used for testing purposes
     */
    public static void main(String[] args)
    {
        new LBMS_BookKeeper().getBookList();
    }
}