package Client.Visitor;

import Network.Command;

/**
 * Created by adamn on 4/16/2017.
 */
public class Connect_Command implements Command {
    Visitor_Operations visitorKeeper = Visitor_Operations.getInstance();

    @Override
    public String execute() {
        System.out.println(visitorKeeper.startConnection());
        return "";
    }
}
