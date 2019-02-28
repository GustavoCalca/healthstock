package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//Model class for a User.
public class User {

    private final IntegerProperty userId;
    private final StringProperty userName;
    private final StringProperty userCargo;
    private final StringProperty userPassword;
    
    //Constructor of the Class
    //Works similarly to inserting values on a DataBase Table.
    public User(
            Integer userId, 
            String userName, 
            String userCargo, 
            String userPassword) {
        this.userId = new SimpleIntegerProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.userCargo = new SimpleStringProperty(userCargo);
        this.userPassword = new SimpleStringProperty(userPassword);
    }

    //userId
    public IntegerProperty getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    //userName
    public StringProperty getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    //userCargo
    public StringProperty getUserCargo() {
        return userCargo;
    }

    public void setUserCargo(String userCargo) {
        this.userCargo.set(userCargo);
    }

    public StringProperty userCargoProperty() {
        return userCargo;
    }

    //userPassword
    public StringProperty getUserPassword() {
        return userPassword;
    }

    public final void setUserPassword(String value) {
        userPassword.set(value);
    }

    public StringProperty userPasswordProperty() {
        return userPassword;
    }
}
