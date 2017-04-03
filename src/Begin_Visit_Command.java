//FILE::Begin_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017
public class Begin_Visit_Command implements Command {
    private Long  vID;

    public Begin_Visit_Command(Long vID){
        this.vID = vID;
    }


    @Override
    public void execute() {
        try {
            Main.vk.beginVisit(this.vID);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
