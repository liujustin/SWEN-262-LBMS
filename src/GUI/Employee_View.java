package GUI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Network.*;


/**
 * Created by Ryan on 4/13/2017.
 */
public class Employee_View extends Application {
    private static Client_Access_View bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        Group root = new Group();
        this.primaryScene = new Scene(root);

        //bootInstance.startLoop(arguments, this);

        BorderPane mainWindow = new BorderPane();
        this.primaryStage.sizeToScene();
        mainWindow.setTop(new Label("Please Select an Option"));
        mainWindow.setCenter(order());
        Scene scene = new Scene(mainWindow);
        primaryStage.setTitle("LBMS");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

     public static void load(String[] args, Client_Access_View clientAccessView)
     {
     arguments = args;
     bootInstance = clientAccessView;
     Application.launch(args);
     }

    private HBox buttonBox1() {

        HBox list1 = new HBox();
        int gap = 5;
        list1.setSpacing(gap);
        Button pay = new Button("Pay Fine");
        pay.setPrefSize(100,50);
        Button borrowed = new Button("Borrowed");
        borrowed.setPrefSize(100,50);
        Button search = new Button("Search");
        search.setPrefSize(100,50);
        Button borrow = new Button("Borrow");
        borrow.setPrefSize(100,50);
        list1.getChildren().addAll(pay,borrowed,search,borrow);

        return list1;
    }

    private HBox buttonBox2() {

        HBox list2 = new HBox();
        int gap = 5;
        list2.setSpacing(gap);
        Button arrive = new Button("Arrive");
        arrive.setPrefSize(100,50);
        Button leave = new Button("Depart");
        leave.setPrefSize(100,50);
        Button undo = new Button("Undo");
        undo.setPrefSize(100,50);
        Button redo = new Button("Redo");
        redo.setPrefSize(100,50);
        list2.getChildren().addAll(arrive,leave,undo,redo);

        return list2;
    }

    private HBox buttonBox3() {

        HBox list3 = new HBox();
        int gap = 5;
        list3.setSpacing(gap);
        Button create = new Button("Create Account");
        create.setPrefSize(100,50);
        Button buy = new Button("Buy Book");
        buy.setPrefSize(100,50);
        Button advance = new Button("Advance Time");
        advance.setPrefSize(100,50);
        Button report = new Button("Report");
        report.setPrefSize(100,50);
        list3.getChildren().addAll(create,buy,advance,report);

        return list3;
    }

    private HBox buttonBox4() {

        HBox list4 = new HBox();
        int gap = 5;
        list4.setSpacing(gap);
        Button logout = new Button("Log Out");
        logout.setPrefSize(100,50);
        Button exit = new Button("Exit");
        exit.setPrefSize(100,50);
        Button buy_search = new Button("Search Store");
        buy_search.setPrefSize(100,50);
        Button register = new Button("Register");
        register.setPrefSize(100,50);
        list4.getChildren().addAll(logout,exit,buy_search,register);

        return list4;
    }

    private VBox order(){
        VBox order = new VBox();
        int gap = 5;
        HBox button1 = buttonBox1();
        HBox button2 = buttonBox2();
        HBox button3 = buttonBox3();
        HBox button4 = buttonBox4();
        Label welcome = new Label("Please Select an Option.");
        order.setSpacing(gap);
        order.getChildren().addAll(welcome,button1,button2,button3,button4);
        return order;
    }
}
