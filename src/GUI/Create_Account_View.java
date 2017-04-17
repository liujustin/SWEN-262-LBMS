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
public class Create_Account_View extends Application {
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
        Button login = new Button("Create Account");
        login.setPrefSize(100,50);
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(100,50);
        my_box.getChildren().addAll(login,cancel);
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
        PasswordField psswdInput = new PasswordField();
        psswdInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(psswd,psswdInput);
        return my_box;
    }
    private HBox cnfrmpsswdField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label psswd = new Label("Confirm Password:");
        psswd.setFont(Font.font(14));
        psswd.setPrefSize(125,50);
        PasswordField psswdInput = new PasswordField();
        psswdInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(psswd,psswdInput);
        return my_box;
    }
    private HBox RoleField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label role = new Label("Role (0 for Visitor, 1 for Employee):");
        role.setFont(Font.font(14));
        role.setPrefSize(225,50);
        PasswordField userInput = new PasswordField();
        userInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(role,userInput);
        return my_box;
    }
    private HBox IDField(){
        HBox my_box = new HBox();
        int gap = 5;
        Label id = new Label("Visitor ID:");
        id.setFont(Font.font(14));
        id.setPrefSize(75,50);
        TextField userInput = new TextField();
        userInput.setPrefSize(200,50);
        my_box.setSpacing(gap);
        my_box.getChildren().addAll(id,userInput);
        return my_box;
    }

    public VBox order(){
        VBox order = new VBox();
        int gap = 5;
        HBox user = usernameField();
        HBox psswd = psswdField();
        HBox cnfrmpsswd = cnfrmpsswdField();
        HBox role = RoleField();
        HBox id = IDField();
        HBox button = buttonBox();
        order.setSpacing(gap);
        order.getChildren().addAll(user,psswd,cnfrmpsswd,role,id,button);
        return order;
    }

}
