//FILE::Visitor.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017

import java.util.ArrayList;

public class Visitor
{
    private String first_name;
    private String last_name;
    private String address;
    private double balance;
    private String phone_number;
    private long visitor_ID;
    private ArrayList<Book_Loan> borrowed_books = new ArrayList<Book_Loan>();

    /**
     *
     * @param first_name
     * @param last_name
     * @param address
     * @param balance
     * @param phone_number
     * @param visitor_ID
     */
    public Visitor(String first_name, String last_name, String address,
                   double balance, String phone_number, long visitor_ID) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.balance = balance;
        this.phone_number = phone_number;
        this.visitor_ID = visitor_ID;
        this.borrowed_books = borrowed_books;
    }

    /**
     *
     * @return first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     *
     * @param first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     *
     * @return last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     *
     * @param last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @return phone number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     *
     * @param phone_number
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     *
     * @return visitor id
     */
    public long getVisitor_ID() {
        return visitor_ID;
    }

    /**
     *
     * @param visitor_ID
     */
    public void setVisitor_ID(int visitor_ID) {
        this.visitor_ID = visitor_ID;
    }

    /**
     *
     * @return array of borrowed books
     */
    public ArrayList<Book_Loan> getBorrowed_books() {
        return borrowed_books;
    }

    /**
     *
     * @param book
     */
    public void add_book(Book_Loan book) {
        this.borrowed_books.add(book);
    }

    /**
     *
     * @param visitor
     * @param book
     */
    public void remove_book(Visitor visitor,Book_Loan book){
            if(visitor.borrowed_books.contains(book)){
                visitor.borrowed_books.remove(book);
            }
    }

    /**
     *
     * @return a visitor in string format
     */
    public String toString()
    {
        String visitorString = String.format("%s:%s:%s:%f:%s:%d",
                                             this.first_name,
                                             this.last_name,
                                             this.address,
                                             this.balance,
                                             this.phone_number,
                                             this.visitor_ID);

        return visitorString;
    }
}
