/**
 * Created by adamn on 3/2/2017.
 */
public class Find_Borrowed_Command implements Command {

        private Visitor v;

    public Find_Borrowed_Command(Visitor v){
        this.v = v;
    }

    public void execute() {
        Main.vk.getVisitorRegistry().get(v.getVisitor_ID()).getBorrowed_books();
    }
}
