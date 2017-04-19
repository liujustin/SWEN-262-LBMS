package Client.Visitor;

//FILE:: Logout_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.16.2017

import Network.Command;

public class Logout_Command implements Command
{
    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();
    private int clientID;

    /**
     *
     * @param clientID
     */
    public Logout_Command(int clientID){
        this.clientID = clientID;
    }

    @Override
    public String execute()
    {
        try
        {
            visitorKeeper.logoutAccount(this.clientID);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
