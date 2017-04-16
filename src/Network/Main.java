package Network;

import GUI.Graphics_View;

import java.util.ArrayList;

//FILE::Network.Main.java
//AUTHOR::Ryan Connors, Adam Nowak, Kevin Barnett, Justin Liu
//DATE::Feb.25.2017
public class Main
{

    private Client_Access_Point clientPoint = new Client_Access_Point();
    private Client_Access_Invoker clientCommand = new Client_Access_Invoker();
    private Graphics_View graphics_view;


    private void graphicsOutput(String outPut)
    {
        System.out.println(outPut);
    }

    public void textOutput(String outPut)
    {
        System.out.println(outPut);
    }

    public void startLoop(String[] args, Graphics_View graphics_view)
    {
        this.graphics_view = graphics_view;

        String command;

        while(true)
        {
            if(args[0].equals("text"))
                command = clientPoint.getCommand();
            else
                command = this.graphics_view.getCommand();

            if(!command.equals(""))
            {ArrayList<Object> parsedCommand = clientPoint.parseCommand(command);

            try
            {
                Command concreteCommand = clientPoint.ConcreteCommand(parsedCommand);
                clientCommand.receiveCommand(concreteCommand);

                if(args[0].equals("text"))
                    textOutput(clientCommand.executeCommand());
                else
                    graphicsOutput(clientCommand.executeCommand());
            }
            catch(NullPointerException e){}}
        }
    }

    /**
     *
     * @param args
     * The main method that prints to the console and presents the user with commands.
     *
     */
    public static void main(String[] args)
    {
        Main main = new Main();

        if(args[0].equals("text"))
        {
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

            main.startLoop(args, null);
        }
        else
            Graphics_View.load(args, main);
    }
}
