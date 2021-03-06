package Network;

import Client.Visitor.Visitor;
import Client.Visitor.Visitor_Operations;
import GUI.Connect_View;

import java.util.ArrayList;
import java.util.Scanner;

//FILE::Network.Client_Access_View.java
//AUTHOR::Ryan Connors, Adam Nowak, Kevin Barnett, Justin Liu
//DATE::Feb.25.2017
public class Client_Access_View
{

    private Client_Access_Point clientPoint = new Client_Access_Point();
    private Client_Access_Invoker clientCommand = new Client_Access_Invoker();


    private void graphicsOutput(String outPut)
    {
        System.out.println(outPut);
    }

    public void textOutput(String outPut)
    {
        System.out.println(outPut);
    }

    public void startLoop()
    {
        String command;

        while(true)
        {//This loop allows the system to continually ask for input by the user. The data input will be parsed into commands.
            command = clientPoint.getCommand();
            if(!command.equals(""))
            {
                ArrayList<Object> parsedCommand = null;
                try {
                    parsedCommand = clientPoint.parseCommand(command);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try
                {
                Command concreteCommand = clientPoint.ConcreteCommand(parsedCommand);
                clientCommand.receiveCommand(concreteCommand);
                textOutput(clientCommand.executeCommand());
                }
                catch(NullPointerException e){} catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
        Client_Access_View clientAccessView = new Client_Access_View();

        System.out.println("Would you like to use the gui?");
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        if(response.toLowerCase().equals("yes")) {
            Connect_View.main(args);
        }
        else{
            System.out.println("Welcome to the Library Book Management System!");
            System.out.println("Please connect and then login.");
            System.out.println();
            clientAccessView.startLoop();
        }
    }
}
