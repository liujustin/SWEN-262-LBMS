import java.util.ArrayList;
import java.util.Objects;

//FILE::Book_Loan.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017
public class Main {

    public static LBMS_VisitorKeeper vk = new LBMS_VisitorKeeper();
    public static LBMS_BookKeeper bk = new LBMS_BookKeeper();
    public static LBMS_StatisticsKeeper sk = new LBMS_StatisticsKeeper();
    public static Client_Access_Point cap = new Client_Access_Point();
    public static Client_Access_Command cac = new Client_Access_Command();

    /**
     *
     * @param args
     * The main in which runs the system
     *
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Library Book Management System!");
        System.out.println("Here are the available commands!");
        System.out.println("Commands:");
        System.out.println();
        System.out.println("register, \t arrive, \t depart");
        System.out.println("info, \t     borrow, \t borrowed");
        System.out.println("return, \t pay, \t     search");
        System.out.println("buy, \t     advance, \t datetime");
        System.out.println("report, \t shutdown");
        System.out.println();

        while (true){
            String command = cap.getCommand();
            ArrayList<Object> parsedCommand = cap.parseCommand(command);
            try {
                Command concreteCommand = cap.ConcreteCommand(parsedCommand);
                cac.receiveCommand(concreteCommand);
                cac.executeCommand();
            }catch(NullPointerException e){}
        }
    }
}
