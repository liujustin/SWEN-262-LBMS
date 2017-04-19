package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.Connect_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.16.2017

public class Connect_Command implements Command {
    Visitor_Operations visitorOperations = Visitor_Operations.getInstance();

    @Override
    public String execute() {
        visitorOperations.loadAccounts();
        System.out.println(visitorOperations.startConnection());
        return "";
    }
}
