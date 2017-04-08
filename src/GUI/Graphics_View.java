package GUI;//FILE::GUI.Graphics_View.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.29.2017

import Network.Main;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Graphics_View extends Application
{
    private static Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    private static String[] arguments;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        Group root = new Group();
        this.primaryScene = new Scene(root);

        bootInstance.startLoop(arguments, this);

        BorderPane mainWindow = new BorderPane();
        mainWindow.setPrefSize(400,100);
        mainWindow.setCenter(order());
        time timer = new time();
        HBox currentTime = timer.start();
        mainWindow.setBottom(currentTime);
        Scene scene = new Scene(mainWindow);
        primaryStage.setTitle("LBMS");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void load(String[] args, Main main)
    {
        arguments = args;
        bootInstance = main;
        Application.launch(args);
    }

    private HBox buttonBox() {
        HBox my_box = new HBox();
        int gap = 5;
        my_box.setSpacing(gap);
        Button login = new Button("Login");
        login.setPrefSize(100,100);
        Button exit = new Button("Exit");
        exit.setPrefSize(100,100);
        Button create = new Button("Create Client.Visitor.Account");
        create.setPrefSize(100,100);
        my_box.getChildren().addAll(login,create,exit);
        return my_box;
    }
    private HBox usernameField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label user = new Label("Username:");
        user.setFont(Font.font(14));
        user.setPrefSize(75,50);
        TextField userInput = new TextField();
        userInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(user,userInput);
        return my_box;
    }
    private HBox psswdField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label psswd = new Label("Password:");
        psswd.setFont(Font.font(14));
        psswd.setPrefSize(75,50);
        TextField psswdInput = new TextField();
        psswdInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(psswd,psswdInput);
        return my_box;
    }

    private VBox order(){
        VBox order = new VBox();
        int gap = 5;
        HBox user = usernameField();
        HBox psswd = psswdField();
        HBox button = buttonBox();
        order.setSpacing(gap);
        order.getChildren().addAll(user,psswd,button);
        return order;
    }

}