package app;

import app.control.MainControl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFX extends Application {


    private static Stage mainStage;
    private static BorderPane mainPane;

    public static void main(String[] args) {
        launch(args);

    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        MainFX.mainStage = mainStage;
    }

    public static BorderPane getMainPane() {
        return mainPane;
    }

    public static void initializeToolkit() {
        Platform.startup(() -> {
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        mainPane = (BorderPane) MainControl.loadFXML();
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }


}
