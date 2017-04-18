package Client.Visitor;

import Network.Command;

//FILE::CreateAccount_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.16.2017
public class CreateAccount_Command implements Command{
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private int clientID;
    private String username;
    private String password;
    private String role;
    private Long visitorID;

    public CreateAccount_Command(int clientID,String username,String password,String role,Long visitorID){
        this.clientID = clientID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.visitorID = visitorID;
    }

    @Override
    public String execute() {
        try {
            visitorKeeper.createAccount(this.clientID,this.username,this.password,this.role,this.visitorID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
