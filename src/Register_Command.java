/**
 * Created by adamn on 3/2/2017.
 */
public class Register_Command implements Command {
    private String last;
    private String first;
    private String address;
    private String phone;

    public Register_Command( String first, String last, String address, String phone){
        this.last = last;
        this.first = first;
        this.address = address;
        this.phone = phone;
    }

    public void execute() {
        Main.vk.registerVisitor(this.first,this.last,this.address,this.phone);
    }
}
