//FILE::Book_Loan.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

public class Book_Loan
{
    private Visitor loaned_to;
    private Book book;
    private double balance;
    private boolean active_balance;

    public Book_Loan(Visitor loaned_to, Book book, double balance, boolean active_balance) {
        this.loaned_to = loaned_to;
        this.book = book;
        this.balance = balance;
        this.active_balance = active_balance;
    }
}