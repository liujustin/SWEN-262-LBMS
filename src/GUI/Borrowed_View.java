package GUI;

import Client.Visitor.Visitor_Operations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Justin on 4/17/17.
 */
public class Borrowed_View extends Connect_View{
    /**
     * The GUI subclass for showing the Borrowed_View which creates and returns a GridPane. This GUI view will show the
     * client what books are borrowed by the visitor that arrived.
     * @param visitorID
     * @return
     */
    public GridPane start(Long visitorID){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Borrowed Books:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 1, 0);

        //Creates a new Visitor_Operations instance
        Visitor_Operations visitorOperations= Visitor_Operations.getInstance();

        //Initializes a String borrowed and uses a try catch in order to get the books
        //that are borrowed by the visitor.
        String borrowed = null;
        try {
            borrowed = visitorOperations.borrowedBooks(visitorID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Splits the result by new line and displays each borrowed book as one whole label
        String[] result = borrowed.split("\n");
        for(int i = 0;i < result.length;i++){
            Label books = new Label();
            books.setText(result[i]);
            grid.add(books,1,i+1);
        }

        return grid;
    }
}
