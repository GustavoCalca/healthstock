package controller;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Medicamento;
import model.PasswordFieldUtil;
import model.User;

public class MainScreenController {

//USER CODE.
//On the MainController every controller will be able to access the "DataBase".
//----------------------------------------------------------------------    
    //The data as an observable list of Users.
    //Works as the Table of the DataBase.
    private ObservableList<User> userData
            = FXCollections.observableArrayList();

    //Constructor
    //Pre-established values on the "table".
    public void userConstructor() {
        userData.add(new User(1, "João", "Administrador", "123456"));
        userData.add(new User(2, "Maria", "Enfermeira", "654321"));
        userData.add(new User(3, "Joãozinho", "Farmacêutico", "123123"));
        userData.add(new User(4, "Joaquim", "Gerente", "321321"));
    }

    //Returns the data as an observable list of Users.
    public ObservableList<User> getUserData() {
        return userData;
    }
//----------------------------------------------------------------------      

//MEDICAMENTO CODE.
//On the MainController every controller will be able to access the "DataBase".
//----------------------------------------------------------------------      
    //The data as an observable list of Medicamentos.
    //Works as the Table of the DataBase.
    private ObservableList<Medicamento> medicamentoData
            = FXCollections.observableArrayList();

    //Constructor
    //Pre-established values on the "table".
    public void medicamentoConstructor() {
        medicamentoData.add(new Medicamento(
                "Nome Comercial 1",
                "Nome Real 1",
                "Lote 1",
                "Setor 1",
                "Ampola",
                25,
                LocalDate.of(2018, 10, 12)//-> 10/12/2018
        ));
        medicamentoData.add(new Medicamento(
                "Nome Comercial 2",
                "Nome Real 2",
                "Lote 2",
                "Setor 2",
                "Comprimido",
                50,
                LocalDate.of(2018, 11, 12)//-> 11/12/2018
        ));
        medicamentoData.add(new Medicamento(
                "Nome Comercial 3",
                "Nome Real 3",
                "Lote 3",
                "Setor 3",
                "Soluto",
                100,
                LocalDate.of(2018, 12, 12)//-> 12/12/2018
        ));
    }

    //Returns the data as an observable list of Medicamentos.
    public ObservableList<Medicamento> getMedicamentoData() {
        return medicamentoData;
    }
//----------------------------------------------------------------------      
    
    //Stage for the Login "dialog".
    private final Stage stage = new Stage();

    @FXML
    BorderPane rootStage;//The rootStage of the app.

    //Controllers that will use the MainScreenController.
    @FXML
    TopMenuController topMenuController;
    @FXML
    MainSearchController mainSearchController;
    @FXML
    SideDrawerController sideDrawerController;
    @FXML
    UsuarioAddController usuarioAddController;
    @FXML
    UsuarioSearchController usuarioSearchController;
    @FXML
    MedicamentoAddController medicamentoAddController;
    @FXML
    MedicamentoSearchController medicamentoSearchController;
    @FXML
    LoginController loginController;

    //Extended class from usuarioAddController.
    PasswordFieldUtil passwordFieldUtil;

    //Initializers.
    @FXML
    public void initialize() {
//USER CODE.
//----------------------------------------------------------------------    
        //Calls the Usuario "Table" Constructor        
        userConstructor();
//----------------------------------------------------------------------    

//MEDICAMENTO CODE.
//----------------------------------------------------------------------    
        //Calls the Medicamento "Table" Constructor
        medicamentoConstructor();
//---------------------------------------------------------------------- 

        //Initialize the Main Screen Active Controllers.
        topMenuController.init(this);
        sideDrawerController.init(this);
        showMainSearch();

        //Calls the setting method of the Login "dialog" Stage.
        stageSetting();
    }
    
    //Method to configure the login "dialog" Stage.
    public void stageSetting() {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    //Set a specific frame on the burgerAnimation and play it.
    public void setBurgerAnimation(Double value) {
        topMenuController.getBurgerAnimation().setRate(value);
        topMenuController.getBurgerAnimation().play();
    }

    //Method to display/hide the drawerMenu and it's SubMenus.
    public void changeDrawer() {
        //Show/Hide the Drawer state.
        sideDrawerController.getDrawer().toggle();
        //Hide the drawer's sideMenus.
        sideDrawerController.changeSubDrawerState(null);

        //Get the animation stage of the hamburger.
        //If it didn't started then will start and show the drawerMenu.
        if (topMenuController.getBurgerAnimation().getRate() == -1) {
            topMenuController.doAnimation();
        } else {
            //If not then it will restart.
            topMenuController.doAnimation();
        }
    }

    //Method to show the UsuarioAdd screen.
    public void showUsuarioAdd() {
        try {
            //Load the usuarioAdd.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/view/UsuarioAdd.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            //Get the scene controller.
            usuarioAddController
                    = (UsuarioAddController) loader.getController();
            //References the usuarioAdd to it's controller.
            usuarioAddController.setItself(anchorPane);
            //Initializes it's main instance.
            usuarioAddController.init(this);

            //Set the usuarioAdd screen on the center of the rootStage.
            rootStage.setCenter(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Method to show the UsuarioSearch screen.
    public void showUsuarioSearch() {
        try {
            //Load the usuarioSearch.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/view/UsuarioSearch.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            //Get the scene controller.
            usuarioSearchController
                    = (UsuarioSearchController) loader.getController();
            //References the usuarioSearch to it's controller.
            usuarioSearchController.setItself(anchorPane);
            //Initializes it's main instance.
            usuarioSearchController.init(this);

            //Set the usuarioSearch screen on the center of the rootStage.
            rootStage.setCenter(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Method to show the MedicamentoAdd screen.
    public void showMedicamentoAdd() {
        try {
            //Load the medicamentoAdd.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/view/MedicamentoAdd.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            //Get the scene controller.
            medicamentoAddController
                    = (MedicamentoAddController) loader.getController();
            //References the medicamentoAdd to it's controller.
            medicamentoAddController.setItself(anchorPane);
            //Initializes it's main instance.
            medicamentoAddController.init(this);

            //Set the medicamentoAdd screen on the center of the rootStage.
            rootStage.setCenter(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Method to show the MedicamentoSearch screen.
    public void showMedicamentoSearch() {
        try {
            //Load the medicamentoSearch.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/view/MedicamentoSearch.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            //Get the scene controller.
            medicamentoSearchController
                    = (MedicamentoSearchController) loader.getController();
            //References the medicamentoSearch to it's controller.
            medicamentoSearchController.setItself(anchorPane);
            //Initializes it's main instance.
            medicamentoSearchController.init(this);

            //Set the medicamentoSearch screen on the center of the rootStage.
            rootStage.setCenter(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Method to show the MedicamentoSearch screen.
    public void showMainSearch() {
        try {
            //Load the medicamentoSearch.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    getClass().getResource("/view/MainSearch.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            //Get the scene controller.
            mainSearchController
                    = (MainSearchController) loader.getController();
            //References the medicamentoSearch to it's controller.
            mainSearchController.setItself(anchorPane);
            //Initializes it's main instance.
            mainSearchController.init(this);

            //Set the medicamentoSearch screen on the center of the rootStage.
            rootStage.setCenter(anchorPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Method to show the Login "dialog" in the Stage.
    public void showLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Login.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();

        //Get the scene controller.
        loginController = (LoginController) loader.getController();
        //References the LoginController to itself.
        loginController.setItself(anchorPane);
        //Initializes it's main instance.
        loginController.init(this);
        
        anchorPane.setEffect(new DropShadow());
        
        //Sets a new Scene for the Login "dialog".
        Scene scene = new Scene(anchorPane);
        scene.setFill(Color.TRANSPARENT);
        
        //Confiure the new Stage.
        stage.centerOnScreen();
        stage.requestFocus();

        stage.setScene(scene);
        stage.showAndWait();
    }

    //Method to hide/close the Login "dialog" Stage.
    public void closeStage() {
        stage.close();
    }

//MEDICAMENTO CODE.
//----------------------------------------------------------------------    
    //Method to show the searched values on the tableSearch.
    public void searchTable(String search) {
        mainSearchController.searchValue(search);
    }
//----------------------------------------------------------------------    

//USER CODE.
//----------------------------------------------------------------------    
    //Index value for reference.
    private int indexUser = userData.size();

    //Method to search the userId from the userId arg.
    public boolean getUser(Integer userId) {
        //Runs throught each element of the userData list.
        for (int i = 0; i < userData.size(); i++) {
            //Checks if the active userData user's Id equals the arg.
            if (userData.get(i).getUserId().get() == userId) {
                //Index receives the correct index searched value.
                indexUser = i;

                //Returns true if the Id was found.
                return true;
            }
        }
        //Returns false if the Id wasn't in the userData.
        return false;
    }

    //Set the active User for the usuarioSearchController.
    public void setUserSearchController() {
        //Set the active user as the one searched.
        usuarioSearchController.setUser(userData.get(indexUser));
    }
    
    public void setUserLoginController(){
        loginController.setUser(userData.get(indexUser));
    }

    //Add a new User to the userData list.
    public void addUser(User user) {
        userData.add(user);
    }
//----------------------------------------------------------------------    

//MEDICAMENTO CODE.
//----------------------------------------------------------------------    
    //Index value for reference.
    private int indexMedicamento = medicamentoData.size();

    //Method to search the medicamentoRealName in the Medicamento DataBase.
    public boolean getMedicamento(String medicamentoRealName) {
        //Runs throught each element of the medicamentoData list.
        for (int i = 0; i < medicamentoData.size(); i++) {
            //Checks the active medicamentoData medicamento's RealName.
            if (medicamentoData.get(i).getMedicamentoRealName().equals(
                    medicamentoRealName)) {
                //Index receives the correct index searched value.
                indexMedicamento = i;

                //Returns true if the RealName was found.
                return true;
            }
        }
        //Returns false if the RealName wasn't in the medicamentoData.
        return false;
    }

    //Set the active Medicamento for the medicamentoSearchController.
    public void setMedicamentoSearchController() {
        //Set the active medicamento as the one searched.
        medicamentoSearchController.setMedicamento(
                medicamentoData.get(indexMedicamento)
        );
    }

    //Add a new Medicamento to the medicamentoData list.
    public void addMedicamento(Medicamento medicamento) {
        medicamentoData.add(medicamento);
    }
//----------------------------------------------------------------------    
}
