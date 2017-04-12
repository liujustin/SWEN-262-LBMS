package Network;//FILE::Network.Client_Access_Invoker.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017

public class Client_Access_Invoker {
    public Command command;

    /**
     *
     * @param command
     */
    public void receiveCommand(Command command){
        this.command = command;
    }

    /**
     *  executes various commands
     */
    public String executeCommand(){
        return this.command.execute();
    }

}