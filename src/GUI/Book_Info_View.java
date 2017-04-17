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
 * Created by Ryan on 4/16/2017.
 */
public class Book_Info_View extends Connect_View {
    //private static Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;


    private HBox buttonBox() {
        HBox my_box = new HBox();
        int gap = 5;
        my_box.setSpacing(gap);
        Button search = new Button("Search");
        search.setPrefSize(100,50);
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(100,50);
        my_box.getChildren().addAll(search,cancel);
        return my_box;
    }

    private HBox authorField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label author = new Label("Author:");
        author.setFont(Font.font(14));
        author.setPrefSize(75,50);
        TextField authorInput = new TextField();
        authorInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(author,authorInput);
        return my_box;
    }
    private HBox titleField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label title = new Label("Title:");
        title.setFont(Font.font(14));
        title.setPrefSize(75,50);
        TextField titleInput = new TextField();
        titleInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(title,titleInput);
        return my_box;
    }
    private HBox isbnField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label isbn = new Label("isbn:");
        isbn.setFont(Font.font(14));
        isbn.setPrefSize(75,50);
        TextField isbnInput = new TextField();
        isbnInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(isbn,isbnInput);
        return my_box;
    }
    private HBox publisherField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label publisher = new Label("Publisher:");
        publisher.setFont(Font.font(14));
        publisher.setPrefSize(75,50);
        TextField publisherInput = new TextField();
        publisherInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(publisher,publisherInput);
        return my_box;
    }
    private HBox sortField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label sort = new Label("Sort Order:");
        sort.setFont(Font.font(14));
        sort.setPrefSize(75,50);
        TextField sortInput = new TextField();
        sortInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(sort,sortInput);
        return my_box;
    }

    public VBox order2(Integer clientID){
        VBox order = new VBox();
        int gap = 5;
        HBox title = titleField();
        HBox author = authorField();
        HBox isbn = isbnField();
        HBox publisher = publisherField();
        HBox sort = sortField();
        HBox button = buttonBox();
        order.setSpacing(gap);
        order.getChildren().addAll(title,author,isbn,publisher,sort,button);
        return order;
    }

    public GridPane order(Integer clientID){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Search To Borrow");
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
