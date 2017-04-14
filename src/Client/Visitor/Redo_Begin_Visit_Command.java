package Client.Visitor;

import Network.Command;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Redo_Begin_Visit_Command implements Command{
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;

    public Redo_Begin_Visit_Command(Long visitorID){
        this.visitorID = visitorID;
    }


    @Override
    public String execute() {
        try {
            return visitorKeeper.beginVisit(this.visitorID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
