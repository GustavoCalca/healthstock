package model;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*
     * PROGRESS HISTORY (30/05/2018):
     * 
     * EXPECTED ERROR.
     * PasswordField CapsLock Feature needs fixing:
     * If the program is initialized with the CapsLock key "On"
     * The exhibition of the labelCapsLock will be inversed.
     * Ex.: CapsLock On -> labelCapsLock invisible.
     * RECOMMENDED BEHAVIOUR: REMOVE FEATURE.
     * 
     * UNDER CONSTRUCTION.
     * Definition of hierarchy access.
     * Implementation of hierarchy access.
     * 
     * INCOMPLETED.
     * Usuario Controllers and Medicamento Controllers need to be polished.
     * Lack of restrictions on the search feature.
     * 
     * UNDONE.
     * Every CSS Class need to be made.
     * 
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Load the MainScreen and set asroot of the scene.
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/MainScreen.fxml"));
            Scene scene = new Scene(root);

            //Set the scene on the stage and show it.
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
