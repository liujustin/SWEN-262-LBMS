//FILE::Visitor.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

public class Visitor
{
    private String first_name;
    private String last_name;
    private String address;
    private double balance;
    private String phone_number;
    private int visitor_ID;

    public Visitor(String first_name, String last_name, String address,
                   double balance, String phone_number, int visitor_ID) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.balance = balance;
        this.phone_number = phone_number;
        this.visitor_ID = visitor_ID;
    }


}
