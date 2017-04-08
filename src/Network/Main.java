package Network;

import Books.LBMS_BookKeeper;
import GUI.Graphics_View;
import Time.LBMS_StatisticsKeeper;
import Client.Visitor.LBMS_VisitorKeeper;

import java.util.ArrayList;

//FILE::Network.Main.java
//AUTHOR::Ryan Connors, Adam Nowak, Kevin Barnett
//DATE::Feb.25.2017
public class Main {

//    protected static LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
//    protected static LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
//    protected static LBMS_StatisticsKeeper statisticsKeeper = LBMS_StatisticsKeeper.getInstance();
    private Client_Access_Point clientPoint = new Client_Access_Point();
    private Client_Access_Command clientCommand = new Client_Access_Command();
    private Graphics_View graphics_view;


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
            String command = clientPoint.getCommand();
            ArrayList<Object> parsedCommand = clientPoint.parseCommand(command);

            try
            {
                Command concreteCommand = clientPoint.ConcreteCommand(parsedCommand);
                clientCommand.receiveCommand(concreteCommand);

                if(args[0].equals("text"))
                    textOutput(clientCommand.executeCommand());
                else
                    graphicsOutput(clientCommand.executeCommand());
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
        System.out.println("Welcome to the Library Books.Book Management System!");
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
