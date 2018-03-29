package Task_2;

import javafx.application.Application;
import javafx.stage.Stage;

public class PingPongMain extends Application {
    @Override
    public void start( Stage primaryStage ) throws Exception {
        MainView mainView = new MainView();

        mainView.show(primaryStage);
    }
}
