/**
 * Created by adamn on 3/2/2017.
 */
public class Register_Command implements Command {
    private LBMS_VisitorKeeper sys;
    private String last;
    private String first;
    private String address;
    private String phone;

    public Register_Command(LBMS_VisitorKeeper VK,String first, String last, String address, String phone){
        this.sys = VK;
        this.last = last;
        this.first = first;
        this.address = address;
        this.phone = phone;
    }

    public void execute() {
        this.sys.registerVisitor(this.first,this.last,this.address,this.phone);
    }
}
