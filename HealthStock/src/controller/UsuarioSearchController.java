package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.PasswordFieldUtil;
import model.User;

public class UsuarioSearchController extends PasswordFieldUtil {

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
    //3rd PasswordField
    private final MaterialDesignIconView iconOn3
            = new MaterialDesignIconView(MaterialDesignIcon.EYE);
    private final MaterialDesignIconView iconOff3
            = new MaterialDesignIconView(MaterialDesignIcon.EYE_OFF);

    //Creates the textFields for support to the passwordFields.
    private final JFXTextField txtSenhaAtualSup = new JFXTextField();
    private final JFXTextField txtSenhaNovaSup = new JFXTextField();
    private final JFXTextField txtSenhaConfirmaSup = new JFXTextField();

//USER CODE.    
//----------------------------------------------------------------------    
    private User user;
//----------------------------------------------------------------------    

    //MainScreenController Instance.
    private MainScreenController main;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextArea textAreaLog;
    @FXML
    private JFXPasswordField txtSenhaNova;
    @FXML
    private JFXPasswordField txtSenhaConfirma;
    @FXML
    private JFXButton btSearchId;
    @FXML
    private Hyperlink hyperlinkLog;
    @FXML
    private Label labelSuccess;
    @FXML
    private Label labelCaps2;
    @FXML
    private Label labelCaps3;
    @FXML
    private Label labelSenhaConfirma;
    @FXML
    private Label labelSenhaNova;
    @FXML
    private Label labelCodigo;
    @FXML
    private JFXButton btConfirm;
    @FXML
    private JFXButton btEdit;
    @FXML
    private JFXButton btDelete;
    @FXML
    private JFXButton btCancel;
    @FXML
    private AnchorPane senhaNovaAnchorPane;
    @FXML
    private AnchorPane senhaConfirmaAnchorPane;
    @FXML
    private JFXPasswordField txtSenhaAtual;
    @FXML
    private Label labelCaps1;
    @FXML
    private Label labelSenhaAtual;
    @FXML
    private AnchorPane senhaAtualAnchorPane;

    @FXML
    void btCancelClicked(ActionEvent event) {
        main.showMainSearch();
    }

    @FXML
    void btConfirmClicked(ActionEvent event) {
        //Checks for errors on the passwordFields.
        //Request focus to the wrong passwordField if any.
        if (!labelSenhaAtual.isVisible()
                && !txtSenhaAtual.getText().isEmpty()) {
            if (!labelSenhaNova.isVisible()
                    && !txtSenhaNova.getText().isEmpty()) {
                if (!labelSenhaConfirma.isVisible()
                        && !txtSenhaConfirma.getText().isEmpty()) {
//USER CODE.
//----------------------------------------------------------------------
                    //Change the User's password.
                    user.setUserPassword(txtSenhaConfirma.getText());
//----------------------------------------------------------------------
                    resetScreen();
                    txtCodigo.setText("");

                    //Show confirmation label.
                    labelSuccess.setVisible(true);
                    labelSuccess.setText("Usuário Alterado com Sucesso");
                } else {
                    txtSenhaConfirma.requestFocus();
                }
            } else {
                txtSenhaNova.requestFocus();
            }
        } else {
            txtSenhaAtual.requestFocus();
        }
    }

    @FXML
    void btDeleteClicked(ActionEvent event) {
//USER CODE.
//----------------------------------------------------------------------    
        //Check the userPasword.
        if (txtSenhaAtual.getText().equals(user.getUserPassword().get())) {
            //Delete the searched User if the password is right.
            main.getUserData().remove(user);
            resetScreen();
            txtCodigo.setText("");

            //Show confirmation label.
            labelSuccess.setVisible(true);
            labelSuccess.setText("Usuário Excluído com Sucesso");
        } else {
            labelSenhaAtual.setVisible(true);
            txtSenhaAtual.requestFocus();
        }
//----------------------------------------------------------------------    
    }

    @FXML
    void btEditClicked(ActionEvent event) {

        //Enable the Edit Features.
        btDelete.setDisable(false);
        btConfirm.setDisable(false);
        txtSenhaAtual.setDisable(false);
        txtSenhaNova.setDisable(false);
        txtSenhaConfirma.setDisable(false);
        txtSenhaAtual.requestFocus();

        btEdit.setDisable(true);
    }

    @FXML
    void btSearchIdClicked(ActionEvent event) {
//USER CODE.
//----------------------------------------------------------------------    
        resetScreen();
        labelSuccess.setVisible(false);

        //Check the txtCodigo value and the User value.
        if (!txtCodigo.getText().isEmpty()
                && main.getUser(Integer.parseInt(txtCodigo.getText()))) {
            //Set the active User of the controller.
            main.setUserSearchController();

            //If it does exist, set the name on the txtNome.
            txtNome.textProperty().set(user.getUserName().get());
            textAreaLog.setText("Here lies my history");

            //Enables the Edit button once found the User.
            btEdit.setDisable(false);
            btConfirm.setDisable(true);
            btDelete.setDisable(true);

            //Hide the labelCodigo if it is right.
            labelCodigo.setVisible(false);
        } else {
            //In case it doesn't exist.
            //Clear the textArea and txtName.
            labelCodigo.setVisible(true);
            txtNome.setText("");
            textAreaLog.setText("");

            //Disables the Edit Features.
            btEdit.setDisable(true);
            btConfirm.setDisable(true);
            btDelete.setDisable(true);

            txtCodigo.requestFocus();
        }

        //Clear the passwordField text.
        txtSenhaAtual.setText("");
        txtSenhaNova.setText("");
        txtSenhaConfirma.setText("");
//----------------------------------------------------------------------   
    }

//USER CODE.
//----------------------------------------------------------------------    
    //Method to set the User from outside the searchUserController.
    public void setUser(User user) {
        this.user = user;
    }
//----------------------------------------------------------------------    

    public void setItself(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    //Initializer.
    public void init(MainScreenController main) {
        this.main = main;

        //Initializes the password methods.
        initSenhaAtual();
        initSenhaNova();
        initSenhaConfirma();

        //Call the handler for the txtCodigo focus property
        checkCodigoFocus();
    }

    public void checkCodigoFocus() {
        txtCodigo.focusedProperty().addListener((o, old_v, new_v) -> {
            if (new_v) {
                labelSuccess.setVisible(false);
            }
        });
    }

    //Password "initializers".
    public void initSenhaAtual() {
        //Loads the passwordUtil method(Icon).
        setSupTextField(txtSenhaAtualSup, txtSenhaAtual, anchorPane);
        setPasswordIcon(
                iconOn1,
                iconOff1,
                txtSenhaAtualSup,
                txtSenhaAtual,
                senhaAtualAnchorPane
        );

        //Handler for the passwordField.
        txtSenhaAtual.addEventHandler(KeyEvent.ANY, event -> {
            //When something is typed the icon is shown.
            senhaClicked(txtSenhaAtual, iconOn1);

            //Check if txtSenhaAtual is equal to the "DataBase" registry.
            if (labelSenhaAtual.isVisible()) {
                senhaAtualCheck();
            }
        });

        //Handler for the passwordField Content.
        txtSenhaAtual.focusedProperty().addListener((o, old_v, new_v) -> {
            //Fired when the passwordField lose focus.
            if (!new_v) {
                //Check if txtSenhaAtual is equal to the "DataBase" registry.
                senhaAtualCheck();
            }
        });
    }

    public void initSenhaNova() {
        //Loads the passwordUtil method(Icon).
        setSupTextField(txtSenhaNovaSup, txtSenhaNova, anchorPane);
        setPasswordIcon(
                iconOn2,
                iconOff2,
                txtSenhaNovaSup,
                txtSenhaNova,
                senhaNovaAnchorPane
        );

        //Handler for the passwordField.
        txtSenhaNova.addEventHandler(KeyEvent.ANY, event -> {
            //When something is typed the icon is shown.
            senhaClicked(txtSenhaNova, iconOn2);

            //label gets hidden as soon as it hits the min value.
            if (labelSenhaNova.isVisible()) {
                senhaNovaCheck();
            }
        });

        //Handler for the passwordField Content.
        txtSenhaNova.focusedProperty().addListener((o, old_v, new_v) -> {
            //Fired when the passwordField lose focus.
            if (!new_v) {
                //txtSenhaConfirma is checked when txtSenha loses focus.
                senhaConfirmaCheck();

                //Method to check if txtSenhaNova has the minimal lenght.
                senhaNovaCheck();
            }
        });
    }

    public void initSenhaConfirma() {
        //Loads the passwordUtil method(Icon).
        setSupTextField(txtSenhaConfirmaSup, txtSenhaConfirma, anchorPane);
        setPasswordIcon(
                iconOn3,
                iconOff3,
                txtSenhaConfirmaSup,
                txtSenhaConfirma,
                senhaConfirmaAnchorPane
        );

        //Handler for the passwordField.
        txtSenhaConfirma.addEventHandler(KeyEvent.ANY, event -> {
            //When something is typed the icon is shown.
            senhaClicked(txtSenhaConfirma, iconOn3);

            //Check if txtSenhaConfirma is equal to txtSenha.
            senhaConfirmaCheck();
        });
    }

    //Method to check if txtSenhaAtual is equal to the "DataBase" registry.
    private void senhaAtualCheck() {
        //Check the text and if txtSenhaAtual isn't empty for the comparison.
        if (!txtSenhaAtual.getText().equals(user.getUserPassword().get())
                && !txtSenhaAtual.getText().isEmpty()) {
            labelSenhaAtual.setVisible(true);
        } else {
            labelSenhaAtual.setVisible(false);
        }
    }

    //Method to check if txtSenhaNova has the minimal lenght.
    public void senhaNovaCheck() {
        //Checks txtSenha text length.
        if (txtSenhaNova.getText().length() < 6) {
            labelSenhaNova.setVisible(true);
        } else {
            labelSenhaNova.setVisible(false);
        }
    }

    //Method to check if txtSenhaConfirma is equal to txtSenhaNova.
    private void senhaConfirmaCheck() {
        //Check the content and if txtSenhaNova isn't empty for the comparison.
        if (!txtSenhaConfirma.getText().equals(txtSenhaNova.getText())
                && !txtSenhaNova.getText().isEmpty()) {
            labelSenhaConfirma.setVisible(true);
        } else {
            labelSenhaConfirma.setVisible(false);
        }
    }

    //Method to be called after a successful edition.
    public void resetScreen() {
        //Clear all the fields and request focus on txtCodigo.
        txtSenhaAtual.setText("");
        txtSenhaNova.setText("");
        txtSenhaConfirma.setText("");
        txtNome.setText("");
        textAreaLog.setText("");

        //Hide the passwordField's Icons and labels
        iconOn1.setVisible(false);
        iconOn2.setVisible(false);
        iconOn3.setVisible(false);
        labelSenhaAtual.setVisible(false);
        labelSenhaNova.setVisible(false);
        labelSenhaConfirma.setVisible(false);

        //Disable again the Edit Features.
        btEdit.setDisable(true);
        btDelete.setDisable(true);
        btConfirm.setDisable(true);
        txtSenhaAtual.setDisable(true);
        txtSenhaNova.setDisable(true);
        txtSenhaConfirma.setDisable(true);
    }
}
