package GUI;
import Books.Book_Purchase_Command;
import Books.Book_Store_Command;
import Books.LBMS_BookKeeper;
import Books.SearchToBuy;
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
 * Created by Ryan,Justin on 4/16/2017.
 */
public class Book_Search_View extends Connect_View {
    //private static Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;


    public GridPane order(Integer clientID){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Search To Buy");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 4, 1);

        Label title = new Label("Title:");
        grid.add(title, 0, 2);

        TextField titleTextField = new TextField();
        grid.add(titleTextField, 1, 2);

        Label author = new Label("Author:");
        grid.add(author, 0, 3);

        TextField authorTextField = new TextField();
        grid.add(authorTextField, 1, 3);

        Label isbn = new Label("ISBN:");
        grid.add(isbn, 0, 4);

        TextField isbnTextField = new TextField();
        grid.add(isbnTextField, 1, 4);

        Label publisher = new Label("Publisher:");
        grid.add(publisher, 0, 5);

        TextField publisherTextField = new TextField();
        grid.add(publisherTextField, 1, 5);

        Label sortOrder = new Label("Sort-Order:");
        grid.add(sortOrder, 0, 6);

        TextField sortOrderTextField = new TextField();
        grid.add(sortOrderTextField, 1, 6);

        Button btn = new Button("Search");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 7);

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
                SearchToBuy.initializeSearch();
                ArrayList result = SearchToBuy.search(title,authorList,isbn, publisher, sortOrder,bookKeeper.getBooksForPurchase());
                SearchToBuy.setLastSearched(result);
                Label bookText = new Label("Books:");
                grid.add(bookText,2,2);
                for(int i = 0;i < result.size();i++){
                    Label books = new Label();
                    books.setText(result.get(i).toString());
                    grid.add(books, 2, i+3);
                    TextField quantity = new TextField();
                    quantity.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (!newValue.matches("\\d*")) {
                                quantity.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    });
                    grid.add(quantity,3,i+3);
                    String[] getISBN = result.get(i).toString().split(",");
                    Button buy = new Button("Buy");
                    grid.add(buy, 4, i+3);
                    buy.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String numberToBuy = String.valueOf(quantity.getText());
                            Integer quantityToBuy = Integer.parseInt(numberToBuy);
                            ArrayList ISBN = new ArrayList();
                            ISBN.add(getISBN[0]);
                            Book_Purchase_Command bpc = new Book_Purchase_Command(quantityToBuy,ISBN,false);
                            bpc.execute();
                        }
                    });
                }
            }
        });

        return grid;
    }

}
