//FILE::Client_Access_Point.java
//AUTHOR: Adam Nowak, Ryan Connors
//DATE::Feb.25.2017

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Client_Access_Point {


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

    public ArrayList parseCommand(String command) {
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

        for (int i = 0; i < command.length() - 1; i++) {
            if (command.charAt(i) == ',' && command.charAt(i + 1) == ' ') {
                parsedcommand.add(command.substring(n, i));
                i += 2;
                n = i;
            } else if (command.charAt(i) == ',' && command.charAt(i + 1) == '"') {
                parsedcommand.add(command.substring(n, i));
                i += 2;
                n = i;
                for (; i < command.length() - 2; i++) {
                    if (command.charAt(i) == '"') {
                        parsedcommand.add(command.substring(n, i));
                        i++;
                        n = i;
                        break;
                    }
                }
            } else if (command.charAt(i) == ',') {
                parsedcommand.add(command.substring(n, i));
                i++;
                n = i;
            }

            if (command.charAt(i) == '{') {
                i++;
                n = i;
                ArrayList<String> parsedids = new ArrayList<>();
                for (; i < command.length(); i++) {
                    if (command.charAt(i) == ',') {
                        parsedids.add(command.substring(n, i));
                        i++;
                        n = i;
                    }
                    if (command.charAt(i) == '}') {
                        parsedids.add(command.substring(n, i));
                        parsedcommand.add(parsedids);
                        if (command.endsWith("}")) {
                            break outerloop;
                        } else if (command.charAt(i + 1) == ',') {
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

        if (!command.endsWith("}")) {

            parsedcommand.add(command.substring(n, command.length()));
        }

        /*
        * Parameter error handling until end of function.
        */

        if (parsedcommand.get(0).equals("register")) {
            if (parsedcommand.size() < 5) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 5) {
                    if (commandsize == 1) {
                        errormessage += "first name, ";
                        commandsize++;
                    }
                    if (commandsize == 2) {
                        errormessage += "last name, ";
                        commandsize++;
                    }
                    if (commandsize == 3) {
                        errormessage += "address, ";
                        commandsize++;
                    }
                    if (commandsize == 4) {
                        errormessage += "phone number}";
                        commandsize++;
                    }
                }
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("arrive")
                || parsedcommand.get(0).equals("depart")
                || parsedcommand.get(0).equals("borrowed")) {

            if (parsedcommand.size() < 2) {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {Visitor ID}";
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("info")) {
            if (parsedcommand.size() <= 6) {
                if (parsedcommand.size() < 3) {
                    commandsize = parsedcommand.size();
                    errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                    while (commandsize < 3) {
                        if (commandsize == 1) {
                            errormessage += "title, ";
                            commandsize++;
                        }
                        if (commandsize == 2) {
                            errormessage += "authors}";
                            commandsize++;
                        }
                        commandsize++;
                    }
                    try {
                        throw new Exception(errormessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (parsedcommand.get(0).equals("borrow")) {
            if (parsedcommand.size() < 3) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 3) {
                    if (commandsize == 1) {
                        errormessage += "Visitor ID, ";
                        commandsize++;
                    }
                    if (commandsize == 2) {
                        errormessage += "id}";
                        commandsize++;
                    }
                }
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("return")) {
            if (parsedcommand.size() < 4) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 4) {
                    if (commandsize == 1) {
                        errormessage += "Visitor ID, ";
                        commandsize++;
                    }
                    if (commandsize == 2) {
                        errormessage += "id, ";
                        commandsize++;
                    }
                    if (commandsize == 3) {
                        errormessage += "ids}";
                        commandsize++;
                    }
                }
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("pay")) {
            if (parsedcommand.size() < 3) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 3) {
                    if (commandsize == 1) {
                        errormessage += "Visitor ID, ";
                        commandsize++;
                    }
                    if (commandsize == 2) {
                        errormessage += "amount}";
                        commandsize++;
                    }
                }
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("search")) {
            if (parsedcommand.size() < 2) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 2) {
                    if (commandsize == 1) {
                        errormessage += "title}";
                        commandsize++;
                    }
                }
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("buy")) {
            if (parsedcommand.size() < 3) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 3) {
                    if (commandsize == 1) {
                        errormessage += "quantity, ";
                        commandsize++;
                    }
                    if (commandsize == 2) {
                        errormessage += "id} ";
                        commandsize++;
                    }
                }
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("advance")) {
            if (parsedcommand.size() < 2) {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {number-of-days}";
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("report")) {
            if (parsedcommand.size() < 2) {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {days}";
                try {
                    throw new Exception(errormessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (parsedcommand.get(0).equals("datetime") || parsedcommand.get(0).equals("shutdown")) {
            return parsedcommand;

            /*
             * Else statement handles an error where request command doesn't exist.
             *
             */
        } else {
            errormessage = "Requested command doesn't exist.";
            try {
                throw new Exception(errormessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
         *  Fixes parsing for buy and return command
         */
        if (parsedcommand.get(0).equals("buy") || parsedcommand.get(0).equals("return")) {
            firstindex = parsedcommand.get(0);
            secondindex = parsedcommand.get(1);
            for (int i = 2; i < parsedcommand.size(); i++) {
                thirdindex.add(parsedcommand.get(i));
            }
            parsedcommand.clear();
            parsedcommand.add(firstindex);
            parsedcommand.add(secondindex);
            parsedcommand.add(thirdindex);
        }
        return parsedcommand;
    }

    public Command ConcreteCommand(ArrayList parsedcommand) {

        if (parsedcommand.get(0).toString().equals("register")) {
            Command c = new Register_Command(parsedcommand.get(1).toString(), parsedcommand.get(2).toString(), parsedcommand.get(3).toString(), parsedcommand.get(4).toString());
            return c;
        } else if (parsedcommand.get(0).toString().equals("arrive")) {
            long a = Long.parseLong(parsedcommand.get(1).toString());
            Command c = new Begin_Visit_Command(a);
            return c;
        } else if (parsedcommand.get(0).toString().equals("depart")) {
            Command c = new End_Visit_Command(Long.parseLong(parsedcommand.get(1).toString()));
            return c;
        } else if (parsedcommand.get(0).toString().equals("info")) {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(2);
            Command c = new Book_Search_Command(parsedcommand.get(1).toString(), innerarraylist.toString(),
                    parsedcommand.get(3).toString(), parsedcommand.get(4).toString(), parsedcommand.get(5).toString());
            return c;
        } else if (parsedcommand.get(0).toString().equals("borrow")) {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(2);
            Command c = new Borrow_Command(Main.vk.getVisitorRegistry().get((parsedcommand.get(1))), innerarraylist.toString());
            return c;
        } else if (parsedcommand.get(0).toString().equals("borrowed")) {
            Command c = new Find_Borrowed_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)));
            return c;
        } else if (parsedcommand.get(0).toString().equals("return")) {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(3);
            Command c = new Return_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)), parsedcommand.get(2).toString(), innerarraylist.toString());
            return c;
        } else if (parsedcommand.get(0).toString().equals("pay")) {
            Command c = new Pay_Fine_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)), Double.parseDouble(parsedcommand.get(2).toString()));
            return c;
        } else if (parsedcommand.get(0).toString().equals("search")) {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(2);
            Command c = new Book_Store_Command(parsedcommand.get(1).toString(), innerarraylist.toString(),
                    parsedcommand.get(3).toString(), parsedcommand.get(4).toString(), parsedcommand.get(5).toString());
            return c;
        } else if (parsedcommand.get(0).toString().equals("buy")) {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(3);
            Command c = new Book_Purchase_Command(Integer.parseInt(parsedcommand.get(1).toString()), parsedcommand.get(2).toString(), innerarraylist.toString());
            return c;
        } else if (parsedcommand.get(0).toString().equals("advance")) {
            int day = Integer.parseInt(parsedcommand.get(1).toString());
            int hour;
            if (parsedcommand.size() < 3) {
                hour = 0;
            } else {
                hour = Integer.parseInt(parsedcommand.get(2).toString());
            }

            System.out.println("advance,success;");
            Command c = new Advance_Time_Command(day, hour);
            return c;
        } else if (parsedcommand.get(0).toString().equals("datetime")) {
            Command c = new Current_Time_Command();
            return c;
        } else {
            Command c = new Library_Report_Command(Integer.parseInt(parsedcommand.get(1).toString()));
            return c;
        }
    }
}