//FILE::Graphics_View.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.29.2017

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graphics_View extends Application
{
    private Main bootInstance;
    private Stage primaryStage;
    private Scene primaryScene;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;

        Group root = new Group();
        this.primaryScene = new Scene(root);

        this.bootInstance.start
    }

    public static void load(String[] args, Main main)
    {
        bootInstance = main;
        Application.launch(args);
    }
}