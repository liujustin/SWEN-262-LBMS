package Client.Visitor;

import Network.Command;

/**
 * Created by adamn on 4/16/2017.
 */
public class Disconnect_Command implements Command{
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private int clientID;

    public Disconnect_Command(int clientID){
        this.clientID = clientID;
    }
    @Override
    public String execute() {
        try {
            System.out.println(visitorKeeper.disconnectConnection(this.clientID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
