package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TopMenuController {

    //MainScreenController Instance.
    private MainScreenController main;

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private ImageView logoHome;
    @FXML
    private JFXButton btLogin;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btSearch;

    private HamburgerBackArrowBasicTransition burgerAnimation;

    @FXML
    void btLoginClicked(ActionEvent event) throws IOException {
        main.showLogin();
    }

    @FXML
    void btSearchClicked(ActionEvent event) {
            main.searchTable(txtSearch.getText());
    }

    //When the hambuger is clicked it's animation is checked.
    @FXML
    void hamburgerClicked(MouseEvent event) {
        main.changeDrawer();
    }

    //Initializer
    public void init(MainScreenController main) {
        this.main = main;

        //Burger Animation setup.
        burgerAnimation = new HamburgerBackArrowBasicTransition(hamburger);
        burgerAnimation.setRate(-1);
    }

    //HAMBURGER ANIMATION METHOD.
    public void doAnimation() {
        burgerAnimation.setRate(burgerAnimation.getRate() * -1);
        burgerAnimation.play();
    }

    //Getter for the animation of the hamburger.
    public HamburgerBackArrowBasicTransition getBurgerAnimation() {
        return burgerAnimation;
    }

    //Setter for the animation of the hamburger.
    public void setBurgerAnimation(
            HamburgerBackArrowBasicTransition burgerAnimation) {
        this.burgerAnimation = burgerAnimation;
    }

    public JFXButton getBtLogin() {
        return btLogin;
    }
}
