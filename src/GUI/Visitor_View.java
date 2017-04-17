package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
import Network.*;

/**
 * Created by Ryan on 4/13/2017.
 */
public class Visitor_View extends Connect_View{

    //private static Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;

    public void start(Stage primaryStage) {
//        VBox order = new VBox();
//        int gap = 5;
////        HBox button1 = buttonBox1(clientID);
////        HBox button2 = buttonBox2();
//        Label welcome = new Label("Please Select an Option.");
//        order.setSpacing(gap);
//        Button search = new Button("Search");
//        search.setPrefSize(75,50);
//        search.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Book_Search_View bsv = new Book_Search_View();
//
//            }
//        });
//        order.getChildren().addAll(welcome,search);
//        return order;
        this.primaryStage = primaryStage;

        Group root = new Group();
        this.primaryScene = new Scene(root);

        //bootInstance.startLoop(arguments, this);

        BorderPane mainWindow = new BorderPane();
        this.primaryStage.sizeToScene();
        mainWindow.setCenter(order());
        Scene scene = new Scene(mainWindow);
        primaryStage.setTitle("LBMS");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void load(String[] args, Main main) {
        arguments = args;
        //bootInstance = main;
        Application.launch(args);
     }

    private HBox buttonBox1() {

        HBox list1 = new HBox();
        int gap = 5;
        list1.setSpacing(gap);
        Button arrive = new Button("Arrive");
        arrive.setPrefSize(75,50);
        Button leave = new Button("Depart");
        leave.setPrefSize(75,50);
        Button search = new Button("Search");
        search.setPrefSize(75,50);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Book_Search_View bsv = new Book_Search_View();
            }
        });
        Button borrow = new Button("Borrow");
        borrow.setPrefSize(75,50);
        list1.getChildren().addAll(arrive,leave,search,borrow);

        return list1;
    }

    private HBox buttonBox2() {

        HBox list2 = new HBox();
        int gap = 5;
        list2.setSpacing(gap);
        Button logout = new Button("Log Out");
        logout.setPrefSize(75,50);
        Button exit = new Button("Exit");
        exit.setPrefSize(75,50);
        Button undo = new Button("Undo");
        undo.setPrefSize(75,50);
        Button redo = new Button("Redo");
        redo.setPrefSize(75,50);
        list2.getChildren().addAll(logout,exit,undo,redo);

        return list2;
    }


    public VBox order(){
        VBox order = new VBox();
        int gap = 5;
        HBox button1 = buttonBox1();
        HBox button2 = buttonBox2();
        Label welcome = new Label("Please Select an Option.");
        order.setSpacing(gap);
        order.getChildren().addAll(welcome,button1,button2);
        return order;
    }

}

