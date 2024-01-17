package lk.ijse.carrental.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageFormController {

    public AnchorPane rootNode;

    @FXML
    void btnCustomerManageOnAction(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
       Scene scene = new Scene(root);

       Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();
       primaryStage.setScene(scene);
       primaryStage.centerOnScreen();
       primaryStage.setTitle("Customer Form");

    }

}
