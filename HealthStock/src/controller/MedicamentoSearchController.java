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

public class MedicamentoSearchController extends DateUtil {

//MEDICAMENTO CODE.    
//----------------------------------------------------------------------    
    private Medicamento medicamento;
//----------------------------------------------------------------------  

    //MainScreenController Instance.
    private MainScreenController main;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtNomeReal;
    @FXML
    private JFXTextField txtNomeComercial;
    @FXML
    private JFXTextField txtLote;
    @FXML
    private JFXTextField txtQtd;
    @FXML
    private JFXComboBox<String> comboSetor;
    @FXML
    private JFXComboBox<String> comboTipo;
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
    private Label labelValidadeTitle;
    @FXML
    private Label labelQtd;
    @FXML
    private JFXDatePicker dateVality;
    @FXML
    private JFXButton btConfirm;
    @FXML
    private JFXButton btEdit;
    @FXML
    private JFXButton btDelete;
    @FXML
    private JFXButton btCancel;
    @FXML
    private JFXButton btSearchReal;

    @FXML
    void btCancelClicked(ActionEvent event) {
        main.showMainSearch();
    }

    @FXML
    void btConfirmClicked(ActionEvent event) {
        //Check the screen for errors.
        if (checkScreen()) {
            //Set the new values on the selected Medicamento.
            medicamento.setMedicamentoCommercialName(
                    txtNomeComercial.getText());
            medicamento.setMedicamentoLot(
                    txtLote.getText());
            medicamento.setMedicamentoQuantity(
                    Integer.parseInt(txtQtd.getText()));
            medicamento.setMedicamentoSector(
                    comboSetor.getValue());
            medicamento.setMedicamentoType(
                    comboTipo.getValue());
            medicamento.setMedicamentoVality(
                    dateVality.getValue());

            //Resets the screen.
            resetScreen();
            txtNomeReal.setText("");

            //Show Confirmation Label.
            labelSuccess.setVisible(true);
            labelSuccess.setText("Medicamento Alterado com Sucesso");
        }
    }

    @FXML
    void btDeleteClicked(ActionEvent event) {
        //Delete the searched Medicamento.
        main.getMedicamentoData().remove(medicamento);
        resetScreen();
        txtNomeReal.setText("");

        //Show confirmation label.
        labelSuccess.setVisible(true);
        labelSuccess.setText("Medicamento Excluído com Sucesso");
    }

    @FXML
    void btEditClicked(ActionEvent event) {

        //Enable the Edit Features.
        btDelete.setDisable(false);
        btConfirm.setDisable(false);
        txtNomeComercial.setDisable(false);
        txtLote.setDisable(false);
        txtQtd.setDisable(false);
        dateVality.setDisable(false);
        comboSetor.setDisable(false);
        comboTipo.setDisable(false);

        //Title label for the ValityDatePicker.
        labelValidadeTitle.setDisable(false);

        btEdit.setDisable(true);
    }

    @FXML
    void btSearchReal(ActionEvent event) {
        resetScreen();
        labelSuccess.setVisible(false);

//MEDICAMENTO CODE.
//----------------------------------------------------------------------  
        //Check the txtNomeReal value and the Medicamento value.
        if (!txtNomeReal.getText().isEmpty()
                && main.getMedicamento(txtNomeReal.getText())) {
            //Set active Medicamento on the controller.
            main.setMedicamentoSearchController();
//----------------------------------------------------------------------  
            //Show the Medicamento data on the disabled fields.
            txtNomeComercial.textProperty().set(
                    medicamento.medicamentoCommercialNameProperty().get());
            txtLote.textProperty().set(
                    medicamento.medicamentoLotProperty().get());
            txtQtd.textProperty().set(medicamento.
                    medicamentoQuantityProperty().getValue().toString());
            comboSetor.valueProperty().set(
                    medicamento.medicamentoSectorProperty().get());
            comboTipo.valueProperty().set(
                    medicamento.medicamentoTypeProperty().get());
            dateVality.valueProperty().set(
                    medicamento.getMedicamentoVality());

            //Enables the Edit button once found the User.
            btEdit.setDisable(false);
            btConfirm.setDisable(true);
            btDelete.setDisable(true);

            //Hide the labelNomeReal if it is right.
            labelNomeReal.setVisible(false);
        } else {
            //In case it doesn't exist.
            labelNomeReal.setVisible(true);

            //Disables the Edit features.
            btEdit.setDisable(true);
            btConfirm.setDisable(true);
            btDelete.setDisable(true);

            txtNomeReal.requestFocus();
        }
    }

//MEDICAMENTO CODE.
//----------------------------------------------------------------------    
    //Method to set the Medicamento from outside the searchMedicamentoController.
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
//---------------------------------------------------------------------- 

    public void setItself(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    //Initializer.
    public void init(MainScreenController main) {
        this.main = main;

        checkNomeRealFocus();

//MEDICAMENTO CODE.
//---------------------------------------------------------------------- 
        populateComboBox();
//---------------------------------------------------------------------- 
    }

    public void checkNomeRealFocus() {
        txtNomeReal.focusedProperty().addListener((o, old_v, new_v) -> {
            if (new_v) {
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

    //Checks if the fields are invalid to Edit.
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

    //Method to reset the screen content.
    public void resetScreen() {
        hideLabels();

        //Clear the textField values.
        txtNomeComercial.setText("");
        txtLote.setText("");
        txtQtd.setText("");

        dateVality.setValue(null);

        comboSetor.setValue(null);
        comboTipo.setValue(null);

        //Title label for the ValityDatePicker.
        labelValidadeTitle.setDisable(true);

        //Disable the Edit Features.
        btEdit.setDisable(true);
        btDelete.setDisable(true);
        btConfirm.setDisable(true);
        txtNomeComercial.setDisable(true);
        txtLote.setDisable(true);
        txtQtd.setDisable(true);
        dateVality.setDisable(true);
        comboSetor.setDisable(true);
        comboTipo.setDisable(true);
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
