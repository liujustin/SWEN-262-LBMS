package GUI;

import Time.Time_Operations;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

//FILE::GUI.timeGUI.java
//AUTHOR::Justin Liu
//DATE::Apr.17.2017

public class timeGUI extends Connect_View {


    Time_Operations timeOperations = Time_Operations.getInstance();
    public HBox start() {
        HBox time = new HBox();
        Label timer = new Label();
        time.maxHeight(Double.MAX_VALUE);
        time.maxWidth(Double.MAX_VALUE);

        //this task continously updates the time by removing the label and adding it over and over again
        Task updateTime = new Task<Void>() {
            @Override
            public Void call () throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String tempTime = timeOperations.Get_Time();
                            timer.setText(tempTime);
                            time.getChildren().removeAll(timer);
                            time.getChildren().addAll(timer);
                        }
                    });
                    Thread.sleep(1000);
                }

            }
        };

        Thread th = new Thread(updateTime);
        th.setDaemon(true);
        th.start();

        return time;
    }
}
