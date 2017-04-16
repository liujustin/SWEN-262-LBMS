package Client.Visitor;

import Network.Command;

//FILE:: Login_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.16.2017
public class Login_Command implements Command{
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private int clientID;
    private String username;
    private String password;

    public Login_Command(int clientID, String username, String password){
        this.clientID = clientID;
        this.username = username;
        this.password = password;
    }
    @Override
    public String execute() {
        try {
            visitorKeeper.loginAccount(this.clientID,this.username,this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
