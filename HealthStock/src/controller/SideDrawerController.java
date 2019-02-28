package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class SideDrawerController {

    //MainScreenController Instance.
    private MainScreenController main;

    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton btHome;
    @FXML
    private JFXButton btUsuario;
    @FXML
    private JFXButton btMedicamento;
    @FXML
    private JFXButton btAbout;
    @FXML
    private JFXButton btConfig;
    @FXML
    private JFXDrawer drawerUsuario;
    @FXML
    private JFXDrawer drawerMedicamento;

    @FXML
    void btAboutClicked(ActionEvent event) {

    }

    @FXML
    void btConfigClicked(ActionEvent event) {

    }

    @FXML
    void btHomeClicked(ActionEvent event) {
        main.changeDrawer();
        main.showMainSearch();
    }

    @FXML
    void btMedicamentoClicked(ActionEvent event) {
        changeSubDrawerState(drawerMedicamento);
    }

    @FXML
    void btUsuarioClicked(ActionEvent event) {
        changeSubDrawerState(drawerUsuario);
    }

    //If the DrawerMenu is dragged it gets closed.
    @FXML
    void drawerClickReleased(MouseEvent event) {
        drawer.close();
        if (drawer.isHiding()) {
            //Plays the "close" burgerAnimation.
            main.setBurgerAnimation(-1.0);
        }
    }

    //Initializer.
    public void init(MainScreenController main) {
        this.main = main;
        //Initializes the drawerMenus
        //Set the SideMenu on the sidePane of the drawerMenu.
        drawer.setSidePane(anchorPane);
        //Call the method to create the GridPanes for the sub-menus.
        drawerUsuario.setSidePane(initMenuUsuario());
        drawerMedicamento.setSidePane(initMenuMedicamento());
    }

    //SUBMENU'S STATE CHANGE METHOD.
    //Show/Hide Drawers based on the param.
    public void changeSubDrawerState(JFXDrawer drawer) {
        //If the drawer already is shown, close it.
        if (drawerUsuario.isShown()
                || drawerUsuario.isShowing() 
                || !drawerUsuario.equals(drawer)) {
            drawerUsuario.close();
            drawerUsuario.toBack();
            drawerUsuario.setVisible(false);
            //btMedicamento gets back to it's original position.
            btMedicamento.setTranslateY(0);
        //If it isn't.
        } else {
            //btMedicamento goes down to display the DrawerUsuario SubMenu.
            btMedicamento.setTranslateY(108);
            drawerUsuario.setVisible(true);
            drawerUsuario.toFront();
            drawerUsuario.open();
        }
        //DrawerMedicamento Variant.
        //Here the btMedicamento position doesn't need to be changed.
        if (drawerMedicamento.isShown() 
                || drawerMedicamento.isShowing() 
                || !drawerMedicamento.equals(drawer)) {
            drawerMedicamento.close();
            drawerMedicamento.toBack();
            drawerMedicamento.setVisible(false);
        } else {
            drawerMedicamento.setVisible(true);
            drawerMedicamento.toFront();
            drawerMedicamento.open();
        }
    }
    
    //SUB-MENU EVENTHANDLER METHOD.
    //Usuario
    public GridPane initMenuUsuario() {
        //Creates the Buttons.
        JFXButton btAdd = new JFXButton("Adicionar");
        JFXButton btSearch = new JFXButton("Buscar");

        //Handler to show the UsuarioAdd screen.
        btAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //Method to call the scene.
            main.showUsuarioAdd();
            //Method to hide the drawer.
            main.changeDrawer();
        });
        
        //Handler to show the UsuarioSearch screen.
        btSearch.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //Method to call the scene.
            main.showUsuarioSearch();
            //Method to hide the drawer.
            main.changeDrawer();
        });
        
        return initMenu(btAdd, btSearch);
    }

    //Medicamento
    public GridPane initMenuMedicamento() {
        //Creates the Buttons.
        JFXButton btAdd = new JFXButton("Adicionar");
        JFXButton btSearch = new JFXButton("Buscar");

        //Handler to show the MedicamentoAdd screen.
        btAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //Method to call the scene.
            main.showMedicamentoAdd();
            //Method to hide the drawer.
            main.changeDrawer();
        });
        
        //Handler to show the MedicamentoSearch screen.
        btSearch.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            //Method to call the scene.
            main.showMedicamentoSearch();
            //Method to hide the drawer.
            main.changeDrawer();
        });
        
        return initMenu(btAdd, btSearch);
    }

    //SUBMENU GRIDPANE CREATOR METHOD.
    public GridPane initMenu(JFXButton btAdd, JFXButton btSearch) {
        //Creates the Button Icons.
        MaterialDesignIconView iconAdd = new MaterialDesignIconView(
                MaterialDesignIcon.PLUS_CIRCLE);
        MaterialDesignIconView iconSearch = new MaterialDesignIconView(
                MaterialDesignIcon.MAGNIFY);
        iconAdd.setSize("15.0");
        iconSearch.setSize("15.0");

        //Adjust the button settings.
        //Add
        btAdd.setPrefWidth(217);
        btAdd.setPrefHeight(54);
        btAdd.setGraphic(iconAdd);
        btAdd.setGraphicTextGap(20);
        btAdd.setPadding(new Insets(0, 0, 0, 50));
        btAdd.setAlignment(Pos.CENTER_LEFT);
        //Search
        btSearch.setPrefWidth(217);
        btSearch.setPrefHeight(54);
        btSearch.setGraphic(iconSearch);
        btSearch.setGraphicTextGap(20);
        btSearch.setPadding(new Insets(0, 0, 0, 50));
        btSearch.setAlignment(Pos.CENTER_LEFT);

        //Create grid and add the buttons to it.
        GridPane grid = new GridPane();
        grid.setVgap(0);
        grid.setPadding(Insets.EMPTY);
        grid.add(btAdd, 0, 0);
        grid.add(btSearch, 0, 1);

        //Return the grid.
        return grid;
    }

    //Getter for the drawerMenu.
    public JFXDrawer getDrawer() {
        return drawer;
    }
}
