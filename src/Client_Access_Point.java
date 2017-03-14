//FILE::Client_Access_Point.java
//AUTHOR::Kevin.P.Barnett , Adam Nowak
//DATE::Feb.25.2017

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

//http://www.se.rit.edu/~swen-262/projects/design_project/ProjectDescription/LBMS-client-request-format.html

public class Client_Access_Point extends Observable {



    /**
     * This Function allows the employee to issue a command via scanner
     *
     * @return String command
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



    /**
     * Parses the Command the user inputs from getCommand()
     *
     * @param command
     * @return Arraylist of objects
     */
    public static ArrayList parseCommand(String command) {

        //deletes the semicolon at the end of the command

        command = command.replace(command.substring(command.length() - 1), "");

        // parses the command differently depending on which command is being invoked.

        ArrayList<Object> parsedcommand = new ArrayList<>();
        int n = 0; // used to point at the first character of an object before it is added into arraylist

        outerloop: // The main loop that parses the input command

        for (int i = 0; i < command.length() - 2; i++) {
            if (command.charAt(i) == ',' && command.charAt(i + 1) == ' '){
                parsedcommand.add(command.substring(n ,i));
                i += 2;
                n = i;
            }
            else if (command.charAt(i) == ',' && command.charAt(i + 1) == '"'){
                parsedcommand.add(command.substring(n,i));
                i += 2;
                n = i;
                for ( ; i < command.length() - 2; i++) {
                    if (command.charAt(i) == '"'){
                        parsedcommand.add(command.substring(n,i));
                        i++;
                        n = i;
                        break;
                    }
                }
            }
            else if (command.charAt(i) == ',') {
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
                        parsedids.add(command.substring(n, i));
                        i++;
                        n = i;
                    }
                    if (command.charAt(i) == '}') {
                        parsedids.add(command.substring(n, i));
                        parsedcommand.add(parsedids);
                        if ( command.endsWith("}")){
                            break outerloop;
                        }
                        else if( command.charAt(i + 1) == ','){
                            i += 2;
                            n = i;
                        }
                        break;
                    }
                }
            }
        }
        /* if statement that handles adding the last string of the arraylist.
         *  This is due to the objects being inputted when a comma is found
         * and the last character of a command doesn't include a comma.
         */
        if(!command.endsWith("}")){

            parsedcommand.add(command.substring(n, command.length()));
        }

        return parsedcommand;
        //call super.update when done parsing
    }





    public static void main(String[] args) {  //delete when fully implemented and remove static from functions
        System.out.println(parseCommand(getCommand()));
    }

}