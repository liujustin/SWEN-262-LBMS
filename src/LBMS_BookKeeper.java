//FILE::LBMS_BookKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LBMS_BookKeeper
{
    private final String bookListURI = "book.txt";
    private HashMap<Book, Integer> purchasedBooks;
    private HashMap<String, Book> bookRegistry;


    public LBMS_BookKeeper()
    {
        this.purchasedBooks = new HashMap<>();
        this.bookRegistry = new HashMap<>();
    }

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
    public void buyBook(Integer quantity, ArrayList<String> ISBNS)
    {
        for(String isbn : ISBNS)
        {
            for(int i = 0; i < quantity; ++ i)
            {
                if(this.purchasedBooks.containsKey(this.bookRegistry.get(isbn)))
                    this.purchasedBooks.put(this.bookRegistry.get(isbn), this.purchasedBooks.get(this.bookRegistry.get(isbn)) + 1);
                else
                    this.purchasedBooks.put(this.bookRegistry.get(isbn), 1);
            }
        }
    }

    /**
     *
     * @param visitor
     * @param bookList
     * @throws Exception
     *
     * allows the visitor to borrow a book
     */
    public void borrowBook(Visitor visitor, String bookList) throws Exception
    {
        String time = LBMS_StatisticsKeeper.Get_Time();
        if(!LBMS_StatisticsKeeper.getIsOpen(LBMS_StatisticsKeeper.Get_Time())){
            throw new Exception("Library is currently closed.");
        }

        String[] bookISBN = bookList.split(",");

        if(bookISBN.length > 5 - visitor.getBorrowed_books().size())
            throw new Exception("borrow,book-limit-exceeded;");

        if(visitor.getBalance() > 0)
            throw new Exception("borrow,outstanding-fine,"+visitor.getBalance()+';');

        ArrayList<String> invalidBookIDs = new ArrayList<>();
        for(int i=0; i<bookISBN.length; ++i)
        {
            if(!this.purchasedBooks.containsKey(bookISBN[i]))
                invalidBookIDs.add(bookISBN[i]);
        }

        if(invalidBookIDs.size() > 0)
        {
            String errorString = "borrow,invalid-book-id,{";
            for(String isbn: invalidBookIDs)
                errorString += isbn+", ";
            errorString = errorString.subSequence(0, errorString.length()-1).toString()+"};";

            throw new Exception(errorString);
        }
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");
        calendar.setTime(dateFormat.parse(time));
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date futureDate = calendar.getTime();
        String futDate = dateFormat.format(futureDate);

        for(String isbn: bookISBN)
            visitor.add_book(new Book_Loan(visitor, this.bookRegistry.get(isbn), 0.0, true,LBMS_StatisticsKeeper.Get_Time(),futDate));

        System.out.println("borrow," + futDate.substring(0,10));
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