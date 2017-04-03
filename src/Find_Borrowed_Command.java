//FILE::Find_Borrowed_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Find_Borrowed_Command implements Command {

    private Visitor v;

    /**
     *
     * @param v
     */
    public Find_Borrowed_Command(Visitor v){
        this.v = v;
    }

    @Override
    public String execute() {
        Main.vk.getVisitorRegistry().get(v.getVisitor_ID()).getBorrowed_books();
        return "";
    }
}
