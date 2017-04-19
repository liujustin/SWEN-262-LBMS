package Books;

import Network.Command;

/**
 * Created by Ryan on 4/19/2017.
 */
public class Information_Service_Command implements Command {
    Book_Operations bookKeeper = Book_Operations.getInstance();
    private String method;

    public Information_Service_Command(String method){
        this.method = method;
    }

    public String execute(){
        if (this.method.equals("local")){
            bookKeeper.setSearchState(false);
        }
        else if (this.method.equals("google")) {
            bookKeeper.setSearchState(true);
        }
        return "service changed";
    }

}
