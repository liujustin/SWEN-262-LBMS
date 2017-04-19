package Books;
//FILE::Books.Book_Loan.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017

import Time.Time_Operations;
import Client.Visitor.Visitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Book_Loan {
    private Visitor loaned_to;
    private Book book;
    private double balance;
    private boolean is_active;
    private String start_date;
    private String due_date;

    /**
     * Book loan constructor that takes in parameters and creates a Book_Loan
     * @param loaned_to
     * @param book
     * @param balance
     * @param is_active
     * @param start_date
     * @param due_date
     */
    public Book_Loan(Visitor loaned_to, Book book, double balance, boolean is_active, String start_date, String due_date) {
        this.loaned_to = loaned_to;
        this.book = book;
        this.balance = balance;
        this.is_active = is_active;
        this.start_date = start_date;
        this.due_date = due_date;

    }

    /**
     * A getter method for returning the visitor the Book_Loan is loaned to
     * @return loaned_to
     */
    public Visitor getLoaned_to() {
        return loaned_to;
    }

    /**
     * A setter method that sets the visitor the book_loan is loaned to
     * @param loaned_to
     */
    public void setLoaned_to(Visitor loaned_to) {
        this.loaned_to = loaned_to;
    }

    /**
     * A getter method that returns the book for the book_loan
     * @return book
     */
    public Book getBook() {
        return book;
    }

    /**
     * A setter method that sets what book applied to the book_loan
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * A getter that returns the fine/balance for the book that's loaned out
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * A setter that sets the fine/balance for the book that's loaned out
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }


    /**
     * A boolean that returns whether or not the book_loan is active
     * @return
     */
    public boolean getIs_Active(){ return is_active;}

    /**
     * A setter that sets a boolean for whether or not the book_loan is active
     * @param is_active
     */
    public void setIs_Active(boolean is_active){ this.is_active = is_active;}

    /**
     * A getter that returns a string for the start date of the book_loan
     * @return
     */
    public String getStart_date(){ return start_date;}

    /**
     * A setter that sets a string for the start date of the book_loan
     * @param start_date
     */
    public void setStart_date(String start_date){ this.start_date = start_date;}

    /**
     * A getter that gets the due date for the book_loan
     * @return due_date
     */
    public String getDue_date(){ return due_date;}

    /**
     * A setter that sets the due date for the book_loan
     * @param due_date
     */
    public void setDue_date(String due_date){ this.due_date = due_date;}

    /**
     * A getter that returns the fines on the book
     * @param v
     * @return
     * @throws ParseException
     */
    public double getFine(Visitor v) throws ParseException {
        double visitor_balance = 0;
        ArrayList loaned_books = v.getBorrowed_books();
        for(int i = 0; i > loaned_books.size(); i++) {
            Book_Loan b = (Book_Loan) loaned_books.get(i);//get the due date of the book
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");
            Date d = dateFormat.parse(Time_Operations.Get_Time());
            if(dateFormat.parse(b.getDue_date()).before(d)) { // check if due date is before current date
                b.balance += 10; //if 1 day late add $10 to balance
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            Date futureDate = calendar.getTime();
            while(futureDate.after(dateFormat.parse(b.getDue_date())) && b.is_active ){// if the date is a week past the due date  (current date + week is after the due date)
                if( b.balance > 28) {
                    b.balance = 30;
                    break;
                }
                b.balance += 2;
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                futureDate = calendar.getTime();
            }
            visitor_balance += b.balance;
        }
        return visitor_balance;
    }

    /**
     * Shutdown method to save the book_loans in a persistent state
     * @param visitorRegistry
     */
    public static void saveBookLoans(HashMap<Long, Visitor> visitorRegistry)
    {
        try
        {
            PrintStream saveState = new PrintStream(new FileOutputStream(new File("borrowBooks.log")));
            saveState.flush();

            for(Visitor v: visitorRegistry.values())
                for(Book_Loan b: v.getBorrowed_books()) {
                    saveState.println(b.toString());
                }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * A toSTring method for the Book_loan object
     * @return
     */
    public String toString() {
        String book_loanString = String.format("%s:%s:%s:%s:%s\n",
                this.loaned_to.getVisitor_ID(),
                this.book.getBookIsbn(),
                Double.toString(this.balance),
                this.start_date,
                this.due_date);

        return book_loanString;
    }
}