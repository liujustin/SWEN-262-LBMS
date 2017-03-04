//FILE::Client_Access_Point.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

import Executable_Commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

/*
    I have no idea what I'm supposed to be doing with this class but I did something
            - Adam
 */

public class Client_Access_Point extends Observable {
    Command c;

    /*
    This Function allows the employee to issue a command via scanner
     */
    public static String getCommand() {
        String command;
        System.out.println("Please enter a command.");
        Scanner scan1 = new Scanner(System.in);
        command = scan1.nextLine().toString();
        while (command.charAt(command.length() - 1) != ';') {
            System.out.println("Please finish entering command.");
            Scanner line = new Scanner(System.in);
            command = command + line.nextLine().toString();
        }
        return command;
    }

    /*
        Parses the Command the user inputs from getCommand()
        At the moment, cannot parse commands which contain any sort of brackets
     */
    public static ArrayList parseCommand(String command) {
        command = command.replace(command.substring(command.length() - 1), "");
        if (command.startsWith("borrow")) {
            ArrayList<Object> parsedcommand = new ArrayList<>();
            int n = 0;
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == ',') {
                    parsedcommand.add(command.substring(n, i));
                    i++;
                    n = i;
                }
                if (command.charAt(i) == '{') {
                    i++;
                    n = i;
                    ArrayList<String> parsedids = new ArrayList<>();
                    for ( ; i < command.length(); i++) {
                        if (command.charAt(i) == ',') {
                            parsedids.add(command.substring(n, i ));
                            i++;
                            n = i;
                        }
                        if (command.charAt(i) == '}') {
                            parsedids.add(command.substring(n, i ));
                            parsedcommand.add(parsedids);
                            break;
                        }
                    }
                }
            }
            return parsedcommand;
        }
        else {
            ArrayList<String> parsedcommand = new ArrayList<>(Arrays.asList(command.split(",")));
            return parsedcommand;
            }
    }

    public static void main(String[] args) {
        System.out.println(parseCommand(getCommand()));
    }
        /*
        call super.update when done parsing
         */


    /*
        Executes the command that was parsed by parseCommand.
        The way each case is executed is not implemented yet. The execute
        functions in the Executable_Commands package needs to be implemented
        as well as paramaters need to be placed within the cases below.


        http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/LBMS-client-request-format.html
     */
    public void handler(ArrayList<String> parsedString) {


        switch (parsedString.get(0)) {

            case "register":
                c = new Register_Command(/*parsedString.get(1), ...*/);

            case "arrive":
                c = new Begin_Visit_Command();

            case "depart":
                c = new End_Visit_Command();

            case "info":
                c = new Book_Search_Command();

            case "borrow":
                c = new Borrow_Command();

            case "borrowed":
                c = new Find_Borrowed_Command();

            case "return":
                c = new Return_Command();

            case "pay":
                c = new Pay_Fine_Command();

            case "search":
                c = new Book_Store_Command();

            case "buy":
                c = new Book_Purchase_Command();

            case "advance":
                c = new Advance_Time_Command();

            case "datetime":
                c = new Current_Time_Command();

            case "report":
                c = new Library_Report_Command();
        }

        c.execute();
    }

}