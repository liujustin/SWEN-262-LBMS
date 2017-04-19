package Client.Visitor;

import Network.Command;

//FILE::Disconnect.java
//AUTHOR:: Adam Nowak
//DATE::Apr.16.2017

public class Disconnect_Command implements Command
{
    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
    private int clientID;

    public Disconnect_Command(int clientID){
        this.clientID = clientID;
    }
    @Override
    public String execute()
    {
        try
        {
            System.out.println(visitorKeeper.disconnectConnection(this.clientID));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
