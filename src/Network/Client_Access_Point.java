//FILE::Client_Access_Point.java
//AUTHOR: Adam Nowak, Ryan Connors
//DATE::Feb.25.2017
package Network;

import Books.*;
import Client.Visitor.*;
import Network.Command;
import Shutdown.Shut_Down_Command;
import Time.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Client_Access_Point {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
    LBMS_StatisticsKeeper statisticsKeeper = LBMS_StatisticsKeeper.getInstance();

    /**
     * This Function allows the employee to issue a command via scanner
     *
     * @return String command
     */

    public String getCommand() {
        String command;
        System.out.println("Please enter a command.");
        Scanner scan1 = new Scanner(System.in);
        command = scan1.nextLine().toString();
        while (command.charAt(command.length() - 1) != ';') {
            System.out.println("partial-request;");
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

    public ArrayList<Object> parseCommand(String command) throws Exception {
        int commandsize;
        String errormessage;
        Object firstindex;
        Object secondindex;
        ArrayList<Object> thirdindex = new ArrayList<>();

        //deletes the semicolon at the end of the command

        command = command.replace(command.substring(command.length() - 1), "");

        // parses the command differently depending on which command is being invoked.

        ArrayList<Object> parsedcommand = new ArrayList<>();
        int n = 0; // used to point at the first character of an object before it is added into arraylist

        outerloop:
        // The main loop that parses the input command

        for(int i = 0; i < command.length() - 1; i++)
        {
            if(command.charAt(i) == ',' && command.charAt(i + 1) == ' ')
            {
                parsedcommand.add(command.substring(n, i));
                i += 2;
                n = i;
            }else if(command.charAt(i) == ',' && command.charAt(i + 1) == '"')
            {
                parsedcommand.add(command.substring(n, i));
                i += 2;
                n = i;
                for(; i < command.length() - 2; i++)
                {
                    if(command.charAt(i) == '"')
                    {
                        parsedcommand.add(command.substring(n, i));
                        i++;
                        n = i;
                        break;
                    }
                }
            }else if(command.charAt(i) == ',')
            {
                parsedcommand.add(command.substring(n, i));
                i++;
                n = i;
            }

            if(command.charAt(i) == '{')
            {
                i++;
                n = i;
                ArrayList<String> parsedids = new ArrayList<>();
                for(; i < command.length(); i++)
                {
                    if(command.charAt(i) == ',')
                    {
                        parsedids.add(command.substring(n, i));
                        i++;
                        n = i;
                    }
                    if(command.charAt(i) == '}')
                    {
                        parsedids.add(command.substring(n, i));
                        parsedcommand.add(parsedids);
                        if(command.endsWith("}"))
                        {
                            break outerloop;
                        }else if(command.charAt(i + 1) == ',')
                        {
                            i += 2;
                            n = i;
                        }
                        break;
                    }
                }
            }
        }
        /*
         * if statement that handles adding the last string of the arraylist.
         *  This is due to the objects being inputted when a comma is found
         * and the last character of a command doesn't include a comma.
         */

        if(! command.endsWith("}"))
        {

            parsedcommand.add(command.substring(n, command.length()));
        }

        /*
        * Parameter error handling until end of function.
        */
        if(parsedcommand.size() == 1){
            parsedcommand.add("whoops");
        }
        if(parsedcommand.get(1).equals("register"))
        {
            if(parsedcommand.size() < 5)
            {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(1) + ">, missing parameters, {";
                while(commandsize < 5)
                {
                    if(commandsize == 1)
                    {
                        errormessage += "first name, ";
                        commandsize++;
                    }
                    if(commandsize == 2)
                    {
                        errormessage += "last name, ";
                        commandsize++;
                    }
                    if(commandsize == 3)
                    {
                        errormessage += "address, ";
                        commandsize++;
                    }
                    if(commandsize == 4)
                    {
                        errormessage += "phone number}";
                        commandsize++;
                    }
                }
                throw new Exception(errormessage);
            }
        }else if(parsedcommand.get(1).equals("info"))
        {
            if(parsedcommand.size() <= 6)
            {
                if(parsedcommand.size() < 3)
                {
                    commandsize = parsedcommand.size();
                    errormessage = "<" + parsedcommand.get(1) + ">, missing parameters, {";
                    while(commandsize < 3)
                    {
                        if(commandsize == 1)
                        {
                            errormessage += "title, ";
                            commandsize++;
                        }
                        if(commandsize == 2)
                        {
                            errormessage += "authors}";
                            commandsize++;
                        }
                        commandsize++;
                    }
                    throw new Exception(errormessage);
                }
            }
        }else if(parsedcommand.get(1).equals("borrow"))
        {
            if(parsedcommand.size() < 3)
            {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(1) + ">, missing parameters, {id};";
                throw new Exception(errormessage);
            }
        }else if(parsedcommand.get(1).equals("return"))
        {
            if(parsedcommand.size() < 4) {
                ArrayList<String> returntest = (ArrayList) parsedcommand.get(2);
                if (parsedcommand.size() < 4 && returntest.get(0).length() == 10) {
                    errormessage = "<" + parsedcommand.get(1) + ">, missing parameters, {id};";
                    throw new Exception(errormessage);
                }
            }
        }else if(parsedcommand.get(1).equals("pay"))
        {
            if(parsedcommand.size() < 3)
            {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {amount};";
                throw new Exception(errormessage);
            }
        }else if(parsedcommand.get(1).equals("search"))
        {
            if(parsedcommand.size() < 2)
            {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {title};";
                throw new Exception(errormessage);
            }
        }else if(parsedcommand.get(1).equals("buy"))
        {
            if(parsedcommand.size() < 3)
            {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while(commandsize < 3)
                {
                    if(commandsize == 1)
                    {
                        errormessage += "quantity, ";
                        commandsize++;
                    }
                    if(commandsize == 2)
                    {
                        errormessage += "id}; ";
                        commandsize++;
                    }
                }
                throw new Exception(errormessage);
            }
        }else if(parsedcommand.get(1).equals("advance"))
        {
            if(parsedcommand.size() < 2)
            {
                errormessage = "<" + parsedcommand.get(1) + ">, missing parameters, {number-of-days};";
                throw new Exception(errormessage);
            }
        }
        else if(parsedcommand.get(1).equals("datetime") || parsedcommand.get(1).equals("shutdown")
                || parsedcommand.get(1).equals("report") || parsedcommand.get(0).equals("connect")
                || parsedcommand.get(1).equals("create") || parsedcommand.get(1).equals("disconnect")
                || parsedcommand.get(1).equals("login") || parsedcommand.get(1).equals("logout")
                ||parsedcommand.get(1).equals("undo") || parsedcommand.get(1).equals("redo")
                || parsedcommand.get(1).equals("service") || parsedcommand.get(1).equals("arrive")
                || parsedcommand.get(1).equals("depart") || parsedcommand.get(1).equals("borrowed"))
        {
            return parsedcommand;

            /*
             * Else statement handles an error where request command doesn't exist.
             *
             */
        }
        else {
            errormessage = "Requested command doesn't exist.";
            throw new Exception(errormessage);
        }
        /*
         *  Fixes parsing for buy and return command
         */
        if (parsedcommand.get(1).equals("buy") || parsedcommand.get(1).equals("return")) {
            firstindex = parsedcommand.get(1);
            secondindex = parsedcommand.get(2);
            for (int i = 3; i < parsedcommand.size(); i++) {
                thirdindex.add(parsedcommand.get(i));
            }
            parsedcommand.clear();
            parsedcommand.add(firstindex);
            parsedcommand.add(secondindex);
            parsedcommand.add(thirdindex);
        }
        return parsedcommand;
    }

    public Command ConcreteCommand(ArrayList parsedcommand) throws Exception
    {
        Command cmd;
        Long visitorID = 0L;
        HashMap<Integer,Account> connections = LBMS_VisitorKeeper.getActiveConnections();
        if(parsedcommand.get(0).equals("connect")) {
            cmd = new Connect_Command();
            return cmd;
        }else {
            if (!connections.containsKey(Integer.parseInt(parsedcommand.get(0).toString()))) {
                throw new Exception("invalid-client-id;");
            }
            switch (parsedcommand.get(1).toString()) {
                case "disconnect":
                    cmd = new Disconnect_Command(Integer.parseInt(parsedcommand.get(0).toString()));
                    break;
                case "login":
                    cmd = new Login_Command(Integer.parseInt(parsedcommand.get(0).toString()), parsedcommand.get(2).toString(), parsedcommand.get(3).toString());
                    break;
                case "logout":
                    cmd = new Logout_Command(Integer.parseInt(parsedcommand.get(0).toString()));
                    break;
                case "create":
                    cmd = new CreateAccount_Command(Integer.parseInt(parsedcommand.get(0).toString()), parsedcommand.get(2).toString(), parsedcommand.get(3).toString(), parsedcommand.get(4).toString(), Long.parseLong(parsedcommand.get(5).toString()));
                    break;
                case "register":
                    cmd = new Register_Command(parsedcommand.get(2).toString(), parsedcommand.get(3).toString(), parsedcommand.get(4).toString(), parsedcommand.get(5).toString());
                    break;
                case "arrive":
                    if(parsedcommand.size() < 3) {
                        for (Integer key : connections.keySet()) {
                            if (key.equals(Integer.parseInt(parsedcommand.get(0).toString()))) {
                                visitorID = connections.get(key).getVisitorID();
                            }
                        }
                    }else{
                        visitorID = Long.parseLong(parsedcommand.get(2).toString());
                    }
                    cmd = new Begin_Visit_Command(visitorID, false);
                    break;
                case "depart":
                    if(parsedcommand.size() < 3) {
                        for (Integer key : connections.keySet()) {
                            if (key.equals(Integer.parseInt(parsedcommand.get(0).toString()))) {
                                visitorID = connections.get(key).getVisitorID();
                            }
                        }
                    }else{
                        visitorID = Long.parseLong(parsedcommand.get(2).toString());
                    }
                    cmd = new End_Visit_Command(visitorID, false);
                    break;
                case "info":
                    cmd = new Book_Search_Command(parsedcommand);
                    break;
                case "borrow":
                    cmd = new Borrow_Command(Long.parseLong(parsedcommand.get(2).toString()), (ArrayList) parsedcommand.get(3), false);
                    break;
                case "borrowed":
                    cmd = new Find_Borrowed_Command(Long.parseLong(parsedcommand.get(2).toString()));
                    break;
                case "return":
                    cmd = new Return_Command(Long.parseLong(parsedcommand.get(2).toString()), (ArrayList) parsedcommand.get(3), false);
                    break;
                case "pay":
                    cmd = new Pay_Fine_Command(Long.parseLong(parsedcommand.get(2).toString()), Double.parseDouble(parsedcommand.get(3).toString()), false);
                    break;
                case "search":
                    cmd = new Book_Store_Command(parsedcommand);
                    break;
                case "buy":
                    cmd = new Book_Purchase_Command(Integer.parseInt(parsedcommand.get(2).toString()), (ArrayList) parsedcommand.get(3), false);
                    break;
                case "advance":
                    int day = Integer.parseInt(parsedcommand.get(2).toString());
                    int hour;
                    if (parsedcommand.size() < 4)
                        hour = 0;
                    else
                        hour = Integer.parseInt(parsedcommand.get(3).toString());

                    cmd = new Advance_Time_Command(day, hour);
                    break;
                case "datetime":
                    cmd = new Current_Time_Command();
                    break;
                case "report":
                    int days;
                    if (parsedcommand.size() < 2) {
                        days = 100000;
                    } else {
                        days = Integer.parseInt(parsedcommand.get(1).toString());
                    }
                    cmd = new Statistics_Report_Command(days);
                    break;
                case "undo":
                    cmd = new Undo_Command();
                    break;
                case "redo":
                    cmd = new Redo_Command();
                    break;
                case "shutdown":
                    cmd = new Shut_Down_Command();
                    break;
                default:
                    return null;
            }
        }
            return cmd;
    }
}