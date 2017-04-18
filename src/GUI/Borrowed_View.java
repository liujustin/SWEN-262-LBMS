package GUI;

import Books.Book_Purchase_Command;
import Books.LBMS_BookKeeper;
import Books.SearchToBuy;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * Created by DemonicVampire on 4/17/17.
 */
public class Borrowed_View {
    public GridPane order(Integer clientID){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Borrowed Books:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 4, 1);

//                LBMS_BookKeeper bookKeeper = LBMS_BookKeeper.getInstance();
//                SearchToBuy.initializeSearch();
//                ArrayList result = SearchToBuy.search(title,authorList,isbn, publisher, sortOrder,bookKeeper.getBooksForPurchase());
//                SearchToBuy.setLastSearched(result);
//                Label bookText = new Label("Books:");
//                grid.add(bookText,2,2);
//                for(int i = 0;i < result.size();i++){
//                    Label books = new Label();
//                    books.setText(result.get(i).toString());
//                    grid.add(books, 2, i+3);
//                    TextField quantity = new TextField();
//                    quantity.textProperty().addListener(new ChangeListener<String>() {
//                        @Override
//                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                            if (!newValue.matches("\\d*")) {
//                                quantity.setText(newValue.replaceAll("[^\\d]", ""));
//                            }
//                        }
//                    });
//                    grid.add(quantity,3,i+3);
//                    String[] getISBN = result.get(i).toString().split(",");
//                    Button buy = new Button("Buy");
//                    grid.add(buy, 4, i+3);
//                    buy.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            String numberToBuy = String.valueOf(quantity.getText());
//                            Integer quantityToBuy = Integer.parseInt(numberToBuy);
//                            ArrayList ISBN = new ArrayList();
//                            ISBN.add(getISBN[0]);
//                            Book_Purchase_Command bpc = new Book_Purchase_Command(quantityToBuy,ISBN,false);
//                            bpc.execute();
//                        }
//                    });
//                }
//            }
//        });

        return grid;
    }
}
