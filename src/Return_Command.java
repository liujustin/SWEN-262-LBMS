//FILE::Return_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Return_Command implements Command {
    private Visitor v;
    private String books;

    public Return_Command(Visitor v,String books){
        this.v = v;
        this.books = books;

    }

    @Override
    public void execute() {
       // Main.bk.Return_Book(v.getVisitor_ID(),this.bookID,this.books);
    }
}
