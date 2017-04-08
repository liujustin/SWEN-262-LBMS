import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class time extends Graphics_View{

    LBMS_StatisticsKeeper sk = LBMS_StatisticsKeeper.getInstance();
    public HBox start() {
        HBox time = new HBox();
        Label timer = new Label();
        time.maxHeight(Double.MAX_VALUE);
        time.maxWidth(Double.MAX_VALUE);

        Task updateTime = new Task<Void>() {
            @Override
            public Void call () throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String tempTime = sk.Get_Time();
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


    public static void main(String[] args) {
        launch(args);
    }
}
