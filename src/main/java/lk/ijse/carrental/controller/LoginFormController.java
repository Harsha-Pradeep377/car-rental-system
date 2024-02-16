package lk.ijse.carrental.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.dto.UserDto;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;
import lk.ijse.carrental.service.custom.UserService;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginFormController {

    @FXML
    private TextField txtPassward;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane loginNode;

    private UserService userService = ServiceFactory.getService(ServiceType.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String pass = txtPassward.getText();
        String hashPassward = hashPassword(pass);

        if (userName.isEmpty() || pass.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter username and password!").show();
            return;
        }
        txtUserName.setText("");
        txtPassward.setText("");

        UserDto userDto = userService.getUser(userName);
        try {
            if(userDto != null){
                if(userName.equals(userDto.getUserName()) && hashPassward.equals(userDto.getPass())){
                    Parent root = FXMLLoader.load(this.getClass().getResource("/view/homepage_form.fxml"));
                    Scene scene = new Scene(root);

                    Stage stage = (Stage) this.loginNode.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("Home Page");
                }else{
                    new Alert(Alert.AlertType.WARNING,"Enter valid user name or passward!").show();
                }
                txtUserName.setText("");
                txtPassward.setText("");
            }else {
                new Alert(Alert.AlertType.WARNING,"Enter valid user name and passward!").show();
            }
            txtUserName.setText("");
            txtPassward.setText("");
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    private String hashPassword(String passward) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(passward.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    void linktoCreateUserOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage) this.loginNode.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("User Registration Form");

    }
}
