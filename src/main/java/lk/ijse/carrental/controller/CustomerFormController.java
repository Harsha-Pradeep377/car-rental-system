package lk.ijse.carrental.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carrental.dto.CustomerDto;
import lk.ijse.carrental.dto.tm.CustomerTm;
import lk.ijse.carrental.service.custom.CustomerService;
import lk.ijse.carrental.service.custom.ServiceFactory;
import lk.ijse.carrental.service.custom.ServiceType;

import java.io.IOException;
import java.util.List;


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

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;


    @FXML
    private TableView<CustomerTm> tblCustomer;


    private CustomerService customerService = ServiceFactory.getService(ServiceType.CUSTOMER);

    public void initialize() {
        setCellValueFactory();
        getAllCustomers();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void getAllCustomers() {
        try {
            ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();
            List<CustomerDto> customerDtos = customerService.getAll();

            for (CustomerDto dto:customerDtos) {
               var customerTm = new CustomerTm(dto.getId(), dto.getName(), dto.getNic(), dto.getAddress(), dto.getContact());
                observableList.add(customerTm);
            }
            tblCustomer.setItems(observableList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

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
            initialize();
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
    void tblSearchCustomerOnAction(MouseEvent event) {
        String custId = tblCustomer.getSelectionModel().getSelectedItem().getId();
        try {
            CustomerDto customerDto = customerService.search(custId);
            if(customerDto != null) {
                txtId.setText(customerDto.getId());
                txtName.setText(customerDto.getName());
                txtNic.setText(customerDto.getNic());
                txtContact.setText(customerDto.getContact());
                txtAddress.setText(customerDto.getAddress());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        var custDto = new CustomerDto(id,name,nic,address,contact);
        try {
            customerService.updateCustomer(custDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            CustomerDto dto = customerService.search(id);
            customerService.deleteCustomer(dto);
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted!").show();
            clearFields();
            initialize();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnClearFieldsOnAction(ActionEvent event) {
        clearFields();
    }
}
