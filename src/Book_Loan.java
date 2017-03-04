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

    public Visitor getLoaned_to() {
        return loaned_to;
    }

    public void setLoaned_to(Visitor loaned_to) {
        this.loaned_to = loaned_to;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActive_balance() {
        return active_balance;
    }

    public void setActive_balance(boolean active_balance) {
        this.active_balance = active_balance;
    }
}