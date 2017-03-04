//FILE::Visitor.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

import java.util.ArrayList;

public class Visitor
{
    private String first_name;
    private String last_name;
    private String address;
    private double balance;
    private String phone_number;
    private int visitor_ID;
    private ArrayList<Book_Loan> borrowed_books = new ArrayList<Book_Loan>();

    public Visitor(String first_name, String last_name, String address,
                   double balance, String phone_number, int visitor_ID) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.balance = balance;
        this.phone_number = phone_number;
        this.visitor_ID = visitor_ID;
        this.borrowed_books = borrowed_books;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getVisitor_ID() {
        return visitor_ID;
    }

    public void setVisitor_ID(int visitor_ID) {
        this.visitor_ID = visitor_ID;
    }

    public ArrayList<Book_Loan> getBorrowed_books() {
        return borrowed_books;
    }

    public void add_book(Visitor visitor,Book_Loan book) {
        visitor.borrowed_books.add(book);
    }
}
