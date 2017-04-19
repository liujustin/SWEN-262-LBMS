package GUI;

import Client.Visitor.Begin_Visit_Command;
import Client.Visitor.End_Visit_Command;
import Client.Visitor.Visitor_Operations;
import Time.Advance_Time_Command;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//FILE::GUI.Connect_View.java
//AUTHOR::Justin Liu
//DATE::Apr.17.2017

public class Connect_View extends Application{

    private String clientMessage;
    private Integer clientID;
    String visitorNumber;

    //main method used for testing
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        visitorNumber = null;
        Stage stage = primaryStage;
        Visitor_Operations visitorOperations = Visitor_Operations.getInstance();
        BorderPane root = new BorderPane();
        primaryStage.setTitle("LBMS");
        //Creates buttons for connect,disconnect,searchtobuy,arrive,searchtoborrow,borrowed,advance and depart.
        Button connectButton = new Button();
        connectButton.setText("Connect");
        Button disconnectButton = new Button();
        disconnectButton.setText("Disconnect");
        Button searchBuyButton = new Button();
        searchBuyButton.setText("SearchToBuy");
        Button arriveButton = new Button();
        arriveButton.setText("Arrive");
        Button searchBorrowButton = new Button();
        searchBorrowButton.setText("SearchToBorrow");
        Button borrowedButton = new Button();
        borrowedButton.setText("Borrowed");
        Button advanceButton = new Button();
        advanceButton.setText("Advance");
        Button depart = new Button("Depart");

        //Creates a VBox for the buttons called clientView
        VBox clientView = new VBox();
        clientView.maxHeight(Double.MAX_VALUE);
        clientView.maxWidth(Double.MAX_VALUE);
        Label clientText = new Label();
        Label visitorText = new Label();

        //Creates a textField for the client to put in a visitorID
        TextField visitorTextField = new TextField();

        //Disables all the buttons by default because the user needs to connect as a client first
        searchBuyButton.setDisable(true);
        arriveButton.setDisable(true);
        searchBorrowButton.setDisable(true);
        advanceButton.setDisable(true);
        borrowedButton.setDisable(true);
        visitorTextField.setDisable(true);

        //Create VBoxes for when a button is pressed so the buttons will always be on the left side for all GUI operations
        VBox searchBox = new VBox();
        VBox borrowBox = new VBox();
        VBox clientBox = clientView;
        VBox borrowedBox = new VBox();

        //Create a timeGUI that will show the system time always
        timeGUI timer = new timeGUI();
        HBox currentTime = timer.start();

        //Create new scenes for searching, borrowing, and showing borrowed for visitor
        Scene scene1 = new Scene(searchBox,1700,1000);
        Scene scene2 = new Scene(borrowBox,1700,1000);
        Scene scene3 = new Scene(borrowedBox,1700,1000);

        //Connect Button handler
        connectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //After the button is pressed, it is disabled so the user can only connect once
                connectButton.setDisable(true);

                //start a connection through visitorOperations class and displays on the GUI what the client number is.
                clientMessage = visitorOperations.startConnection();
                clientText.setText(clientMessage);

                //sets the clientID so it can be used later on for disconnect.
                clientID = Integer.parseInt(clientMessage.split(",|\\;")[1]);

                //enables some of the buttons because there is a connected client now
                searchBuyButton.setDisable(false);
                arriveButton.setDisable(false);
                advanceButton.setDisable(false);
                visitorTextField.setDisable(false);
                clientView.getChildren().addAll(clientText);
            }
        });

        //Disconnect button handler
        disconnectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Try catch to disconnect the client and end the visit for the visitor that the client
                //may have arrived for. Then it will call the restart method to reset the stage.
                try {
                    visitorOperations.disconnectConnection(clientID);
                    visitorOperations.endVisit(Long.parseLong(visitorNumber));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    restart(primaryStage);
                }
            }
        });

        //Search to Buy books button handler
        searchBuyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Creates a new Book Search View and calls the start method within all the while setting it to a new GridPane.
                Book_Search_View bookSearchView = new Book_Search_View();
                GridPane gridPane = bookSearchView.start();
                gridPane.add(clientBox,0,1);

                //Checks if the gridpane is already added to the searchBox VBox and if it is, don't do anything. Else,
                //add it into the searchBox VBox.
                if(searchBox.getChildren().contains(gridPane)){
                }
                else{
                    searchBox.getChildren().addAll(currentTime, gridPane);
                }
                stage.setScene(scene1);
            }
        });

        //Arriving a visitor button handler
        arriveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //parses for the visitorID
                String visitorID;
                long visitor = Long.parseLong(visitorTextField.getText());

                //Creates a new Begin Visit Command while passing in the required parameters and executing it
                Begin_Visit_Command bvc = new Begin_Visit_Command(visitor,false);
                String result = bvc.execute();
                String[] visitorString = result.split(",");
                if (visitorString.length == 1){
                    visitorID = visitorString[0];
                    visitorText.setText(visitorID);
                }
                else {
                    //If it does begin a visit, then enable the searchtoBuy method.
                    //Also enables searchtoBorrow as well as borrowed and disables
                    //buttons for arriving and putting in text into the visitor TextField.
                    searchBuyButton.setDisable(false);
                    searchBorrowButton.setDisable(false);
                    borrowedButton.setDisable(false);
                    visitorText.setDisable(true);
                    visitorTextField.setDisable(true);
                    arriveButton.setDisable(true);
                    visitorID = visitorString[1];
                    visitorNumber = visitorID;

                    //Displays visitor's ID on the GUI
                    visitorText.setText("Visitor: " + visitorID);

                    //Creates a depart button handler
                    depart.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            //enables the depart button and creates a new End Visitor Command which will, if the button is pressed,
                            //depart the visitor and then disable the depart button.
                            depart.setDisable(false);
                            End_Visit_Command evc = new End_Visit_Command(Long.parseLong(visitorID), false);
                            evc.execute();
                            depart.setDisable(true);
                        }
                    });
                    clientView.getChildren().add(depart);
                }
            }
        });

        //Create a search to borrow books button handler
        searchBorrowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Create a new Book Info View and call the start method which will set it to a GridPane variable.
                //Then it will add the clientBox which are the all the button operations to the gridpane and then
                //add time and the gridpane to the new scene and set the new scene.
                Book_Info_View bookInfoView = new Book_Info_View();
                GridPane gridPane = bookInfoView.start(visitorNumber);
                gridPane.add(clientBox,0,3);
                borrowBox.getChildren().addAll(currentTime, gridPane);
                stage.setScene(scene2);
            }
        });

        //Create a borrow button handler
        borrowedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Create a new Borrowed View and calls the start method which will set it to a GridPane variable.
                //Then it will add the clientBox which are the all the button operations to the gridpane and then
                //add time and the gridpane to the new scene and set the new scene.
                Borrowed_View borrowedView = new Borrowed_View();
                GridPane gridPane = borrowedView.start(Long.parseLong(visitorNumber));
                gridPane.add(clientBox,0,0);
                borrowedBox.getChildren().addAll(currentTime, gridPane);
                stage.setScene(scene3);
            }
        });

        //create advance time labels and a button handler
        Label advance = new Label("Advance Time");
        Label days = new Label("Days:");
        ComboBox daysToAdvance = new ComboBox();
        for(int i = 0; i < 7; i++)
            daysToAdvance.getItems().add(i);
        daysToAdvance.setValue(0);
        Label hours = new Label("Hours:");
        final ComboBox hoursToAdd = new ComboBox();
        for(int i = 0; i < 24; i++)
            hoursToAdd.getItems().add(i);
        hoursToAdd.setValue(0);

        //this button handler creates advance time command and executes it with the neccessary parameters.
        advanceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Advance_Time_Command atc = new Advance_Time_Command(Integer.parseInt(daysToAdvance.getValue().toString()),Integer.parseInt(hoursToAdd.getValue().toString()));
                atc.execute();
            }
        });
        clientView.getChildren().addAll(currentTime,connectButton,disconnectButton,searchBuyButton,arriveButton,visitorTextField,borrowedButton,searchBorrowButton,visitorText,clientText,advance,days,daysToAdvance,hours,hoursToAdd,advanceButton);
        root.setLeft(clientBox);
        root.setTop(currentTime);
        primaryStage.setScene(new Scene(root, 3000, 3000));
        primaryStage.show();

        //end the program if the GUI window is closed
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * This method is used to reset the stage
     */
    void cleanup(){

    }

    /**
     * The restart method used to reset the stage from scratch. Used for disconnect when 
     * disconnecting a client and departing all visitors.
     * @param stage
     */
    void restart(Stage stage) {
        cleanup();
        start(stage);
    }
}
