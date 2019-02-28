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

public class UsuarioAddController extends PasswordFieldUtil {

    //MainScreenController Instance.
    private MainScreenController main;

    //Creates the Password show/hide icons.
    //1st PasswordField
    private final MaterialDesignIconView iconOn1
            = new MaterialDesignIconView(MaterialDesignIcon.EYE);
    private final MaterialDesignIconView iconOff1
            = new MaterialDesignIconView(MaterialDesignIcon.EYE_OFF);
    //2nd PasswordField
    private final MaterialDesignIconView iconOn2
            = new MaterialDesignIconView(MaterialDesignIcon.EYE);
    private final MaterialDesignIconView iconOff2
            = new MaterialDesignIconView(MaterialDesignIcon.EYE_OFF);

    //Creates the textFields for support to the passwordFields.
    private final JFXTextField txtSenhaSup = new JFXTextField();
    private final JFXTextField txtSenhaConfirmaSup = new JFXTextField();

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane senhaAnchorPane;
    @FXML
    private AnchorPane senhaConfirmaAnchorPane;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXPasswordField txtSenhaConfirma;
    @FXML
    private JFXButton btCodigo;
    @FXML
    private Label labelSuccess;
    @FXML
    private Label labelCaps1;
    @FXML
    private Label labelCaps2;
    @FXML
    private Label labelNumero;
    @FXML
    private Label labelSenhaConfirma;
    @FXML
    private Label labelSenha;
    @FXML
    private JFXButton btAdd;
    @FXML
    private JFXButton btCancel;

    @FXML
    void btAddClicked(ActionEvent event) {
//USER CODE.
//----------------------------------------------------------------------
        //Check to see any error on the passwords    
        if (!labelSenha.isVisible()
                && !txtSenha.getText().isEmpty()) {
            if (!labelSenhaConfirma.isVisible()
                    && !txtSenhaConfirma.getText().isEmpty()) {
                User user = new User(Integer.parseInt(
                        txtId.getText()),
                        "TextField Nome",
                        "TextField Cargo",
                        txtSenhaConfirma.getText());
                //Add the new User to the observableList of users userData.
                main.addUser(user);
                resetScreen();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
                labelSuccess.setVisible(true);
            } else {
                txtSenhaConfirma.requestFocus();
            }
        } else {
            txtSenha.requestFocus();
        }
//----------------------------------------------------------------------
    }

    @FXML
    void btCancelClicked(ActionEvent event) {
        main.showMainSearch();
    }

    @FXML
    void btCodigoClicked(ActionEvent event) {
//USER CODE.
//----------------------------------------------------------------------
        resetScreen();
        labelSuccess.setVisible(false);

        //Sets the userId text.
        txtId.setText(txtCodigo.getText());

        //Check the txtId value.
        if (txtId.getText().isEmpty()) {
            labelNumero.setText("Código inválido");
            labelNumero.setVisible(true);
        } else {
            //Check if the Id already exists on the userData.
            if (main.getUser(Integer.parseInt(txtId.getText()))) {
                btAdd.setDisable(true);
                txtId.setText("");
                labelNumero.setText("Este número já está adicionado");
                labelNumero.setVisible(true);
                txtCodigo.requestFocus();
            } else {
                labelNumero.setVisible(false);

                btAdd.setDisable(false);
                txtSenha.requestFocus();
            }
        }
//----------------------------------------------------------------------
    }

    public void setItself(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    //Initializer.
    public void init(MainScreenController main) {
        this.main = main;

        //Initializes the password methods.
        initSenha();
        initSenhaConfirma();
    }

    //Password "initializers"
    public void initSenha() {
        //Loads the passwordUtil method(Icon).
        setSupTextField(txtSenhaSup, txtSenha, anchorPane);
        setPasswordIcon(
                iconOn1,
                iconOff1,
                txtSenhaSup,
                txtSenha,
                senhaAnchorPane
        );

        //Handler for the passwordField.
        txtSenha.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            //When something is typed the icon is shown.
            senhaClicked(txtSenha, iconOn1);

            //Method to control the capsLockLabel visibility.
            checkCapsLock(event, labelCaps1);

            //Hides the label (if it is shown) as soon as it hits the min value.
            if (labelSenha.isVisible()) {
                senhaCheck();
            }
        });

        //Handler for the passwordField Content.
        txtSenha.focusedProperty().addListener((o, old_v, new_v) -> {
            //Fired when the passwordField lose focus.
            if (!new_v) {
                //txtSenhaConfirma is checked when txtSenha loses focus.
                senhaConfirmaCheck();

                //Method to check if txtSenhaNova has the minimal lenght.
                senhaCheck();
            }
        });
    }

    public void initSenhaConfirma() {
        //Loads the passwordUtil method(Icon).
        setSupTextField(txtSenhaConfirmaSup, txtSenhaConfirma, anchorPane);
        setPasswordIcon(
                iconOn2,
                iconOff2,
                txtSenhaConfirmaSup,
                txtSenhaConfirma,
                senhaConfirmaAnchorPane
        );

        //Handler for the passwordField.
        txtSenhaConfirma.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            //When something is typed the icon is shown.
            senhaClicked(txtSenhaConfirma, iconOn2);

            //Method to control the capsLockLabel visibility.
            checkCapsLock(event, labelCaps2);

            //Check if txtSenhaConfirma is equal to txtSenha.
            senhaConfirmaCheck();
        });
    }

    //Method to check if txtSenhaNova has the minimal lenght.
    public void senhaCheck() {
        //Checks txtSenha text length.
        if (txtSenha.getText().length() < 6) {
            labelSenha.setVisible(true);
        } else {
            labelSenha.setVisible(false);
        }
    }

    //Method to check if txtSenhaConfirma is equal to txtSenha.
    private void senhaConfirmaCheck() {
        //Check the content and if txtSenha isn't empty for the comparison.
        if (!txtSenhaConfirma.getText().equals(txtSenha.getText())
                && !txtSenha.getText().isEmpty()) {
            labelSenhaConfirma.setVisible(true);
        } else {
            labelSenhaConfirma.setVisible(false);
        }
    }

    //"Clear" the screen after actions that call it.
    public void resetScreen() {
        //Hide passwordField "things".
        iconOn1.setVisible(false);
        iconOn2.setVisible(false);
        labelSenha.setVisible(false);
        labelSenhaConfirma.setVisible(false);

        //Clear textField values and disable addButton.
        btAdd.setDisable(true);
        txtSenha.setText("");
        txtSenhaConfirma.setText("");
        txtId.setText("");
    }
}
