import java.util.ArrayList;

//FILE::Return_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017
public class Return_Command implements Command {
    private Long visitor_ID;
    private ArrayList<String> ISBNS;

    public Return_Command(Long visitor_ID,ArrayList<String> ISBNS){
        this.visitor_ID = visitor_ID;
        this.ISBNS = ISBNS;

    }

    @Override
    public String execute() {
        try {
            Main.vk.returnBook(this.visitor_ID,this.ISBNS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
