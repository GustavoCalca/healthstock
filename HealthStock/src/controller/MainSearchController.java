package controller;

import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.DateUtil;
import model.Medicamento;

public class MainSearchController extends DateUtil {

    //MainScreenController Instance.
    private MainScreenController main;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Medicamento> tableSearch;
    @FXML
    private TableColumn<Medicamento, String> columnMedicamento;
    @FXML
    private TableColumn<Medicamento, Integer> columnQtd;
    @FXML
    private TableColumn<Medicamento, String> columnValidade;

    
    public void setItself(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    //Initializer.
    public void init(MainScreenController main) {
        this.main = main;

        setValues();
    }

    //Method to set the Medicamento values into the tableSearch.
    public void setValues() {
        //Set the RealName as the columnMedicamento value.
        columnMedicamento.setCellValueFactory(
                new PropertyValueFactory<>("medicamentoRealName"));

        //Set the Quantity as the columnQtd value.
        columnQtd.setCellValueFactory(
                new PropertyValueFactory<>("medicamentoQuantity"));

        //Set the Vality as the columnValidade value.
        columnValidade.setCellValueFactory(
                //Formatting the LocalDate.
                (TableColumn.CellDataFeatures<Medicamento, String> value) -> {
                    final Medicamento medicamento = value.getValue();
                    final SimpleObjectProperty<String> simpleObject
                    = new SimpleObjectProperty<>(
                            format(medicamento.getMedicamentoVality()));
                    return simpleObject;
                });

        //Set the Medicamento items on the tableSearch.
        tableSearch.setItems(main.getMedicamentoData());
    }

    //Method to update tableSearch with the searched RealName value.
    public void searchValue(String search) {
        FilteredList filter
                = new FilteredList(main.getMedicamentoData(), e -> true);
        filter.setPredicate(
                (Predicate<Medicamento>) (Medicamento medicamento) -> {
                    if (search.isEmpty() || search.equals("")) {
                        //If empty display every registry.
                        return true;
                    } else if (medicamento.getMedicamentoRealName().
                            contains(search)) {
                        //Display the searched value.
                        return true;
                    }
                    return false;
                });

        //Create a new list with the searched values.
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(
                tableSearch.comparatorProperty());
        //Display the sorted list on the tableSearch.
        tableSearch.setItems(sort);
    }
}
