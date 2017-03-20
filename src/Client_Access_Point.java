//FILE::Client_Access_Point.java
//AUTHOR::Kevin.P.Barnett , Adam Nowak
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
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        } else if (parsedcommand.get(0).equals("arrive")
                || parsedcommand.get(0).equals("depart")
                || parsedcommand.get(0).equals("borrowed")) {

            if (parsedcommand.size() < 2) {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {Visitor ID}";
                try {
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
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
                        throw new Error(errormessage);
                    } catch (Error e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
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
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
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
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
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
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        } else if (parsedcommand.get(0).equals("search")) {
            if (parsedcommand.size() < 4) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 4) {
                    if (commandsize == 1) {
                        errormessage += "title, ";
                        commandsize++;
                    }
                    if (commandsize == 3) {
                        errormessage += "isbn}";
                        commandsize++;
                    }
                    commandsize++;
                }
                try {
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        } else if (parsedcommand.get(0).equals("buy")) {
            if (parsedcommand.size() < 4) {
                commandsize = parsedcommand.size();
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {";
                while (commandsize < 4) {
                    if (commandsize == 1) {
                        errormessage += "quantity, ";
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
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        } else if (parsedcommand.get(0).equals("advance")) {
            if (parsedcommand.size() < 2) {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {number-of-days}";
                try {
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        } else if (parsedcommand.get(0).equals("report")) {
            if (parsedcommand.size() < 2) {
                errormessage = "<" + parsedcommand.get(0) + ">, missing parameters, {days}";
                try {
                    throw new Error(errormessage);
                } catch (Error e) {
                    System.out.println(e.getMessage());
                }
                return null;
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
                throw new Error(errormessage);
            } catch (Error e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
        return parsedcommand;
    }
    public Command ConcreteCommand(ArrayList parsedcommand){

        if(parsedcommand.get(0).toString()== "register") {
            Command c = new Register_Command(parsedcommand.get(1).toString(), parsedcommand.get(2).toString(), parsedcommand.get(3).toString(), parsedcommand.get(4).toString());
            return c;
        }
        if(parsedcommand.get(0).toString()== "arrive") {
            Command c = new Begin_Visit_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)));
            return c;
        }
        if(parsedcommand.get(0).toString()== "depart") {
            Command c = new End_Visit_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)));
            return c;
        }
        if(parsedcommand.get(0).toString()== "info") {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(2);
            Command c = new Book_Search_Command(parsedcommand.get(1).toString(), innerarraylist.toString(),
                    parsedcommand.get(3).toString(), parsedcommand.get(4).toString(), parsedcommand.get(5).toString());
            return c;
        }
        if(parsedcommand.get(0).toString() == "borrow") {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(2);
            Command c = new Borrow_Command(Main.vk.getVisitorRegistry().get((parsedcommand.get(1))),innerarraylist.toString());
            return c;
        }
        if(parsedcommand.get(0).toString() == "borrowed") {
            Command c = new Find_Borrowed_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)));
            return c;
        }
        if(parsedcommand.get(0).toString() == "return") {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(3);
            Command c = new Return_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)), parsedcommand.get(2).toString(), innerarraylist.toString());
            return c;
        }
        if((parsedcommand.get(0).toString() == "pay")) {
            Command c = new Pay_Fine_Command(Main.vk.getVisitorRegistry().get(parsedcommand.get(1)),Double.parseDouble(parsedcommand.get(2).toString()));
            return c;
        }
        if(parsedcommand.get(0).toString() == "search") {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(2);
            Command c = new Book_Store_Command(parsedcommand.get(1).toString(),innerarraylist.toString(),
                    parsedcommand.get(3).toString(),parsedcommand.get(4).toString(),parsedcommand.get(5).toString());
            return c;
        }
        if(parsedcommand.get(0).toString() == "buy") {
            ArrayList innerarraylist = (ArrayList) parsedcommand.get(3);
            Command c = new Book_Purchase_Command(Integer.parseInt(parsedcommand.get(1).toString()),parsedcommand.get(2).toString(),innerarraylist.toString());
            return c;
        }
        if(parsedcommand.get(0).toString() == "advance") {
            Command c = new Advance_Time_Command(Integer.parseInt(parsedcommand.get(1).toString()),Integer.parseInt(parsedcommand.get(2).toString()));
            return c;
        }
        if(parsedcommand.get(0).toString() == "datetime") {
            Command c = new Current_Time_Command();
            return c;
        }
        if(parsedcommand.get(0).toString() == "report") {
            Command c = new Library_Report_Command(Integer.parseInt(parsedcommand.get(1).toString()));
            return c;
        }
        return null;
    }
}
