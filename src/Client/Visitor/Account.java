package Client.Visitor;

/**
 * Created by Ryan on 4/7/2017.
 */
public class Account {

    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
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