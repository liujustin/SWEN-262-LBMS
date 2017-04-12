package Client.Visitor;

import Network.Command;

//FILE::Client.Visitor.Begin_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017
public class Begin_Visit_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private Long visitorID;

    public Begin_Visit_Command(Long visitorID){
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
