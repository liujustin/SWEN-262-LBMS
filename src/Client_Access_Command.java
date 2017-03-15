//FILE::Client_Access_Command.java
//AUTHOR::Kevin.P.Barnett
//DATE::Feb.25.2017

public class Client_Access_Command {
    public Command command;

    public void receiveCommand(Command command){
        this.command = command;
    }

    public void executeCommand(){
        this.command.execute();
    }

}