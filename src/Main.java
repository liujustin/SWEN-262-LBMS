import java.util.ArrayList;
import java.util.Objects;

//FILE::Main.java
//AUTHOR::Ryan Connors, Adam Nowak, Kevin Barnett
//DATE::Feb.25.2017
public class Main {

    protected static LBMS_VisitorKeeper vk = new LBMS_VisitorKeeper();
    protected static LBMS_BookKeeper bk = new LBMS_BookKeeper();
    protected static LBMS_StatisticsKeeper sk = new LBMS_StatisticsKeeper();
    private Client_Access_Point cap = new Client_Access_Point();
    private Client_Access_Command cac = new Client_Access_Command();

    private Graphics_View graphics_view;

    public LBMS_VisitorKeeper getVk()
    {
        return this.vk;
    }
    public LBMS_BookKeeper getBk()
    {
        return this.bk;
    }
    public LBMS_StatisticsKeeper getSk()
    {
        return this.sk;
    }

    private void graphicsOutput(String outPut)
    {
        System.out.println(outPut);
    }

    public void textOutput(String outPut)
    {
        System.out.println(outPut);
    }

    public void startLoop(String[] args, Graphics_View graphics_view) throws Exception
    {
        this.graphics_view = graphics_view;
        while(true)
        {
            String command = cap.getCommand();
            ArrayList<Object> parsedCommand = cap.parseCommand(command);

            try
            {
                Command concreteCommand = cap.ConcreteCommand(parsedCommand);
                cac.receiveCommand(concreteCommand);

                if(args[0].equals("text"))
                    textOutput(cac.executeCommand());
                else
                    graphicsOutput(cac.executeCommand());
            }
            catch(NullPointerException e){}
        }
    }

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

        Main main = new Main();

        Graphics_View.load(args, main);
    }
}
