import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Ryan on 3/19/2017.
 */
public class Main {
    public static LBMS_VisitorKeeper vk = new LBMS_VisitorKeeper();
    public static LBMS_BookKeeper bk = new LBMS_BookKeeper();
    public static LBMS_StatisticsKeeper sk = new LBMS_StatisticsKeeper();
    public static Client_Access_Point cap = new Client_Access_Point();
    public static Client_Access_Command cac = new Client_Access_Command();

    public static void main(String[] args) {

        while (true){
            String command = cap.getCommand();
            ArrayList<Object> parsedCommand = cap.parseCommand(command);
            Command concreteCommand = cap.ConcreteCommand(parsedCommand);
            cac.receiveCommand(concreteCommand);
            cac.executeCommand();
        }

    }
}
