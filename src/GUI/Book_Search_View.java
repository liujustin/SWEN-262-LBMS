package GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Ryan on 4/16/2017.
 */
public class Book_Search_View extends Application {
    //private static Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        //Group root = new Group();
        //this.primaryScene = new Scene(root);

        //bootInstance.startLoop(arguments, this);

        BorderPane mainWindow = new BorderPane();
        mainWindow.setCenter(order());
        Scene scene = new Scene(mainWindow);
        primaryStage.setTitle("LBMS");
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**  public static void load(String[] args, Main main)
     {
     arguments = args;
     //bootInstance = main;
     Application.launch(args);
     }*/

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

    private VBox order(){
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

}
