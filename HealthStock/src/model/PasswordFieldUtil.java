package model;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

//Class to handle some global passwordField properties.
public class PasswordFieldUtil {

//It is responsible for the "See Password" Feature.
//-----------------------------------------------------------------------------    
    //Method to configure and initialize the support TextFields.
    public void setSupTextField(
            JFXTextField textField,
            JFXPasswordField passwordField,
            AnchorPane anchorPane) {
        //textField with the passwordField settings mirrowed.
        textField.setPromptText(passwordField.getPromptText());
        textField.setLabelFloat(passwordField.isLabelFloat());
        textField.setLayoutX(passwordField.getLayoutX());
        textField.setLayoutY(passwordField.getLayoutY());
        textField.setPrefHeight(passwordField.getPrefHeight());
        textField.setPrefWidth(passwordField.getPrefWidth());
        textField.setVisible(false);

        //Add the support textFields to the "scene".
        anchorPane.getChildren().add(textField);
    }

    //Method to configure and initialize the password icons.
    public void setPasswordIcon(
            MaterialDesignIconView iconOn,
            MaterialDesignIconView iconOff,
            JFXTextField textField,
            JFXPasswordField passwordField,
            AnchorPane anchorPane) {
        //Icon Configuration.
        iconOn.setSize("17");
        iconOff.setSize("17");
        iconOn.setVisible(false);
        iconOff.setVisible(false);

        //AnchorPane to hold the icons.
        AnchorPane iconPane = new AnchorPane(iconOn, iconOff);

        //Handler for when the pane is pressed by the mouse.
        iconPane.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            //Hides the active icon andshows the another.
            iconOff.setVisible(true);
            iconOn.setVisible(false);
            //Sets the password text on support textField.
            textField.setText(passwordField.getText());
            //Hides the passwordField and shows the support textField.
            passwordField.setVisible(false);
            textField.setVisible(true);
            textField.requestFocus();
        });

        //Handler for when the pane is released by the mouse.
        iconPane.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) -> {
            //Hides the active icon andshows the another.
            iconOn.setVisible(true);
            iconOff.setVisible(false);
            //Hides the support textField and shows back the passwordField.
            textField.setVisible(false);
            passwordField.setVisible(true);
            passwordField.requestFocus();
        });

        //Add the anchorPane to the "scene".
        anchorPane.getChildren().setAll(iconPane);
    }

    //Handler method for the passwordField's Icons.
    public void senhaClicked(
            JFXPasswordField passwordField,
            MaterialDesignIconView icon) {
        //Checks to see if the password have any text.
        if (passwordField.textProperty().get().length() > 0) {
            //If it haves then the icon is showed.
            icon.setVisible(true);
        } else {
            //Else the icon is hidden.
            icon.setVisible(false);
        }
    }

    //Method to control the capsLockLabel visibility.
    public void checkCapsLock(KeyEvent event, Label label) {
        if (event.getCode() == KeyCode.CAPS) {
            label.setVisible(!label.isVisible());
        }
    }
//-----------------------------------------------------------------------------    
}
