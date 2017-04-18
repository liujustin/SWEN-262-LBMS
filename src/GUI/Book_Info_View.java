package GUI;
import Books.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Ryan on 4/16/2017.
 */
public class Book_Info_View extends Connect_View {
    //private static Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;

    public GridPane order(String visitorID){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Search To Borrow");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 4, 1);

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

        Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 7);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
                LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
                SearchForInfo.initializeSearch();
                ArrayList result = SearchForInfo.search(title,authorList,isbn, publisher, sortOrder, bookKeeper.getPurchasedBooks());
                for(Object s : result){
                    System.out.println(s);
                }
                SearchForInfo.setLastInfoSearch(result);
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
                    borrow.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ArrayList ISBN = new ArrayList();
                            ISBN.add(getISBN[0]);
                            if (visitorID == null) {
                                borrow.setDisable(true);
                            }
                            else{
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
