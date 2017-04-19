package Client.Visitor;

//FILE::Client.Visitor.Account.java
//AUTHOR::Ryan Connors
//DATE::Apr.7.2017
public class Account
{

    Visitor_Operations visitorOperations = Visitor_Operations.getInstance();
    private String username;
    private String password;
    private String role;
    private long visitorID;
    private Visitor v;

    /**
     *
     * @param username
     * @param password
     * @param role
     * @param visitorID
     */
    public Account(String username, String password, String role, long visitorID)
    {
        this.username = username;
        this.password = password;
        this.role = role;
        this.visitorID = visitorID;
        this.v = visitorOperations.getVisitorRegistry().get(this.visitorID);
    }
    // getter methods for Account
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