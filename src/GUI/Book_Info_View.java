package GUI;
import Books.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Justin on 4/18/2017.
 */
public class Book_Info_View extends Connect_View {

    /**
     * The GUI subclass for showing the Book_Info view which creates and returns a GridPane.
     * @param visitorID
     * @return
     */
    public GridPane start(String visitorID){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Search To Borrow");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 4, 1);

        //Creating a bunch of labels and text fields for the parameters needed search for books
        Label title = new Label("Title:");
        grid.add(title, 1, 2);

        TextField titleTextField = new TextField();
        grid.add(titleTextField, 2, 2);

        Label author = new Label("Author:");
        grid.add(author, 1, 3);

        TextField authorTextField = new TextField();
        grid.add(authorTextField, 2, 3);

        Label isbn = new Label("ISBN:");
        grid.add(isbn, 1, 4);

        TextField isbnTextField = new TextField();
        grid.add(isbnTextField, 2, 4);

        Label publisher = new Label("Publisher:");
        grid.add(publisher, 1, 5);

        TextField publisherTextField = new TextField();
        grid.add(publisherTextField, 2, 5);

        Label sortOrder = new Label("Sort-Order:");
        grid.add(sortOrder, 1, 6);

        TextField sortOrderTextField = new TextField();
        grid.add(sortOrderTextField, 2, 6);

        //Creating a search button and adding the button into a hBox
        Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 7);

        //Search button event handler method
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //For all the parameters, if the user does not put in anything, it will assume that
                //there is no input so it will be default set to *.
                String title = String.valueOf(titleTextField.getText());
                if(title.equals("")){
                    title = "*";
                }
                String author = String.valueOf(authorTextField.getText());
                ArrayList<String> authorList = new ArrayList();
                if(author.equals("")){
                    authorList.add("*");
                }
                else{
                    for(String s :author.split(",")){
                        authorList.add(s);
                    }
                }
                String isbn = String.valueOf(isbnTextField.getText());
                if(isbn.equals("")){
                    isbn = "*";
                }
                String publisher = String.valueOf(publisherTextField.getText());
                if(publisher.equals("")){
                    publisher = "*";
                }
                String sortOrder = String.valueOf(sortOrderTextField.getText());
                if(sortOrder.equals("")){
                    sortOrder = "*";
                }

                //Create a bookOperations instance and calls the SearchForInfo method while passing in the parameters for searching for a book
                Book_Operations bookOperations = Book_Operations.getInstance();
                SearchForInfo.initializeSearch();
                ArrayList result = SearchForInfo.search(title,authorList,isbn, publisher, sortOrder, bookOperations.getPurchasedBooks());
                for(Object s : result){
                    System.out.println(s);
                }
                SearchForInfo.setLastInfoSearch(result);

                //This will in turn show all the books that the library currently has that is borrowable
                Label bookText = new Label("Books:");
                grid.add(bookText,3,2);
                for(int i = 0;i < result.size();i++){
                    Label books = new Label();
                    books.setText(result.get(i).toString());
                    grid.add(books, 3, i+3);
                    String[] getISBN = result.get(i).toString().split(",");
                    Button borrow = new Button("Borrow");
                    grid.add(borrow, 4, i+3);
                    borrow.setDisable(false);

                    //Borrow button event handler
                    borrow.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ArrayList ISBN = new ArrayList();
                            ISBN.add(getISBN[0]);

                            //disables the borrow button if there is no visitor in the library that has arrived
                            if (visitorID == null) {
                                borrow.setDisable(true);
                            }
                            else{

                                //Create a borrow command object and executes it for the visitor which in turn borrows the book for that specific visitor
                                Borrow_Command bc = new Borrow_Command(Long.parseLong(visitorID), ISBN, false);
                                bc.execute();
                            }
                        }
                    });
                }
            }
        });

        return grid;
    }

}
