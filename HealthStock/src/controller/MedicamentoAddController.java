package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.DateUtil;
import model.Medicamento;

public class MedicamentoAddController extends DateUtil {

    //MainScreenController Instance.
    private MainScreenController main;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtNomeComercial;
    @FXML
    private JFXButton btAdd;
    @FXML
    private JFXButton btCancel;
    @FXML
    private JFXTextField txtNomeReal;
    @FXML
    private JFXTextField txtLote;
    @FXML
    private JFXTextField txtQtd;
    @FXML
    private JFXComboBox<String> comboSetor;
    @FXML
    private JFXComboBox<String> comboTipo;
    @FXML
    private JFXDatePicker dateVality;
    @FXML
    private Label labelSuccess;
    @FXML
    private Label labelNomeComercial;
    @FXML
    private Label labelNomeReal;
    @FXML
    private Label labelLote;
    @FXML
    private Label labelValidade;
    @FXML
    private Label labelQtd;

    @FXML
    void btAddClicked(ActionEvent event) {

        //Makes the checking of the fields.
        if (checkScreen()) {
            //Check if Medicamento already exists.
            if (main.getMedicamento(txtNomeReal.getText())) {
                //If it does then it is shown in the txtNomeReal textField.
                labelNomeReal.setVisible(true);
                txtNomeReal.requestFocus();
            } else {
                //If it doesn't exist it is then created.
//MEDICAMENTO CODE.
//----------------------------------------------------------------------        
                //Create the Medicamento with the inserted properties.
                Medicamento medicamento = new Medicamento(
                        txtNomeComercial.getText(),
                        txtNomeReal.getText(),
                        txtLote.getText(),
                        comboSetor.getValue(),
                        comboTipo.getValue(),
                        Integer.parseInt(txtQtd.getText()),
                        dateVality.getValue()
                );
                //Add the new Medicamento to the list in MedicamentoData.
                main.addMedicamento(medicamento);
//----------------------------------------------------------------------        

                //Reset the Screen.
                resetScreen();
                txtNomeReal.setText("");

                labelSuccess.setVisible(true);
                labelSuccess.requestFocus();
            }
        }
    }

    @FXML
    void btCancelClicked(ActionEvent event) {
        main.showMainSearch();
    }

    public void setItself(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    //Initializer.
    public void init(MainScreenController main) {
        this.main = main;

        initRealName();

//MEDICAMENTO CODE.
//----------------------------------------------------------------------        
        populateComboBox();

        //Initial setters for some values.
        dateVality.setValue(LocalDate.now());

        comboSetor.setValue(comboSetor.getItems().get(0));
        comboTipo.setValue(comboTipo.getItems().get(0));
//----------------------------------------------------------------------        
    }

    //Method to the focusProperty Listener.
    public void initRealName() {
        labelSuccess.focusedProperty().addListener((o, old_v, new_v) -> {
            //If lost focus them is hidden.
            if (!new_v) {
                labelSuccess.setVisible(false);
            }
        });
    }

//MEDICAMENTO CODE.
//----------------------------------------------------------------------        
    //Method to populate the comboBox with the DataBase values.
    public void populateComboBox() {
        comboSetor.getItems().addAll(
                "Setor 1",
                "Setor 2",
                "Setor 3"
        );
        comboTipo.getItems().addAll(
                "Ampola",
                "Comprimido",
                "Soluto"
        );
    }
//----------------------------------------------------------------------        

    //Checks if the fields are invalid to Add.
    public boolean checkScreen() {
        hideLabels();

        //bool value for the checking.
        boolean failed = false;

        //Check each field individualy.
        if (txtNomeComercial.getText().isEmpty()) {
            labelNomeComercial.setVisible(true);
            failed = true;
        }
        if (txtNomeReal.getText().isEmpty()) {
            labelNomeReal.setVisible(true);
            failed = true;
        }
        if (txtLote.getText().isEmpty()) {
            labelLote.setVisible(true);
            failed = true;
        }
        if (txtQtd.getText().isEmpty()) {
            labelQtd.setVisible(true);
            failed = true;
        }
        if (dateVality.getValue().isBefore(LocalDate.now())
                || dateVality.getValue().isEqual(LocalDate.now())) {
            labelValidade.setVisible(true);
            failed = true;
        }

        //Returns true if didn't failed.
        return !failed;
    }

    //"Clear" the screen after actions that call it.
    public void resetScreen() {
        hideLabels();

        //Clear the textField's values.
        txtNomeComercial.setText("");
        txtLote.setText("");
        txtQtd.setText("");
        dateVality.setValue(LocalDate.now());
        comboSetor.setValue(null);
        comboTipo.setValue(null);
    }

    //Method responsible to hide the control labels.
    public void hideLabels() {
        labelLote.setVisible(false);
        labelNomeComercial.setVisible(false);
        labelNomeReal.setVisible(false);
        labelQtd.setVisible(false);
        labelValidade.setVisible(false);
    }
}
