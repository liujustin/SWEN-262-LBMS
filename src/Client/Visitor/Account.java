package Client.Visitor;

import Network.Main;

import java.util.HashMap;

/**
 * Created by Ryan on 4/7/2017.
 */
public class Account {

    public static HashMap<String, Account> activeAccounts = new HashMap<>();

    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private String username;
    private String password;
    private int role;
    private long visitorID;
    private Visitor v;

    public Account(String username, String password, int role, long visitorID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.visitorID = visitorID;
        this.v = visitorKeeper.getVisitorRegistry().get(this.visitorID);
    }

    public Account createAccount(String username, String password, int role, long visitorID){
        Account newAccount = new Account(username,password,role,visitorID);
        System.out.println("create,success");
        return newAccount;
    }
}