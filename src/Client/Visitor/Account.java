package Client.Visitor;

//FILE::Account.java
//AUTHOR::Ryan Connors
//DATE::Apr.7.2017
public class Account {

    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
    private String username;
    private String password;
    private String role;
    private long visitorID;
    private Visitor v;

    public Account(String username, String password, String role, long visitorID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.visitorID = visitorID;
        this.v = visitorKeeper.getVisitorRegistry().get(this.visitorID);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    public long getVisitorID(){
        return visitorID;
    }

}