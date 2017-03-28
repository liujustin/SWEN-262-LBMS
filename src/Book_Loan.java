//FILE::Book_Loan.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Book_Loan {
    private Visitor loaned_to;
    private Book book;
    private double balance;
    private String start_date;
    private String due_date;

    public Book_Loan(Visitor loaned_to, Book book, double balance, String start_date, String due_date) {
        this.loaned_to = loaned_to;
        this.book = book;
        this.balance = balance;
        this.start_date = start_date;
        this.due_date = due_date;
    }

    /**
     * @return loaned_to
     */
    public Visitor getLoaned_to() {
        return loaned_to;
    }

    /**
     * @param loaned_to
     */
    public void setLoaned_to(Visitor loaned_to) {
        this.loaned_to = loaned_to;
    }

    /**
     * @return book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @return start date
     */
    public String getStart_date(){ return start_date;}

    /**
     *
     * @param start_date
     */
    public void setStart_date(String start_date){ this.start_date = start_date;}

    /**
     *
     * @return due_date
     */
    public String getDue_date(){ return due_date;}

    /**
     *
     * @param due_date
     */
    public void setDue_date(String due_date){ this.due_date = due_date;}

    /**
     *
     * @param v
     * @return Total fines for a visitor
     */
    public double getFine(Visitor v) throws ParseException {
        double visitor_balance = 0;
        ArrayList loaned_books = v.getBorrowed_books();
        for(int i = 0; i > loaned_books.size(); i++) {
            Book_Loan b = (Book_Loan) loaned_books.get(i);//get the due date of the book
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date d = dateFormat.parse(LBMS_StatisticsKeeper.Get_Time());
            if(dateFormat.parse(b.getDue_date()).before(d)) { // check if due date is before current date
                b.balance += 10; //if 1 day late add $10 to balance
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            Date futureDate = calendar.getTime();
            while(futureDate.after(dateFormat.parse(b.getDue_date()))){// if the date is a week past the due date  (current date + week is after the due date)
                if( b.balance > 28) {
                    break;
                }
                b.balance += 2;
                calendar.add(Calendar.DAY_OF_YEAR, 7);
                futureDate = calendar.getTime();
            }
            visitor_balance += b.balance;
        }
        v.setBalance(visitor_balance); //sets visitor balance
        return visitor_balance;
    }
}