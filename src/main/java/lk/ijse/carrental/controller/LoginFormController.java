package lk.ijse.carrental.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField txtPassward;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane loginNode;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

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
