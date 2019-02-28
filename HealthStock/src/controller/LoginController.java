package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.PasswordFieldUtil;
import model.User;

public class LoginController extends PasswordFieldUtil {

    //Creates the Password show/hide icons.
    private final MaterialDesignIconView iconOn1
            = new MaterialDesignIconView(MaterialDesignIcon.EYE);
    private final MaterialDesignIconView iconOff1
            = new MaterialDesignIconView(MaterialDesignIcon.EYE_OFF);

    //Creates the textFields for support to the passwordField.
    private final JFXTextField txtSenhaSup = new JFXTextField();

//USER CODE.    
//----------------------------------------------------------------------    
    private User user;
//---------------------------------------------------------------------- 

    //MainController instance.
    private MainScreenController main;

    //Login Control variable
    private boolean correct = false;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private AnchorPane senhaAnchorPane;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXButton btEntrar;
    @FXML
    private JFXButton btCancel;
    @FXML
    private Label labelFailure;

    @FXML
    private void btEntrarClicked(ActionEvent event) {
        //If the values are correct then the Login is done.
        if (checkCodigo()) {
            //Close the stage.
            main.closeStage();
        }

        //Resets the control variable.
        correct = false;
    }

    @FXML
    private void btCancelClicked(ActionEvent event) {
        //Close the stage.
        main.closeStage();
    }

    public void setItself(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public void init(MainScreenController main) {
        this.main = main;

        initSenha();
    }

//USER CODE.
//----------------------------------------------------------------------    
    //Method to set the User from outside the searchUserController.
    public void setUser(User user) {
        this.user = user;
    }
//---------------------------------------------------------------------- 

    //Check if the userCodigo is correct.
    public boolean checkCodigo() {
        if (txtCodigo.getText().isEmpty() || !main.getUser(Integer.parseInt(txtCodigo.getText()))) {
            labelFailure.setVisible(true);
            txtCodigo.requestFocus();
        } else {
            labelFailure.setVisible(false);
            //Set the correct User on the LoginController.
            main.setUserLoginController();

            //Check if the password is correct.
            checkSenha();
        }
        //Retorna a variável se os campos estiverem corretos.
        return correct;
    }

    //Password "initializer"
    public void initSenha() {
        //Loads the passwordUtil method(Icon).
        setSupTextField(txtSenhaSup, txtSenha, anchorPane2);
        setPasswordIcon(
                iconOn1,
                iconOff1,
                txtSenhaSup,
                txtSenha,
                senhaAnchorPane
        );

        //Handler for the passwordField.
        txtSenha.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (!txtSenha.getText().isEmpty()) {

                //When something is typed the icon is shown.
                senhaClicked(txtSenha, iconOn1);
            }
        });
    }

    //Method to check if txtSenha is equal to the "DataBase" registry.
    private boolean checkSenha() {
        //Check the text and if txtSenha is equal to the correct password.
        if (!txtSenha.getText().equals(user.getUserPassword().get())) {
            //If it isn't correct.
            labelFailure.setVisible(true);
            correct = false;
            txtCodigo.requestFocus();
        } else {
            labelFailure.setVisible(false);
            correct = true;
        }
        return correct;
    }
}
