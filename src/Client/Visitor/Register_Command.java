package Client.Visitor;

import Network.Command;
import Network.Main;

//FILE::Client.Visitor.Register_Command.java
//AUTHOR::Ryan Connors, Adam Nowak
//DATE::Feb.25.2017
public class Register_Command implements Command {
    private String last;
    private String first;
    private String address;
    private String phone;

    /**
     *
     * @param first
     * @param last
     * @param address
     * @param phone
     */
    public Register_Command( String first, String last, String address, String phone){
        this.last = last;
        this.first = first;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String execute() {
        try {
            Main.vk.registerVisitor(this.first,this.last,this.address,this.phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
