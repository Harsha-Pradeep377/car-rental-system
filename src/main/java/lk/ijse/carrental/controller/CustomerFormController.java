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
import lk.ijse.carrental.service.custom.CustomerService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;


public class CustomerFormController {


    public AnchorPane customerNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;



    private CustomerService customerService = ServiceFactory.getService(ServiceType.CUSTOMER);


    @FXML
    void btnHomePageOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/homepage_form.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) this.customerNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        var custDto = new CustomerDto(id,name,nic,address,contact);

        try {
            customerService.saveCustomer(custDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved!").show();
            clearFields();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtNic.setText("");
        txtAddress.setText("");
        txtContact.setText("");
     }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String custId = txtId.getText();
       try {
           CustomerDto customerDto = customerService.search(custId);
           if(customerDto != null) {
               txtName.setText(customerDto.getName());
               txtNic.setText(customerDto.getNic());
               txtContact.setText(customerDto.getContact());
               txtAddress.setText(customerDto.getAddress());
           }else{
               new Alert(Alert.AlertType.WARNING,"Customer not found").show();
           }
       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }

    }
    @FXML
    void btnClearFieldsOnAction(ActionEvent event) {
        clearFields();
    }
}
